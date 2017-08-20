package com.yangkai.test;
public class ObjectDemo {

	Demo d=new Demo();
	public void test(){
		d.change();
		System.out.println("Hello");
		System.out.println(d);
	}
	public void change(){
		d.change();
		System.out.println("Welcome");
		System.out.println(d);
	}
	public static void main(String[] args) {
		ObjectDemo od=new ObjectDemo();
		od.test();
		od.change();
		
		
	}

}
