/**
 * 
 */
package com.training.validator.demo;


import java.util.List;

import com.training.validator.common.Caller;
import com.training.validator.common.ValidatorContext;
import com.training.validator.model.Message;
import com.training.validator.model.SysUser;

/**
 * @author yangkai
 *
 */
public class MessageValidator implements Validator<Message, List<SysUser>> {

	@Override
	public boolean accept(ValidatorContext context, Message t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validate(ValidatorContext context, Message t, Caller<List<SysUser>> caller) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onException(ValidatorContext context, Exception e, Message t) {
		// TODO Auto-generated method stub
		
	}

}
