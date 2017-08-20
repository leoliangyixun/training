package com.yangkai.test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo4 {
	public static void main(String[] args) 
	{
		Date date=new Date();
		DateFormat d1=DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		System.out.println("DateFormat.SHORT:"+d1.format(date));
		DateFormat d2=DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
		System.out.println("DateFormat.FULL:"+d2.format(date));
		DateFormat d3=DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
		System.out.println("DateFormat.LONG:"+d3.format(date));
		DateFormat d4=DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		System.out.println("DateFormat.MEDIUM:"+d4.format(date));
		SimpleDateFormat d=new SimpleDateFormat();
		
	}

}
