package com.yangkai.model.adapter;

public class InitializationDemo {
	private Initialization demo1=new Initialization();
	private Initialization demo2;
	{System.out.println(demo1);}
	public InitializationDemo() {
		System.out.println("in Constructor:"+demo1);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		demo2=new Initialization();
		System.out.println(demo2);
		
	}

	public static void main(String[] args) {
		InitializationDemo demo=new InitializationDemo();
		
		
		
	}
}
