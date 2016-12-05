package com.training.validator.demo;

import com.training.validator.common.Caller;
import com.training.validator.common.ValidatorContext;

public interface Validator2<T> {
    boolean accept(ValidatorContext context, T t);

    <C> boolean validate(ValidatorContext context, T t, Caller<C> caller);

    void onException(ValidatorContext context, Exception e, T t);

}
