package com.yangkai.test;

import java.sql.Date;

public class DateDemo3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date d=new Date(new java.util.Date().getTime());
		System.out.println(d.toLocaleString());

	}

}
