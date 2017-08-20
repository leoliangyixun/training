package com.yangkai.test;

public class StringDemo2 {

	public static void main(String[] args) 
	{
		String a="123";
		String b="123";
		byte[] d={'x','y','z'};
		String c=new String("123");
		System.out.println(a==b);
		System.out.println(c);
		System.out.println(a==c);
		System.out.println(d[1]);
	}

}
