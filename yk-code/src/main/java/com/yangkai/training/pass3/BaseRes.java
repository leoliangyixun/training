/**
 * 
 */
package com.training.pass3;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author yangkai
 *
 */
@Data
@NoArgsConstructor
public class BaseRes<T> implements Serializable {
    
    private static final long serialVersionUID = -6292020789541018618L;
    protected int code;
    protected int status;
    protected String message;
    protected T data;

    public void setStatus(int status) {
        this.status = status;
        this.code = status;
    }

    public int getCode() {
        return status;
    }

    public BaseRes(int status, String message, T data) {
        this.status = status;
        this.code = status;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseRes<T> fail(int status, String message, T data) {
        return new BaseRes<T>(status, message, data);
    }
    
    public static <T> BaseRes<T> fail(int status, String message) {
        return new BaseRes<T>(status, message, null);
    }

    public static <T> BaseRes<T> ok() {
        return new BaseRes<T>(0, null, null);
    }

    public static <T> BaseRes<T> ok(T data) {
        return new BaseRes<T>(0, null, data);
    }

    public static <T> BaseRes<T> ok(int status, String message, T data) {
        return new BaseRes<T>(status, message, data);
    }

    public BaseRes<T> toUserCenterStandardRes() {
        return new BaseRes<T>(status, message, data);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
