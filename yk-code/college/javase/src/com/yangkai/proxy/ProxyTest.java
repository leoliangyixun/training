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
		p.eat("�з", "ţ��");
		p.walk();
		p.say("Hello World");

	}
}

class MyInvocationHandlerTest implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("����ִ�еķ���:" + method);
		if (args != null) {
			System.out.println("�����ǵ��ø÷���ʱ����Ĳ�����");
			System.out.println("�����ĸ�����" + args.length);
			for (Object arg : args) {
				System.out.println(arg);
			}
		} else {
			System.out.println("���ø÷���ʱû�д������!!!");
		}
		// method.invoke(obj, args);
		return null;
	}
}