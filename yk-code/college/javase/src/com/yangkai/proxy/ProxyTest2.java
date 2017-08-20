package com.yangkai.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Person2{
	public String job(String name,String work);
	public String say(String name,String words);
}
public class ProxyTest2 {
	public static void main(String[] args) {
		MyInvocationHandler2 myHandler2=new MyInvocationHandler2();
		Person2 p2=(Person2) Proxy.newProxyInstance(Person2.class.getClassLoader(), new Class[]{Person2.class}, myHandler2);
		String str1=p2.say("zhangsan", "Hello World");
		System.out.println(str1);
		String str2=p2.job("lisi", "coding");
		System.out.println(str2);
	}
}
class MyInvocationHandler2 implements InvocationHandler{
	Person2 p2=new Person2Impl();
	//Person2 myp2=new MyPerson2Impl();
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)throws Throwable {
		if(method.getName().equals("job")){
			String str="do in Proxy:"+args[0] +"的工作是："+args[1];
			System.out.println(str);
			return null;
		}else{
			return method.invoke(p2, args);
		}
	}
}

class MyPerson2Impl implements Person2{

	@Override
	public String job(String name, String work) {
		return "do in MyPerson2Impl:"+name+"的工作是："+work;
	}

	@Override
	public String say(String name, String words) {
		return "do in MyPerson2Impl:"+name+"说的话是："+words;
	}
	
}

class Person2Impl implements Person2{

	@Override
	public String job(String name, String work) {
		return "do in Person2Impl:"+name+"’ job is："+work;
	}

	@Override
	public String say(String name, String words) {
		return "do in Person2Impl:"+name+" is saying："+words;
	}
}
