package com.yangkai.test;

public class AbstractSubClassDemo extends AbstractClassDemo {

	public AbstractSubClassDemo() 
	{
		
	}

	
	public static void main(String[] args) 
	{
		AbstractSubClassDemo demo1=new AbstractSubClassDemo();
		demo1.getFatherMethod();
		demo1.getSubClassMethod1();

	}


	
	public void getSubClassMethod1() 
	{
		
		this.getFatherMethod();
		super.getFatherMethod();
	}
	public void getSubClassMethod2() 
	{
		
		
	}

}
