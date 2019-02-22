package com.hujiang.notifycenter.push.job.processor;

import static com.hujiang.notifycenter.push.core.ContextConfig.DEFAULT_SHARDING_SDF;
import static com.hujiang.notifycenter.push.model.common.AudienceType.ALIAS;
import static com.hujiang.notifycenter.push.model.common.AudienceType.ALL;
import static com.hujiang.notifycenter.push.model.common.AudienceType.DEVICE;
import static com.hujiang.notifycenter.push.model.common.AudienceType.TAG;
import static com.hujiang.notifycenter.push.model.common.AudienceType.TOKEN;
import static com.hujiang.notifycenter.push.model.common.AudienceType.USER;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;
import com.hujiang.basic.framework.core.util.JsonUtil;
import com.hujiang.notifycenter.gundam.common.model.constant.DeviceInfoStatus;
import com.hujiang.notifycenter.gundam.common.model.constant.HJPushException;
import com.hujiang.notifycenter.gundam.common.model.constant.ResponseCodeEnum;
import com.hujiang.notifycenter.push.core.Interceptor;
import com.hujiang.notifycenter.push.core.Processor;
import com.hujiang.notifycenter.push.job.core.SendHistoryWorker;
import com.hujiang.notifycenter.push.model.bo.App;
import com.hujiang.notifycenter.push.model.bo.PushByTag;
import com.hujiang.notifycenter.gundam.common.model.constant.ErrorCode;
import com.hujiang.notifycenter.push.model.common.Status;
import com.hujiang.notifycenter.push.model.dto.Audience;
import com.hujiang.notifycenter.push.model.dto.Payload;
import com.hujiang.notifycenter.push.model.po.DeviceInfoPo;
import com.hujiang.notifycenter.push.model.po.PushBreakTaskPo;
import com.hujiang.notifycenter.push.model.po.PushTaskPo;
import com.hujiang.notifycenter.push.model.po.PushTaskSendHistoryPo;
import com.hujiang.notifycenter.push.service.AppService;
import com.hujiang.notifycenter.push.service.DeviceInfoService;
import com.hujiang.notifycenter.push.service.PushBreakTaskService;
import com.hujiang.notifycenter.push.service.PushTaskService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangkai
 * @date 2018/5/7
 * @email yangkai@hujiang.com
 * @description
 */
@Slf4j
@Component
public class PushClassifyProcessor implements Processor<Payload>, Interceptor<Payload> {

    @Autowired
    private DeviceInfoService deviceInfoService;

    @Autowired
    private PushTaskService pushTaskService;

    @Autowired
    private PushBreakTaskService pushBreakTaskService;

    @Autowired
    private AppService appService;

    @Autowired
    private SendHistoryWorker worker;

    @Override
    public void intercept(Payload payload) {
        //防止接入方突然变更APNs token
        App app = payload.getApp();
        app = appService.getByAppKey(app.getAppKey());
        payload.setApp(app);
    }

    @Override
    public void process(Payload payload) {
        intercept(payload);
        App app = payload.getApp();
        Audience audience = (Audience) payload.getAudience();

        if (payload.getAudienceType() == TAG) {
            audience.setAudiences(getAudiencesByTag(payload));
        } else if (payload.getAudienceType() == ALIAS) {
            audience.setAudiences(getAudiencesByAlias(payload));
        } else if (payload.getAudienceType() == TOKEN) {
            audience.setAudiences(getAudiencesByToken(payload));
        } else if (payload.getAudienceType() == ALL) {
            audience.setAudiences(getAudiencesByAll(payload));
        } else if (payload.getAudienceType() == DEVICE) {
            audience.setAudiences(getAudiencesByDevice(payload));
        } else if (payload.getAudienceType() == USER) {
            audience.setAudiences(getAudiencesByUser(payload));
        }

        //更新任务状态
        if (payload.isBreakTask()) {
            pushBreakTaskService.update(new PushBreakTaskPo(payload.getMsgId(), payload.getTaskId(), Status.PROCESSING.status()));
        } else {
            pushTaskService.update(new PushTaskPo(
                payload.getMsgId(),
                Status.PROCESSING.status(),
                app.getAppCategory() + "_" + DEFAULT_SHARDING_SDF.format(new Date())
            ));
        }
    }

    /**
     * 按Device Token推送
     * @param payload
     * @return
     */
    private Set<PushByTag> getAudiencesByToken(Payload payload) {
        Audience audience = (Audience) payload.getAudience();
        return audience.getDeviceToken().stream().map(e -> new PushByTag(null, null, e, null)).collect(Collectors.toSet());
    }

    /**
     * 全量推送
     * @param payload
     * @return
     */
    private Set<PushByTag> getAudiencesByAll(Payload payload) {
        App app = payload.getApp();

        Set<PushByTag> all = payload.getBreakTask().getTags();
        Set<PushByTag> tags0 = all.stream().filter(e -> e.getStatus() == DeviceInfoStatus.NOT_SET.getStatus()).collect(Collectors.toSet());
        Set<PushByTag> tags1 = all.stream().filter(e -> e.getStatus() == DeviceInfoStatus.ALLOW.getStatus()).collect(Collectors.toSet());
        Set<PushByTag> tags2 = all.stream().filter(e -> e.getStatus() == DeviceInfoStatus.CLOSE.getStatus()).collect(Collectors.toSet());

        if (CollectionUtils.isNotEmpty(tags0)) {
            worker.saveAll(tags0.stream().map(e -> {
                PushTaskSendHistoryPo history = new PushTaskSendHistoryPo();
                history.setMsgId(payload.getMsgId());
                history.setAppId(app.getAppId());
                history.setAppCategory(app.getAppCategory());
                history.setAppName(app.getAppName());
                history.setAudienceType(payload.getAudienceType().value());
                history.setContent(JsonUtil.object2JSON(payload.toJSON()));
                history.setUserId(e.getUserId());
                history.setDeviceId(e.getDeviceId());
                history.setDeviceToken(e.getDeviceToken());
                history.setSuccess(false);
                history.setCode(ErrorCode.DISALLOW_NOTIFICATION.getErrCode());
                history.setMsg(ErrorCode.DISALLOW_NOTIFICATION.getErrMsg());
                return history;
            }).collect(Collectors.toSet()));
        }

        if (CollectionUtils.isNotEmpty(tags2)) {
            worker.saveAll(tags2.stream().map(e -> {
                PushTaskSendHistoryPo history = new PushTaskSendHistoryPo();
                history.setMsgId(payload.getMsgId());
                history.setAppId(app.getAppId());
                history.setAppCategory(app.getAppCategory());
                history.setAppName(app.getAppName());
                history.setAudienceType(payload.getAudienceType().value());
                history.setContent(JsonUtil.object2JSON(payload.toJSON()));
                history.setUserId(e.getUserId());
                history.setDeviceId(e.getDeviceId());
                history.setDeviceToken(e.getDeviceToken());
                history.setSuccess(false);
                history.setCode(ErrorCode.CLOSE_NOTIFICATION.getErrCode());
                history.setMsg(ErrorCode.CLOSE_NOTIFICATION.getErrMsg());
                return history;
            }).collect(Collectors.toSet()));
        }

        return tags1;
    }

    /**
     * 按用户ID推送
     * @param payload
     * @return
     */
    @SuppressWarnings("unchecked")
    private Set<PushByTag> getAudiencesByUser(Payload payload) {
        App app = payload.getApp();
        Audience audience = (Audience) payload.getAudience();

        //全部用户ID
        Set<Long> all = audience.getUserId();
        //有效用户ID
        Set<Long> list1 = null;
        //无效用户ID
        Set<Long> list2 = null;
        // status == DeviceInfoStatus.NOT_ALLOW (用户未设置通知开关)用户ID
        Set<DeviceInfoPo> uids0 = null;
        // status == DeviceInfoStatus.ALLOW (用户允许通知)用户ID
        Set<DeviceInfoPo> uids1 = null;
        // status == DeviceInfoStatus.CLOSE (用户关闭通知)用户ID
        Set<DeviceInfoPo> uids2 = null;
        // status == DeviceInfoStatus.LOGOUT (用户退出登录)用户ID
        Set<DeviceInfoPo> uids3 = null;
        List<DeviceInfoPo> uids = deviceInfoService.getDeviceInfoListByUids(payload.getApp().getIosBundleId(), all);
        if (CollectionUtils.isNotEmpty(uids)) {
            list1 = uids.stream().map(DeviceInfoPo::getUserId).collect(Collectors.toSet());
            list2 = Sets.newHashSet(CollectionUtils.subtract(all, list1));

            uids0 = uids.stream().filter(e -> e.getStatus() == DeviceInfoStatus.NOT_SET.getStatus()).collect(Collectors.toSet());
            uids1 = uids.stream().filter(e -> e.getStatus() == DeviceInfoStatus.ALLOW.getStatus()).collect(Collectors.toSet());
            uids2 = uids.stream().filter(e -> e.getStatus() == DeviceInfoStatus.CLOSE.getStatus()).collect(Collectors.toSet());
            uids3 = uids.stream().filter(e -> e.getStatus() == DeviceInfoStatus.LOGOUT.getStatus()).collect(Collectors.toSet());
        } else {
            list2 = Sets.newHashSet(all);
        }

        if (CollectionUtils.isNotEmpty(list2)) {
            worker.saveAll(list2.stream().map(e -> {
                PushTaskSendHistoryPo history = new PushTaskSendHistoryPo();
                history.setMsgId(payload.getMsgId());
                history.setAppId(app.getAppId());
                history.setAppCategory(app.getAppCategory());
                history.setAppName(app.getAppName());
                history.setAudienceType(payload.getAudienceType().value());
                history.setContent(JsonUtil.object2JSON(payload.toJSON()));
                history.setUserId(e);
                history.setSuccess(false);
                history.setCode(ErrorCode.INVALID_USER.getErrCode());
                history.setMsg(ErrorCode.INVALID_USER.getErrMsg());
                return history;
            }).collect(Collectors.toSet()));
        }

        if (CollectionUtils.isNotEmpty(uids0)) {
            worker.saveAll(uids0.stream().map(e -> {
                PushTaskSendHistoryPo history = new PushTaskSendHistoryPo();
                history.setMsgId(payload.getMsgId());
                history.setAppId(app.getAppId());
                history.setAppCategory(app.getAppCategory());
                history.setAppName(app.getAppName());
                history.setAudienceType(payload.getAudienceType().value());
                history.setContent(JsonUtil.object2JSON(payload.toJSON()));
                history.setUserId(e.getUserId());
                history.setDeviceId(e.getDeviceId());
                history.setDeviceToken(e.getPushToken());
                history.setSuccess(false);
                history.setCode(ErrorCode.DISALLOW_NOTIFICATION.getErrCode());
                history.setMsg(ErrorCode.DISALLOW_NOTIFICATION.getErrMsg());
                return history;
            }).collect(Collectors.toSet()));
        }

        if (CollectionUtils.isNotEmpty(uids2)) {
            worker.saveAll(uids2.stream().map(e -> {
                PushTaskSendHistoryPo history = new PushTaskSendHistoryPo();
                history.setMsgId(payload.getMsgId());
                history.setAppId(app.getAppId());
                history.setAppCategory(app.getAppCategory());
                history.setAppName(app.getAppName());
                history.setAudienceType(payload.getAudienceType().value());
                history.setContent(JsonUtil.object2JSON(payload.toJSON()));
                history.setUserId(e.getUserId());
                history.setDeviceId(e.getDeviceId());
                history.setDeviceToken(e.getPushToken());
                history.setSuccess(false);
                history.setCode(ErrorCode.CLOSE_NOTIFICATION.getErrCode());
                history.setMsg(ErrorCode.CLOSE_NOTIFICATION.getErrMsg());
                return history;
            }).collect(Collectors.toSet()));
        }

        if (CollectionUtils.isNotEmpty(uids3)) {
            worker.saveAll(uids3.stream().map(e -> {
                PushTaskSendHistoryPo history = new PushTaskSendHistoryPo();
                history.setMsgId(payload.getMsgId());
                history.setAppId(app.getAppId());
                history.setAppCategory(app.getAppCategory());
                history.setAppName(app.getAppName());
                history.setAudienceType(payload.getAudienceType().value());
                history.setContent(JsonUtil.object2JSON(payload.toJSON()));
                history.setUserId(e.getUserId());
                history.setDeviceId(e.getDeviceId());
                history.setDeviceToken(e.getPushToken());
                history.setSuccess(false);
                history.setCode(ErrorCode.USER_LOGOUT.getErrCode());
                history.setMsg(ErrorCode.USER_LOGOUT.getErrMsg());
                return history;
            }).collect(Collectors.toSet()));
        }

        return CollectionUtils.isNotEmpty(uids1) ? uids1.stream().map(PushByTag::new).collect(Collectors.toSet()) : null;
    }

    /**
     * 根据设备ID推送
     * @param payload
     * @return
     */
    @SuppressWarnings("unchecked")
    private Set<PushByTag> getAudiencesByDevice(Payload payload) {
        App app = payload.getApp();
        Audience audience = (Audience) payload.getAudience();

        //所有设备ID
        Set<String> all = audience.getDeviceId();
        //有效设备ID
        Set<String> list1 = null;
        //无效设备ID
        Set<String> list2 = null;
        // status == DeviceInfoStatus.NOT_SET (用户未设置通知开关)的设备ID
        Set<DeviceInfoPo> dids0 = null;
        // status == DeviceInfoStatus.ALLOW (用户允许通知)的设备ID
        Set<DeviceInfoPo> dids1 = null;
        // status == DeviceInfoStatus.CLOSE (用户关闭通知)的设备ID
        Set<DeviceInfoPo> dids2 = null;
        List<DeviceInfoPo> dids = deviceInfoService.getDeviceInfoListByDids(payload.getApp().getIosBundleId(), all);
        if (CollectionUtils.isNotEmpty(dids)) {
            list1 = dids.stream().map(DeviceInfoPo::getDeviceId).collect(Collectors.toSet());
            list2 = Sets.newHashSet(CollectionUtils.subtract(all, list1));

            dids0 = dids.stream().filter(e -> e.getStatus() == DeviceInfoStatus.NOT_SET.getStatus()).collect(Collectors.toSet());
            dids1 = dids.stream().filter(e -> e.getStatus() == DeviceInfoStatus.ALLOW.getStatus()).collect(Collectors.toSet());
            dids2 = dids.stream().filter(e -> e.getStatus() == DeviceInfoStatus.CLOSE.getStatus()).collect(Collectors.toSet());
        } else {
            list2 = Sets.newHashSet(all);
        }

        if (CollectionUtils.isNotEmpty(list2)) {
            worker.saveAll(list2.stream().map(e -> {
                PushTaskSendHistoryPo history = new PushTaskSendHistoryPo();
                history.setMsgId(payload.getMsgId());
                history.setAppId(app.getAppId());
                history.setAppCategory(app.getAppCategory());
                history.setAppName(app.getAppName());
                history.setAudienceType(payload.getAudienceType().value());
                history.setContent(JsonUtil.object2JSON(payload.toJSON()));
                history.setDeviceId(e);
                history.setSuccess(false);
                history.setCode(ErrorCode.INVALID_DEVICE.getErrCode());
                history.setMsg(ErrorCode.INVALID_DEVICE.getErrMsg());
                return history;
            }).collect(Collectors.toSet()));
        }

        if (CollectionUtils.isNotEmpty(dids0)) {
            worker.saveAll(dids0.stream().map(e -> {
                PushTaskSendHistoryPo history = new PushTaskSendHistoryPo();
                history.setMsgId(payload.getMsgId());
                history.setAppId(app.getAppId());
                history.setAppCategory(app.getAppCategory());
                history.setAppName(app.getAppName());
                history.setAudienceType(payload.getAudienceType().value());
                history.setContent(JsonUtil.object2JSON(payload.toJSON()));
                history.setUserId(e.getUserId());
                history.setDeviceId(e.getDeviceId());
                history.setDeviceToken(e.getPushToken());
                history.setSuccess(false);
                history.setCode(ErrorCode.DISALLOW_NOTIFICATION.getErrCode());
                history.setMsg(ErrorCode.DISALLOW_NOTIFICATION.getErrMsg());
                return history;
            }).collect(Collectors.toSet()));
        }

        if (CollectionUtils.isNotEmpty(dids2)) {
            worker.saveAll(dids2.stream().map(e -> {
                PushTaskSendHistoryPo history = new PushTaskSendHistoryPo();
                history.setMsgId(payload.getMsgId());
                history.setAppId(app.getAppId());
                history.setAppCategory(app.getAppCategory());
                history.setAppName(app.getAppName());
                history.setAudienceType(payload.getAudienceType().value());
                history.setContent(JsonUtil.object2JSON(payload.toJSON()));
                history.setUserId(e.getUserId());
                history.setDeviceId(e.getDeviceId());
                history.setDeviceToken(e.getPushToken());
                history.setSuccess(false);
                history.setCode(ErrorCode.CLOSE_NOTIFICATION.getErrCode());
                history.setMsg(ErrorCode.CLOSE_NOTIFICATION.getErrMsg());
                return history;
            }).collect(Collectors.toSet()));
        }

        return CollectionUtils.isNotEmpty(dids1) ? dids1.stream().map(PushByTag::new).collect(Collectors.toSet()) : null;
    }

    /**
     * 根据tag推送
     * @param payload
     * @return
     */
    @SuppressWarnings("unchecked")
    private Set<PushByTag> getAudiencesByTag(Payload payload) {
        //TODO
        log.warn("unsupported send type, audienceType: {}, data: {}", payload.getAudienceType().value(), payload);
        throw new HJPushException(ResponseCodeEnum.UNSUPPORTED_AUDIENCE_TYPE
            .getStatus(), ResponseCodeEnum.UNSUPPORTED_AUDIENCE_TYPE.getMsg());
    }

    /**
     * 根据设备ID推送
     * @param payload
     * @return
     */
    @SuppressWarnings("unchecked")
    private Set<PushByTag> getAudiencesByAlias(Payload payload) {
        //TODO
        log.warn("unsupported send type, audienceType: {}, data: {}", payload.getAudienceType().value(), payload);
        throw new HJPushException(ResponseCodeEnum.UNSUPPORTED_AUDIENCE_TYPE
            .getStatus(), ResponseCodeEnum.UNSUPPORTED_AUDIENCE_TYPE.getMsg());
    }

}
