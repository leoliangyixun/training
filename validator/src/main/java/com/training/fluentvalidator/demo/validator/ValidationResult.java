package com.training.fluentvalidator.demo.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class ValidationResult {

    private boolean success = true;

    private List<ValidationError> errors;

    private int timeElapsed;

    public void addError(ValidationError error) {
        if (CollectionUtils.isEmpty(errors)) {
            errors = new ArrayList<ValidationError>();
        }
        errors.add(error);
    }

    public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<ValidationError> getErrors() {
        return errors;
    }

    public int getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(int timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}
