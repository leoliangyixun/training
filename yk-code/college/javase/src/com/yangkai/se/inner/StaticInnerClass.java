package com.yangkai.se.inner;

public class StaticInnerClass {
	private static int m=0;
	private int n=0;
	static class InnerClass{
		private int a=10;
		private static int b=20;
		public void innerMethod(){
			System.out.println("member method in StaticInnerClass");
			System.out.println(a);
		}
		public static void staticInnerMethod(){
			System.out.println("static method in StaticInnerClass");
			System.out.println(m);
			System.out.println(n);//Cannot make a static reference to the non-static field n.
			
		}
	}
	public static void main(String[] args) {
		System.out.println(StaticInnerClass.InnerClass.b);
		InnerClass.staticInnerMethod();
		/*
		 * 静态内部类对象可以直接实例化，不需要寄存在外部类实例之中。
		 * */
		InnerClass inner=new InnerClass();
		inner.innerMethod();
	}

}
