package com.yangkai.proxy.test4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicProxyFactory{
	public static Object getProxyInstance(Object obj)
	{
		InvocationHandler handler=new MyInvocationHandler(obj);
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
	}
}