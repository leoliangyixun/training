package com.yangkai.sql;

public class FactoryDemo2 {

	public static FactoryDemo2 fac=new FactoryDemo2();
	private FactoryDemo2() 
	{
		
	}
	public static FactoryDemo2 getFactoryInstance()
	{
		return fac;
	}
	public Single getSingleInstance()
	{
		return new Single();
	}
}
