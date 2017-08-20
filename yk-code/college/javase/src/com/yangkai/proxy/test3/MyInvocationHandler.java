package com.yangkai.proxy.test3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
	public Object target;
	public MyInvocationHandler(){
	
	}
	public MyInvocationHandler(Object target){
		this.target=target;
	}
	public void setTarget(Object target){
		this.target=target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
		return method.invoke(target, args);
	}
}
