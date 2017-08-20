package com.yangkai.se.inner;

public class MemberInnerClass {
	private int a=0;
	/**
	 * 成员内部类
	 */
	public class InnerClass{
		private static final int n=10;
		private int m=0;
		private void test(){
			System.out.println("fuck");
			System.out.println(a);
		}
	}

	public static void main(String[] args) {
		//MemberInnerClass.InnerClass inner=new MemberInnerClass().new InnerClass();
		InnerClass inner=new MemberInnerClass().new InnerClass();
		/**
		 * 静态方法中不能实例化成员类部类对象。
		 * 可以实例化静态类部类对象。
		 */
		//成员内部类必须寄存在外部类对象之中，也就是说，当存在一个成员内部类对象时，必然存在一个外部类对象。 
		//InnerClass inner2=new InnerClass();
		inner.test();
		MemberInnerClass outer=new MemberInnerClass();
	
		System.out.println(outer.a);
		System.out.println(MemberInnerClass.InnerClass.n);
		
	}

}
