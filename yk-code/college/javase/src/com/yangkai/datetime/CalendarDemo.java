package com.yangkai.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class CalendarDemo {

	public CalendarDemo() {
		
	}
	public static void main(String[] args) {
		Calendar cal=Calendar.getInstance();
		Date d1=cal.getTime();
		System.out.println("d1:"+d1);
		
		Date d=new Date();
		System.out.println("d:"+d);
		
		String str="1988-10-15";
		try {
			Date d2=DateFormat.getDateInstance().parse(str);
			System.out.println("d2:"+d2);
			System.out.println("d2 format:"+DateFormat.getDateInstance().format(d2));
			System.out.println(cal.getTimeInMillis());
			System.out.println(cal.get(Calendar.YEAR));
			System.out.println(d.getYear()-d2.getYear());
			System.out.println(d.getMonth()+1);
			System.out.println(d2.getMonth()+1);
			
			System.out.println(cal.get(Calendar.YEAR));
			cal.setTime(d2);
			System.out.println(cal.get(Calendar.YEAR));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
