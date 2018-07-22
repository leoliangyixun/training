package push3.interceptor;

import com.hujiang.basic.framework.core.util.JsonUtil;
import com.hujiang.notifycenter.push.core.executor.Command;
import com.hujiang.notifycenter.push.model.bo.Audience;
import com.hujiang.notifycenter.push.model.bo.IosAlert;
import com.hujiang.notifycenter.push.model.bo.Payload;

import com.alibaba.fastjson.TypeReference;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author yangkai
 * @date 2018/4/15
 * @email yangkai@hujiang.com
 * @version
 * @description
 */
@Slf4j
@Component
public class PushConvertInterceptor extends CommandInvoker<Payload> {

    @Override
    public void execute(Payload payload, Command<Payload> command) {

        if (!(payload.getPlatform() instanceof String)) {
            payload.setPlatform(
                JsonUtil.json2Reference(JsonUtil.object2JSON(payload.getPlatform()), new TypeReference<Set<String>>(){}));
        }

        if (!(payload.getAudience() instanceof String)) {
            payload.setPlatform(JsonUtil.json2Object(JsonUtil.object2JSON(payload.getAudience()), Audience.class));
        }

        if (!(payload.getNotification().getAlert() instanceof String)) {
            payload.getNotification().setAlert(
                JsonUtil.json2Object(JsonUtil.object2JSON(payload.getNotification().getAlert()), IosAlert.class));
        }

        System.out.println("PushConvertInterceptor before" );
        super.execute(payload, command);
        System.out.println("PushConvertInterceptor after");
    }
}
