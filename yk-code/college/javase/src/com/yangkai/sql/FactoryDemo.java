package com.yangkai.sql;
class Single{
	String name=null;
}
public class FactoryDemo {

	public static Single s1=new Single();
	private FactoryDemo() 
	{
		
	}
	
	public static Single getSingleInstance() 
	{
		return s1;
	}
	public static void main(String[] args)
	{
		/*
		 * fac1,fac2两个不同对象。
		 */
		/*
		FactoryDemo fac1=new FactoryDemo();
		FactoryDemo fac2=new FactoryDemo();
		System.out.println(fac1);
		System.out.println(fac2);
		*/
		/*
		FactoryDemo fac1=new FactoryDemo();
		System.out.println(fac1.getSingleInstance());
		FactoryDemo fac2=new FactoryDemo();
		System.out.println(fac2.getSingleInstance());
		*/
		/*
		System.out.println(FactoryDemo.getSingleInstance());
		System.out.println(FactoryDemo.getSingleInstance());
		*/
	}

}
