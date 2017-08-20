package com.yangkai.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;

public class ProxyDemo {

	public static void main(String[] args) {
		//Class proxyClass=Proxy.getProxyClass(Animal.class.getClassLoader(), Animal.class,Plant.class);
		//µÈ¼ÛÓÚ
		//Class proxyClass=Proxy.getProxyClass(Animal.class.getClassLoader(),new Class[]{Animal.class,Plant.class});
		Class proxyClass=Proxy.getProxyClass(Animal.class.getClassLoader(), Animal.class);
		
		
	}

}
interface Animal{
	
}
interface Plant{
	
}
class Cat implements Animal{
	
}