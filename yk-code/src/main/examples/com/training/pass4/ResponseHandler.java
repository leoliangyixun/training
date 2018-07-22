package com.training.pass4;

import com.hujiang.pass.support.model.dto.res.BaseRes;

/**
 * Created by yangkai on 2017/6/5.
 */
public interface ResponseHandler<U, T extends BaseRes<?>> {
     T handle(U r);
}
