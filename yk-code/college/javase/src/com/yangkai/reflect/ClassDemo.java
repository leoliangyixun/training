package com.yangkai.reflect;

public class ClassDemo {

	public ClassDemo() {
		
	}

	public void test()
	{
		System.out.println("This is a test method in ClassDemo!!!");
	}
	public static void main(String[] args) {
		Class c1=Boolean.TYPE;
		Class c2=Boolean.class;
		System.out.println(c1);
		System.out.println(c2);
	}

}
