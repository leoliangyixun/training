package com.training.ykgwj.model;


/**
 * Used to return a uniform response to caller.
 * @author dengchao
 *
 */

public class Msg {
	
	int status;
	boolean ok;
	String message;
	Object data;
	
	public static Msg ok(Object data){
		Msg msg = new Msg();
		msg.setStatus(200);
		msg.setOk(true);
		msg.setData(data);
		return msg;
	}
	
	public static Msg fail(String message, int status){
		Msg msg = new Msg();
		msg.setStatus(status);
		msg.setMessage(message);
		msg.setOk(false);
		return msg;
	}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
	
	
}
