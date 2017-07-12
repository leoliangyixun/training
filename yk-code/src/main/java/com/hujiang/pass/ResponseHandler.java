package com.hujiang.pass;

import com.hujiang.pass.support.model.dto.finance.res.BaseFinanceRes;

/**
 * Created by yangkai on 2017/6/5.
 */
public interface ResponseHandler<T extends BaseFinanceRes<E>, E> {
    T handle();
   // <T extends BaseFinanceRes<E>, E> T handle();
   // <T extends BaseFinanceRes<? extends Object>> T handle();
   // <T extends BaseFinanceRes<? extends Object>> T handle();
   // <T extends BaseFinanceRes<?>> T exec();
}
