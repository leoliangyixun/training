package com.training.yk.model.hj.dsp;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.basic.framework.core.exception.AppException;

public enum SiteType {

    PC(1,"PC"), TOUCH(2,"触屏’"), APP(3,"APP");
    protected int code;
    protected String value;

    private SiteType(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }

    public static SiteType get(int code) {

        for(SiteType type : values()){
            if(type.code == code){
                return type;
            }
        }

        throw new AppException(-1, "not support enum type");
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("value", value);
        return json;
    }
}
