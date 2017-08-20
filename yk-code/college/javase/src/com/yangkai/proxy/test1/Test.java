package com.yangkai.proxy.test1;


public class Test {

	public static void main(String[] args) {
		Dog target=new DogImpl();
		Dog dog=(Dog) MyProxyFactory.getProxyInstance(target);
		dog.info();
		dog.run();
	}

}
