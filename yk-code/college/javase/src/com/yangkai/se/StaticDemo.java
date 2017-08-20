package com.yangkai.se;
public class StaticDemo{
	public static void main(String[] args)
	{
		
	}
} 
class A{
	public static int i=6;
}
class B{
	public static void main(String[] args)
	{
		A b=new A();
		b.i++;
		System.out.println(b.i);
	}
}
class C{
	public static void main(String[] args)
	{
		A c=new A();
		System.out.println(c.i);
	}
} 