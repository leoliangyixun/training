package com.yangkai.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Person {
	void eat(String food1, String food2);

	void walk();

	void say(String name);
}

public class ProxyTest {

	public static void main(String[] args) {
		MyInvocationHandlerTest myHandler = new MyInvocationHandlerTest();
		Person p = (Person) Proxy.newProxyInstance(
				Person.class.getClassLoader(), new Class[] { Person.class },
				myHandler);
		p.eat("螃蟹", "牛肉");
		p.walk();
		p.say("Hello World");

	}
}

class MyInvocationHandlerTest implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("正在执行的方法:" + method);
		if (args != null) {
			System.out.println("下面是调用该方法时传入的参数：");
			System.out.println("参数的个数：" + args.length);
			for (Object arg : args) {
				System.out.println(arg);
			}
		} else {
			System.out.println("调用该方法时没有传入参数!!!");
		}
		// method.invoke(obj, args);
		return null;
	}
}