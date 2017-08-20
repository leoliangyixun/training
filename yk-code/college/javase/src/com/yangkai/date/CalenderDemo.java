package com.yangkai.date;

import java.util.Calendar;
import java.util.Date;

public class CalenderDemo {


	public static void main(String[] args) 
	{
		Date d=new Date();
		
		System.out.println(d);
		//System.out.println(Calendar.DAY_OF_YEAR);
		java.sql.Date d1=new java.sql.Date(d.getTime());
		System.out.println(d1);

	}

}
