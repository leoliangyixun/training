package com.yangkai.test;

public class StringSplitDemo {

	public static void main(String[] args) {
	String a="192.168.16.1";
	String[] b=a.split("\\.");
	for(int i=0;i<b.length;i++)
	{
		System.out.println(b[i]);
	}

	}

}
