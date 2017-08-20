package com.yangkai.proxy.test4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler{
	public Object obj;
	public MyInvocationHandler(Object obj)
	{
		this.obj=obj;
	}
	public Object invoke(Object proxy, Method method, Object[] args) 
	{
		System.out.println("fuck you");
		Object result=null;
		try {
//			result= method.invoke(obj);
			result= method.invoke(obj,args);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}

}