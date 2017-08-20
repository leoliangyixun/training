package com.yangkai.proxy.test1;

import java.lang.reflect.Proxy;

public class MyProxyFactory {
	public MyProxyFactory(){
		
	}
	public static Object getProxyInstance(Object target){
		MyInvocationHandler handler=new MyInvocationHandler(target);
		//return Proxy.newProxyInstance(target.getClass().getClassLoader(), new Class<?>[]{target.getClass()}, handler);
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
	}
}
