package com.yangkai.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.yangkai.vo.Account;
import com.yangkai.vo.User;

public class ReflectDemo {

	public ReflectDemo() {
		
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object reflectTest(Class clazz)
	{
		Object obj=null;
		/*
		try {
			obj=clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		*/

		try {
			Constructor con1=clazz.getConstructor(String.class);
			
			obj=con1.newInstance("leo");
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	public static void main(String[] args) {
		
		User user=(User) reflectTest(User.class);
		Account account=(Account)reflectTest(Account.class);
		System.out.println(user);
		System.out.println(account);
	}

}
