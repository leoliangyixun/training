package com.yangkai.se.inner;

public class InnerClassDemo {

	public InnerClassDemo() {
		// TODO Auto-generated constructor stub
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public class MemberInnerClass{
		public int a=0;
		/*
		 * The field b cannot be declared static,
		 * static fields can only be declared in static or top level types.
		 * */
		public static int b=0;
		public static final int c=0;
	}
	public static class StaticInnerClass{
		public int x=0;
		public static int y=0;
	}
	public void test(){
		class LocalInnerClass{
			int m=0;
			/*
			 * The field n cannot be declared static,
			 * static fields can only be declared in static or top level types
			 * */
			static int n=0;
			final static int n1=0;
		}
	}
}
