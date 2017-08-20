package com.yangkai.proxy.test1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
	public Object target;
	public MyInvocationHandler(Object target){
		this.target=target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
		DogUtil du=new DogUtil();
		du.method1();
		Object result=method.invoke(target,args);
		du.method2();
		return result;
	}
}
