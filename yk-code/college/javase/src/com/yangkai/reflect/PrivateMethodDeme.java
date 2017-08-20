package com.yangkai.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PrivateMethodDeme {

	public static void main(String[] args) {
		PrivateMethod pm=new PrivateMethod();
		Class<PrivateMethod> classType=PrivateMethod.class;
		try {
			Method m=classType.getDeclaredMethod("test", new Class<?>[]{});
			m.setAccessible(true);
			m.invoke(pm, new Object[]{});
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

}
