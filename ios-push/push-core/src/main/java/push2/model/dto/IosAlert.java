package push2.model.dto;

import com.hujiang.basic.framework.core.util.JsonUtil;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * @author: yangkai
 * @date: 2018/4/15
 * @email: yangkai@hujiang.com
 * @version:
 * @description
 */
@Data
public class IosAlert {
    private String title;
    private String subtitle;
    private String body;
    @JSONField(name = com.hujiang.notifycenter.client.model.push.notification.IosAlert.TITLE_LOC_KEY)
    private String titleLocKey;
    @JSONField(name = com.hujiang.notifycenter.client.model.push.notification.IosAlert.TITLE_LOC_ARGS)
    private String[] titleLocArgs;
    @JSONField(name = com.hujiang.notifycenter.client.model.push.notification.IosAlert.ACTION_LOC_KEY)
    private String actionLocKey;
    @JSONField(name = com.hujiang.notifycenter.client.model.push.notification.IosAlert.LOC_KEY)
    private String locKey;
    @JSONField(name = com.hujiang.notifycenter.client.model.push.notification.IosAlert.LOC_ARGS)
    private String[] locArgs;
    @JSONField(name = com.hujiang.notifycenter.client.model.push.notification.IosAlert.LAUNCH_IMAGE)
    private String launchImage;

    @Override
    public String toString() {
        return JsonUtil.object2JSON(this);
    }
}
