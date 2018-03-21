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
public enum TextType {
    NULL(1, "null"), ROW(2, "row");

    protected int code;
    protected String value;

    private TextType(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }

    public static TextType get(int code) {

        for(TextType type : values()){
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
