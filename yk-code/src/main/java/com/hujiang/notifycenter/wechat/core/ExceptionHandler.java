package com.hujiang.notifycenter.wechat.core;

/**
 * Created by yangkai on 2017/7/20.
 */
public interface ExceptionHandler<T> {
    void handle(T obj, Exception e);
}
