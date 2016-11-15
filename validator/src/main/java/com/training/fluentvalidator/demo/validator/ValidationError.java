package com.training.fluentvalidator.demo.validator;

public class ValidationError {


    private String errorMsg;


    private String field;

    private int errorCode;


    private Object invalidValue;

    @Override
    public String toString() {
        return "ValidationError{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", field='" + field + '\'' +
                ", invalidValue=" + invalidValue +
                '}';
    }


    public static ValidationError create(String errorMsg) {
        return new ValidationError().setErrorMsg(errorMsg);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public ValidationError setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public ValidationError setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public String getField() {
        return field;
    }

    public ValidationError setField(String field) {
        this.field = field;
        return this;
    }

    public Object getInvalidValue() {
        return invalidValue;
    }

    public ValidationError setInvalidValue(Object invalidValue) {
        this.invalidValue = invalidValue;
        return this;
    }

}
