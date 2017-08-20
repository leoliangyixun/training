package com.yangkai.test;

public class StringDemo1 {


	public static void main(String[] args) {
		char[] c={'I',' ','l','o','v','e',' ','y','o','u'};
		int len=c.length;
		if(len>5)
		{
			len=5;
		}
		String C=new String(c,0,len);
		System.out.println(C);
		System.out.println(C.length());

	}

}
