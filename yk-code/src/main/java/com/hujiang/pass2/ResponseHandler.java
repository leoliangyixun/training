package com.hujiang.pass.account.config;

import com.hujiang.pass.support.model.dto.res.BaseRes;

/**
 * Created by yangkai on 2017/6/5.
 */
public interface ResponseHandler<R, T extends BaseRes<?>> {
    T handle(R r);
}
