package com.yangkai.proxy.test2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Vector;

public class Test {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<String> realList=new Vector<String>();
		List<String> list=(List<String>) DynamicProxyFactory.getProxy(realList);
		list.add("Ñî¿ª");
		list.add("¸¶´ä");
		list.add("¸ßÔ²Ô²");
	}
}
class MyInvocationHandler2 implements InvocationHandler{
	private Object obj;
	public MyInvocationHandler2(Object obj){
		this.obj=obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if(args!=null){
			for(Object arg:args){
				System.out.println(arg);
			}
		}
		return method.invoke(obj, args);
	}
	
}
class DynamicProxyFactory{
	public static Object getProxy(Object obj){
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new MyInvocationHandler2(obj));
	}
}