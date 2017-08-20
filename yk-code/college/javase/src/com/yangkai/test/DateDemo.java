package com.yangkai.test;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDemo { 

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Date d=new Date();
		/*
		System.out.println(d.getTime());
		System.out.println(d);
		System.out.println(d.getDate());
		System.out.println(d.getDay());
		System.out.println("-------------------------");
		java.sql.Date d1=new java.sql.Date(d.getTime());
		System.out.println(d1);
		System.out.println("-------------------------");
		java.sql.Date d2=new java.sql.Date(d.getYear(),d.getMonth(),d.getDate());
		System.out.println(d2);
		System.out.println("-------------------------");
		Calendar cd=Calendar.getInstance();
		System.out.println();
        */
		SimpleDateFormat df1=new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
		SimpleDateFormat df2=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//SimpleDateFormat df=new SimpleDateFormat();
			System.out.println(df1.format(d));
			System.out.println(df2.format(d));
			//System.out.println(d.toLocaleString());
			System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
	
	}

}
