package com.yangkai.se;

public class SingleDemo {

	public SingleDemo() {
		
	}

	public static void main(String[] args) 
	{
		System.out.println(Single.getInstance());

	}

}
class Single{
	private static Single s=new Single();
	private Single()
	{
		
	}
	public static Single getInstance()
	{
		return s;
	}
}
