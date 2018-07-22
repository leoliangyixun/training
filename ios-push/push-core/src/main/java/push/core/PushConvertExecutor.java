package push.core;

import com.hujiang.basic.framework.core.util.JsonUtil;
import com.hujiang.notifycenter.push.model.dto.Audience;
import com.hujiang.notifycenter.push.model.dto.IosAlert;
import com.hujiang.notifycenter.push.model.dto.Payload;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author: yangkai
 * @date: 2018/4/15
 * @email: yangkai@hujiang.com
 * @version:
 * @description
 */
public class PushConvertExecutor extends AbstractPushExecutor<Payload> {

    @Override
    public void execute(Payload payload) {

        if (!(payload.getPlatform() instanceof String)) {
            payload.setPlatform(JsonUtil.json2Reference(JsonUtil.object2JSON(payload.getPlatform()), new TypeReference<Set<String>>(){}));
        }

        if (!(payload.getAudience() instanceof String)) {
            payload.setPlatform(JsonUtil.json2Object(JsonUtil.object2JSON(payload.getAudience()), Audience.class));
        }

        if (!(payload.getNotification().getAlert() instanceof String)) {
            payload.getNotification().setAlert(JsonUtil.json2Object(JsonUtil.object2JSON(payload.getNotification().getAlert()), IosAlert.class));
        }

        if (this.next != null) {
            this.next.execute(payload);
        }
    }

    public static void main(String[] args) {
        JSONArray array = new JSONArray();
        array.add("ios");
        array.add("android");
        Set<String> platform = Sets.newHashSet("ios", "android");
        System.out.println(JsonUtil.object2JSON(array));

        platform = JsonUtil.json2Reference(JsonUtil.object2JSON(array), new TypeReference<Set<String>>(){});
        System.out.println(platform);
    }
}
