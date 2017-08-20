package com.yangkai.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateDemo {

	public StringToDateDemo() {
		
	}


	public static void main(String[] args) 
	{
		String date="1988-10-15";
		try {
			Date d=DateFormat.getDateInstance().parse(date);
//			System.out.println(d.getTime());
			java.sql.Date d1=new java.sql.Date(d.getTime());
			System.out.println(d1);
//			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
//			System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(d));
//			System.out.println(new SimpleDateFormat("HH:mm:ss").format(d));
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
