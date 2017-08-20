package com.yangkai.proxy.test2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test2 {

	public Test2() {
		
	}

	public static void main(String[] args) {
		Subject realSubject=new RealSubject();
		InvocationHandler handler=new MyInvocationHandler(realSubject);
		Subject subject=(Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[]{Subject.class}, handler);
		subject.show();
		System.out.println(subject);
		System.out.println(subject.getClass().getSimpleName());
		System.out.println(subject instanceof Subject);
	}

}
