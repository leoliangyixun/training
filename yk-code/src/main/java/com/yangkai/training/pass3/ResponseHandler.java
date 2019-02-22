package com.training.pass3;

/**
 * Created by yangkai on 2017/6/5.
 */
public interface ResponseHandler<R, T extends BaseRes<R>> {
    T handle(R r);
}
