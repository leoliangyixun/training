package com.yangkai.generic;

import java.util.ArrayList;
import java.util.List;

public class GenericDemo {

	public GenericDemo() {
		
	}

	public static <T> T change(T t)
	{
		return t;
	}
	public static void main(String[] args) 
	{
		//List<?> a=new ArrayList<?>();//不能这样定义。
		Demo d=new Demo();
		Test t=new Test();
		System.out.println(change(d).getClass());
		System.out.println(change(t).getClass());
		
	}

}
class Demo{	
}
class Test{	
}
