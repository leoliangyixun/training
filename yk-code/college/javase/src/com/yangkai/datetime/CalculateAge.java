package com.yangkai.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class CalculateAge {

	public static void main(String[] args) {
		try {
			int age=0;
			Date d1=new Date();
			String str="1988-10-15";
			Date d2=DateFormat.getDateInstance().parse(str);
			Calendar cal1=Calendar.getInstance();
			Calendar cal2=Calendar.getInstance();
			cal1.setTime(d1);
			cal2.setTime(d2);
			if(cal1.get(Calendar.MONTH)<cal2.get(Calendar.MONTH)){
				age=cal1.get(Calendar.YEAR)-cal2.get(Calendar.YEAR)-1;
			}else{
				age=cal1.get(Calendar.YEAR)-cal2.get(Calendar.YEAR);
			}
			System.out.println(age);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
