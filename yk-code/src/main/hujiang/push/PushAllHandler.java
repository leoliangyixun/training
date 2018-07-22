package com.hujiang.notifycenter.push.api.handler;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.hujiang.basic.framework.core.util.JsonUtil;
import com.hujiang.notifycenter.push.api.config.MqInitializer;
import com.hujiang.notifycenter.push.core.ContextConfig;
import com.hujiang.notifycenter.push.model.bo.App;
import com.hujiang.notifycenter.push.model.bo.PushBreakTask;
import com.hujiang.notifycenter.push.model.common.AudienceType;
import com.hujiang.notifycenter.push.model.common.Status;
import com.hujiang.notifycenter.push.model.dto.Audience;
import com.hujiang.notifycenter.push.model.dto.Payload;
import com.hujiang.notifycenter.push.model.po.PushBreakTaskPo;
import com.hujiang.notifycenter.push.service.PushBreakTaskService;
import com.hujiang.notifycenter.push.service.PushByTagService;

/**
 * 推送给所有用户
 */
@Component
public class PushAllHandler extends AbstractPushHandler {

    //每个子任务人数
    @Value("${push.breakTask.size}")
    protected Integer size;

    @Autowired
    protected PushBreakTaskService pushBreakTaskService;

    @Autowired
    protected PushByTagService pushByTagService;

    @SuppressWarnings("rawtypes")
    @Override
    public void handle(Payload payload) {
        App app = payload.getApp();
        String tag = app.getAppCategory();
        String appUniqueId = app.getIosBundleId();
        //总人数
        Integer count = pushByTagService.countByTag(tag, appUniqueId);
        //总子任务数
        Integer total = (count % size) == 0 ? (count / size) : (count / size) + 1;
        payload.setBreakTaskCount(total);
        payload.setTotalCount(count);
        payload.setAudienceType(AudienceType.ALL);
        //TODO 最好下沉到JOB，提高API QPS
        //原始任务入库
        save(payload);

        List<PushBreakTask> tasks = Lists.newArrayList();

        //拆分子任务
        CompletableFuture[] futures = IntStream.range(1, total + 1)
            .mapToObj(i -> CompletableFuture.supplyAsync(() -> pushBreakTaskService.getBreakTaskByTag(payload.getMsgId(), i, tag, appUniqueId, i, size), ContextConfig.PUSH_THREAD_POOL))
            .map(future -> future.thenAccept(task -> Optional.ofNullable(task).ifPresent(tasks::add)))
            .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();

        if (CollectionUtils.isNotEmpty(tasks)) {
            //子任务入库
            List<PushBreakTaskPo> entities = tasks.stream().map(task -> {
                PushBreakTaskPo entity = new PushBreakTaskPo();
                entity.setMsgId(payload.getMsgId());
                entity.setTaskId(task.getTaskId());
                entity.setAppId(app.getAppId());
                entity.setAudience(JsonUtil.object2JSON(task.getTags()));
                entity.setStatus(Status.RECEIVE.status());
                return entity;
            }).collect(Collectors.toList());

            saveAll(entities);

            //子任务发送到MQ
            tasks.forEach(task -> {
                if (CollectionUtils.isNotEmpty(task.getTags())) {
                    payload.setTaskId(task.getTaskId());
                    payload.setIsBreakTask(true);
                    payload.setBreakTask(task);
                    payload.setAudience(new Audience());
                    MqInitializer.publisher(tag).send(payload);
                }
            });
        }
    }

    protected void saveAll(List<PushBreakTaskPo> entities) {
        pushBreakTaskService.saveAll(entities);
    }
}
