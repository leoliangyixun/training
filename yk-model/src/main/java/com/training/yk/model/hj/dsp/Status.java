package com.training.yk.model.hj.dsp;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.basic.framework.core.exception.AppException;

public enum Status implements CommonType {

    ACTIVE("启用", 1),
    DELETED("删除", 2),
    INACTIVE("禁用", 3);

    protected String value;
    protected Integer code;

    Status(String value, int code) {
        this.value = value;
        this.code = code;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public static Status get(int code) {
        for (Status status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new AppException(-1, "not support enum type");
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("statusCode", code);
        json.put("status", value);
        return json;
    }
}
