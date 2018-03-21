/**
 * 
 */
package com.training.springmvc.model.vo;

import java.io.Serializable;
import java.util.Map;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年4月28日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月28日       jiqingchuan          1.0             Why & What is modified
 */
public class Msg implements Serializable {

    private static final long serialVersionUID = 5088293040263002266L;
    
    private String code;
    private boolean ok;
    private String msg;
    private Map<String, Object> data;
    
    public Msg() {
        
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the ok
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * @param ok the ok to set
     */
    public void setOk(boolean ok) {
        this.ok = ok;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the data
     */
    public Map<String, Object> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Map<String, Object> data) {
        this.data = data;
    }
    



}
