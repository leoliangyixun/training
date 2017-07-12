package com.hujiang.notifycenter.wechat;

/**
 * Created by yangkai on 2016/11/25.
 */
public interface Executor<T> {

    void execute(T obj);
}
