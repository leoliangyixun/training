/**
 * 
 */
package com.training.validator.demo;


import com.training.validator.common.Caller;
import com.training.validator.common.ValidatorContext;
import com.training.validator.model.Message;

/**
 * @author yangkai
 *
 */
public class MessageValidator2 implements Validator2<Message> {

	@Override
	public boolean accept(ValidatorContext context, Message t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	@Override
	public void onException(ValidatorContext context, Exception e, Message t) {
		// TODO Auto-generated method stub
		
	}

	//attention: 无法指定C的具体类型,需要调用方指定
	@Override
	public <C> boolean validate(ValidatorContext context, Message t, Caller<C> caller) {
		// TODO Auto-generated method stub
		return false;
	}


}
