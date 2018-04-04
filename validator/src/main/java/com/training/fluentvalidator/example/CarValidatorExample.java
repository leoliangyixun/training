/**
 * 
 */
package com.training.fluentvalidator.example;

import com.baidu.unbiz.fluentvalidator.*;
import com.training.fluentvalidator.validator.CarSeatCountValidator;
import com.training.validator.model.Car;

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
public class CarValidatorExample {


	public CarValidatorExample() {
		// TODO Auto-generated constructor stub
	}


	public static void main(String[] args) {
		
			Car car = new Car();
			car.setSeatCount(3);
			ComplexResult result =  FluentValidator.checkAll()
					.on(car.getSeatCount(), new CarSeatCountValidator())
					.doValidate()
					.result(ResultCollectors.toComplex());
			
			System.out.println(result);
	}

}