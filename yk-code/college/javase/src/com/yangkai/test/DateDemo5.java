package com.yangkai.test;

import java.text.DateFormat;
import java.util.Date;

public class DateDemo5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Date d=new Date();
		System.out.println(d.getDate());
		System.out.println(new java.sql.Date(d.getTime()));
		System.out.println(DateFormat.getDateInstance().format(d));

	}

}
