package com.yangkai.reflect;

public class ClassLoaderDemo {

	public static void main(String[] args) {

		try {
//			Class<?> clazz1 = Class.forName("ClassDemo");
//			System.out.println(clazz1);
			Class<?> clazz2=Class.forName("com.mysql.jdbc.Driver");
			System.out.println(clazz2);
			Class<?> clazz3=Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println(clazz3);
//			Class<?> clazz4=Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
//			System.out.println(clazz4);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
