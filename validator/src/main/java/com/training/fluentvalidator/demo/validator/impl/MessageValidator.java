/**
 * 
 */
package com.training.fluentvalidator.demo.validator.impl;

import com.training.fluentvalidator.demo.validator.Caller;
import com.training.fluentvalidator.demo.validator.Validator;
import com.training.fluentvalidator.demo.validator.ValidatorContext;
import com.training.validator.model.Message;

/**
 * @author yangkai
 *
 */
public class MessageValidator implements Validator<Message> {

	@Override
	public boolean accept(ValidatorContext context, Message t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validate(ValidatorContext context, Message t, Caller<Message> caller) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onException(ValidatorContext context, Exception e, Message t) {
		// TODO Auto-generated method stub
		
	}

}
