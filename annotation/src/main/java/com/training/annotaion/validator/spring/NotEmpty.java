package com.training.annotaion.validator.spring;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.training.annotaion.validator.spring.NotEmptyValidator;

//Should base on spring validator
@Documented
@Constraint(validatedBy = {NotEmptyValidator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.FIELD})
public @interface NotEmpty {

    String message() default "kidding";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
