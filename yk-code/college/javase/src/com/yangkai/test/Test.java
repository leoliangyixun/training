package com.yangkai.test;

public class Test {
	public int i=9;
	public static void main(String[] args) 
	{
		Test t=new Test();
		t.getNum(t.i);

	}
	public void getNum(int i)
	{
		System.out.println(i);
	}
}
