package com.training.fluentvalidator.demo.validator;

import java.util.HashMap;
import java.util.Map;

public class ValidatorContext {


    private Map<String, Object> contextData;

    public ValidationResult result;

    public void addErrorMsg(String msg) {
        result.addError(ValidationError.create(msg));
    }

    public void addError(ValidationError validationError) {
        result.addError(validationError);
    }

    public Object getAttribute(String key) {
        if (contextData != null && !contextData.isEmpty()) {
            return contextData.get(key);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
	public  <V> V getAttribute(String key, Class<V> clazz) {
        return (V)getAttribute(key);
    }

    public void addContextData(String key, Object value) {
        if (contextData == null) {
        	contextData = new HashMap<String, Object>();
        }
        contextData.put(key, value);
    }

    public void setResult(ValidationResult result) {
        this.result = result;
    }
}
