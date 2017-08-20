package com.yangkai.se.inner;

public class InnerClassTest {

	public InnerClassTest() {
		
	}
	public String username;
	public class Outputer{
		public void output(String name){
			System.out.println(name);
			System.out.println(username);
		}
	}
	public static class Printer{
		
		public void output(String name){
			System.out.println(name);
		}
	}
	public void test(){
		Outputer outputer=new Outputer();
		this.username="lavender";                          
	}
	public static void main(String[] args) {
		/*
		 * No enclosing instance of type InnerClassTest is accessible.
		 * Outputer outputer=new Outputer();
		 * */		
//		Outputer outputer=new InnerClassTest().new Outputer();
		final Outputer outputer=new InnerClassTest().new Outputer();
//		Printer printer=new Printer();
		
		new Thread(new Runnable() {
			/*
			 * No enclosing instance of type InnerClassTest is accessible.
			 * Outputer outputer=new Outputer();
			 * */	
		
			@Override
			public void run() {
				while(true){
					outputer.output("chengjing");
				}
			}
		}).start();
	}

}
