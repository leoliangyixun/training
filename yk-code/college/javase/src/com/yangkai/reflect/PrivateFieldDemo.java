package com.yangkai.reflect;

import java.lang.reflect.Field;

public class PrivateFieldDemo {
	public static void main(String[] args) {
		try {
			Class<PrivateField> classType=PrivateField.class;
			Object obj=classType.newInstance();
			Field f=classType.getDeclaredField("name");
			f.setAccessible(true);
			f.set(obj,"lavender");
			String str=(String)f.get(obj);
			System.out.println(str);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}

}
