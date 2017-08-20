package com.yangkai.test;

public class ClassDemoTest {

	public void getClassName(ClassDemo cd)
	{
		System.out.println(cd.getClass());
		System.out.println(cd.getClass().getSimpleName());
		
	}
	public static void main(String[] args) 
	{
		ClassDemoTest c1=new ClassDemoTest();
		ClassDemo cd=new ClassDemo();
		c1.getClassName(cd);

	}

}
