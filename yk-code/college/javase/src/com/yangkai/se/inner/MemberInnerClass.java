package com.yangkai.se.inner;

public class MemberInnerClass {
	private int a=0;
	/**
	 * ��Ա�ڲ���
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
		 * ��̬�����в���ʵ������Ա�ಿ�����
		 * ����ʵ������̬�ಿ�����
		 */
		//��Ա�ڲ������Ĵ����ⲿ�����֮�У�Ҳ����˵��������һ����Ա�ڲ������ʱ����Ȼ����һ���ⲿ����� 
		//InnerClass inner2=new InnerClass();
		inner.test();
		MemberInnerClass outer=new MemberInnerClass();
	
		System.out.println(outer.a);
		System.out.println(MemberInnerClass.InnerClass.n);
		
	}

}
