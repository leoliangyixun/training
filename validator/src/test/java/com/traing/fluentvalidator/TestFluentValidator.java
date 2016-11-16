package com.traing.fluentvalidator;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toSimple;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toComplex;
import org.junit.Test;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.Result;
import com.training.fluentvalidator.validator.CarSeatCountValidator;
import com.training.validator.model.Car;

public class TestFluentValidator {
	
	@Test
	public void test() {
		FluentValidator validator = FluentValidator.checkAll();
		validator.on(new Car());
	}
	
	@Test
	public void testSimpleResult() {
		Car car = new Car();
		car.setSeatCount(0);
		Result result =  FluentValidator.checkAll()
				.on(car.getSeatCount(), new CarSeatCountValidator())
				.doValidate()
				.result(toSimple());
		System.out.println(result);
	}
	
	   @Test
	    public void testComplexResult() {
	        Car car = new Car();
	        car.setSeatCount(0);
	        ComplexResult result =  FluentValidator.checkAll()
	                .on(car.getSeatCount(), new CarSeatCountValidator())
	                .doValidate()
	                .result(toComplex());
	        System.out.println(result);
	    }

}
