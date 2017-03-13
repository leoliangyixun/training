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
public enum AdTypeEnum {
	
    PC(1,"PC"), TOUCH(2,"触屏’"), APP(3,"APP");
	
	private String name;
	private int siteType;
    private int code;
    

    private AdTypeEnum(String name, int siteType, int code) {
    	this.name = name;
        this.siteType = siteType;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public static AdTypeEnum get(int code) {

        for(AdTypeEnum type : values()){
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
