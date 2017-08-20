package com.yangkai.test;

public class SonDemo extends FatherDemo{

	public void getSonMethod()
	{
		System.out.println("son");
	}
	public void getFatherMethod()
	{
		System.out.println("son in father");
	}
	public static void main(String[] args) 
	{
		FatherDemo son1=new SonDemo();
		son1.getFatherMethod();
		SonDemo son2=new SonDemo();
		

	}

}
