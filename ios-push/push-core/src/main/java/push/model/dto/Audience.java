package push.model.dto;

import com.hujiang.basic.framework.core.util.JsonUtil;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class Audience implements Serializable {

    @JSONField(name = "user_id")
    private Set<Long> userId;
    @JSONField(name = "device_id")
    private Set<String> deviceId;
    @JSONField(name = "device_token")
    private Set<String> deviceToken;
    @JSONField(name = "tag")
    private Set<String> tag;
    @JSONField(name = "tag_and")
    private Set<String> tagAnd;
    @JSONField(name = "tag_not")
    private Set<String> tagNot;
    @JSONField(name = "alias")
    private Set<String> alias;

    @Override
    public String toString() {
        return JsonUtil.object2JSON(this);
    }
}
