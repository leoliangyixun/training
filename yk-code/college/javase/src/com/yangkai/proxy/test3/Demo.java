package com.yangkai.proxy.test3;

import java.lang.reflect.Proxy;

public class Demo {

	public static void main(String[] args) {
		Foo f1=new FooImpl1();
		Foo f2=new FooImpl2();
		MyInvocationHandler handler=new MyInvocationHandler();
		handler.setTarget(f1);
		Foo f=(Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(), new Class<?>[]{Foo.class}, handler);
		f.doInAction();
		handler.setTarget(f2);
		f=(Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(), new Class<?>[]{Foo.class}, handler);
		f.doInAction();
	}

}
