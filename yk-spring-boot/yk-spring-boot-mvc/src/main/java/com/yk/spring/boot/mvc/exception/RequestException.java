package com.yk.spring.boot.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by yangkai on 2016/10/23.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private int errorCode;
	
	public RequestException(int errorCode, String msg) {
		super(msg);
		this.errorCode = errorCode;
	}

	public RequestException(int errorCode, String msg, Throwable cause) {
		super(msg, cause);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
	
	
	
	
    
}
