package com.yangkai.test;

public class StringDemo3 {
	public static void main(String[] args) 
	{
		String str1="abc";
		String str2="abc";
		System.out.println("str1==str2:"+(str1==str2));
		System.out.println("str1 equals str2:"+(str1.equals(str2)));
		System.out.println("-------------------------------------");
		String str3=new String("abc");
		String str4=new String("abc");
		System.out.println("str3==str4:"+(str3==str4));
		System.out.println("str3 equals str4:"+(str3.equals(str4)));
		System.out.println("-------------------------------------");
		String str5="abc";
		String str6=new String("abc");
		System.out.println("str5==str6:"+(str5==str6));
		System.out.println("str5 equals str6:"+(str5.equals(str6)));
		System.out.println("-------------------------------------");
		Demo1 d1=new Demo1();
		Demo1 d2=new Demo1();
		System.out.println("d1==d2:"+(d1==d2));
		System.out.println("d1 equals d2:"+(d1.equals(d2)));
		System.out.println("-------------------------------------");
		Object obj1=new Object();
		Object obj2=new Object();
		System.out.println("obj1==obj2:"+(obj1==obj2));
		System.out.println("obj1 equals obj2:"+(obj1.equals(obj2)));
		System.out.println("-------------------------------------");
		
	}

}
class Demo1{
	
}
