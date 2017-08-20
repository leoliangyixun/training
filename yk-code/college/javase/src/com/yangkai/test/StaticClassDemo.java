package com.yangkai.test;

public class StaticClassDemo {

	private static String name=null;

	public static void setName(String name)
	{
		StaticClassDemo.name=name;
	}
	public static void main(String[] args) 
	{
		/**
		 * demo1,demo2是两个不同的对象。
		 */
		 StaticClassDemo demo1=new StaticClassDemo();
		 demo1.setName("leo");
		 System.out.println(demo1.name);
		 System.out.println(demo1);
		 StaticClassDemo demo2=new StaticClassDemo();
		 demo2.setName("lavender");
		 System.out.println(demo2.name);
		 System.out.println(demo1.name);
		 System.out.println(demo2);
	}

}
