/**
 * 
 */
package com.training.fluentvalidator.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年4月14日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月14日       jiqingchuan          1.0             Why & What is modified
 */
public class CarSeatCountValidator extends ValidatorHandler<Integer> implements Validator<Integer> {

	@Override
    public boolean validate(ValidatorContext context, Integer t) {
        if (t < 2) {
            //context.addErrorMsg(String.format("Seat count is not valid, invalid value=%s", t));
            context.addError(ValidationError.create("invalid seat")
                    .setErrorCode(50000)
                    .setField("seat")
                    .setInvalidValue(t));
            return false;
        }
        return true;
    }

}
