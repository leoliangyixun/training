package com.yk.spring.boot.mvc.result;

import java.io.Serializable;

/**
 * Created by yangkai on 2016/10/23.
 */
public class Msg implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int code;
    private boolean ok;
    private String message;
    private Object data;

    public Msg() {}

    public Msg(int code, boolean ok, String message, Object data) {
        this.ok = ok;
        this.data = data;
        this.code = code;
        this.message = message;
    }


    public static Msg fail(int code, String message, Object msg) {
        return new Msg(code, false, message, msg);
    }

    public static  Msg fail(String message) {
        return new Msg(-1, false, message, null);
    }

    public static Msg fail(int code, String message) {
        return new Msg(code, false, message, null);
    }

    public static Msg ok() {
        return new Msg(200, true, "success", null);
    }

    public static Msg ok(Object data) {
        return new Msg(200, true, "success", data);
    }

    public static Msg ok(String message, Object data) {
        return new Msg(200, true, message, data);
    }

    @SuppressWarnings("unchecked")
    public <T extends Object>  T getData() {
        return (T)data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return "Msg{" + "code =" + code + ", message =" + message + ", data ='" + data + '}';
    }
}
