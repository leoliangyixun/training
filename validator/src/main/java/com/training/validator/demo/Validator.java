package com.training.validator.demo;

import com.training.validator.common.Caller;
import com.training.validator.common.ValidatorContext;

public interface Validator<T,C> {
    boolean accept(ValidatorContext context, T t);

    boolean validate(ValidatorContext context, T t, Caller<C> caller);

    void onException(ValidatorContext context, Exception e, T t);

}
