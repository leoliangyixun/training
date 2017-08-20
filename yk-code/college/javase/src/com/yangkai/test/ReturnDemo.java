package com.yangkai.test;
public class ReturnDemo {

	public static void main(String[] args) {
		ReturnDemo rd=new ReturnDemo();
		//rd.test();
		//System.out.println("fuck you");
		for(int i=0;i<10;i++)
		{
			if(i==3)
			{
				return;
				//break;
			}
			System.out.println(i);
		}
		

	}
	public void test()
	{
		System.out.println("fuck");
		//return;
		System.exit(0);
		
	}
}
