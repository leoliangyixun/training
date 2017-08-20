package com.yangkai.proxy.test4;
public class Test{
	public static void main(String[] args)
	{
		Dog target1=new DogImpl();
		Dog dog=(Dog)DynamicProxyFactory.getProxyInstance(target1);
		dog.run();
		Cat target2=new CatImpl();
		Cat cat=(Cat)DynamicProxyFactory.getProxyInstance(target2);
		cat.run();
	}
}

