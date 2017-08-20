package com.yangkai.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo2 {

	public static void main(String[] args) {
	Date  d=new Date();
	System.out.println(d);
	java.sql.Date d1=new java.sql.Date(d.getTime());
	System.out.println(d1);
	SimpleDateFormat sd1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sd2=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	System.out.println(sd1.format(d));
	System.out.println(sd2.format(d));
	}

}
