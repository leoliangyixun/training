package com.yangkai.sql;

public class FactoryDemoTest {

	public static void main(String[] args) 
	{
		//FactoryDemo fac=new FactoryDemo();
		//FactoryDemo fac=FactoryDemo
		//Single s=new Single();
		
		Single s1=FactoryDemo.getSingleInstance();
		System.out.println(s1);
		Single s2=FactoryDemo.getSingleInstance();
		System.out.println(s2);
		
		FactoryDemo2 fac1=FactoryDemo2.getFactoryInstance();
		System.out.println(fac1.getSingleInstance());
		FactoryDemo2 fac2=FactoryDemo2.getFactoryInstance();
		System.out.println(fac2.getSingleInstance());
	}

}
