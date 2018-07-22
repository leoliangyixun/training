package push2.model.dto;

import com.hujiang.basic.framework.core.util.JsonUtil;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class Options implements Serializable {
    @JSONField(name = "time_to_live")
    private Integer timeToLive;
    @JSONField(name = "apns_production")
    private Boolean apnsProduction = true;

    @Override
    public String toString() {
        return JsonUtil.object2JSON(this);
    }

}
