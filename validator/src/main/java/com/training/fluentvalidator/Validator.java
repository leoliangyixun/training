package com.training.fluentvalidator;

import com.training.validator.common.Caller;
import com.training.validator.common.ValidatorContext;

public interface Validator<T> {
    boolean accept(ValidatorContext context, T t);

    boolean validate(ValidatorContext context, T t, Caller<T> caller);

    void onException(ValidatorContext context, Exception e, T t);

}
