package org.yangkai.jdbc;

public class ConstructorDemo {

	/*
	//所有包都可以构造。
	public ConstructorDemo() {
		
	}
	*/
	/*
	//包内可以构造，包外不能构造。
	protected ConstructorDemo() {
		
	}
	*/
	/*
	//只能在本类的内部构造。
	private ConstructorDemo() {
		
	}
	*/
	
	ConstructorDemo() {
		
	}
    
	public static void main(String[] args) {
		ConstructorDemo cons=new ConstructorDemo();
	}
}
