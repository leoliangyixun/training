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
public enum SplashType {
    CENTER(1, "居中"), FULL_SCREEN(2, "全屏");

    protected int code;
    protected String value;

    private SplashType(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }

    public static SplashType get(int code) {

        for(SplashType type : values()){
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
