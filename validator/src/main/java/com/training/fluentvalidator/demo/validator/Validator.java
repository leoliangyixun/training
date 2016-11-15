package com.training.fluentvalidator.demo.validator;


public interface Validator<T> {
    boolean accept(ValidatorContext context, T t);

    boolean validate(ValidatorContext context, T t, Caller<T> caller);

    void onException(ValidatorContext context, Exception e, T t);

}
