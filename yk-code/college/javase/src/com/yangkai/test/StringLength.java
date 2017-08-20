package com.yangkai.test;

public class StringLength {

	public static void main(String[] args) {
		String s1="";
		String s2="a";
		String s3=null;
		System.out.println(s1.length());
		System.out.println(s2.length());
		
		System.out.println(s3 instanceof String);
		String[] s={s3};
		System.out.println(s.length);
	}

}
