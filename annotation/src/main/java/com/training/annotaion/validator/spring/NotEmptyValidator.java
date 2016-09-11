package com.training.annotaion.validator.spring;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.training.annotaion.validator.aop.NotEmpty;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {

    @Override
    public void initialize(NotEmpty arg0) {
       
        
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        return value != null && StringUtils.isNotEmpty(value);
    }

}
