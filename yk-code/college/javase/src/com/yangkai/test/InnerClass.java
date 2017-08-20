package com.yangkai.test;
public class InnerClass{
	public static void main(String[] args)
	{
		Outer outer=new Outer();
		//outer.getInstance().fuck();
		//Outer.Inner in=new Outer().new Inner();
	}

}
class Outer{
	    private class Inner{
		
		public void fuck()
		{
			System.out.println("fuck");
		}
		public void shit()
		{
			System.out.println("shit");
		}
	}
	
	public Inner getInstance()
	{
		return new Inner();
	}
	public static void main(String[] args)
	{
		Outer.Inner in=new Outer().new Inner();
		in.shit();
		 
	}
}
