package com.yangkai.proxy.test2;

public class RealSubject implements Subject {

	public RealSubject() {
		
	}

	@Override
	public void show() {
		System.out.println("in RealSubject!!!");
	}

}
