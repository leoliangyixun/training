/**
 * 
 */
package com.training.yk.model.hj.dsp;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.basic.framework.core.exception.AppException;

/**
 * @author yangkai
 *
 */
public enum CustomerType {
    SELF(1, "自营"), AGENCY(2, "机构");

    protected int code;
    protected String value;

    private CustomerType(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }

    public static CustomerType get(int code) {

        for(CustomerType type : values()){
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
