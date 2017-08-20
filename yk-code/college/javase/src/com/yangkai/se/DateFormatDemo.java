package com.yangkai.se;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
public class DateFormatDemo {

	public static void main(String[] args)
	{
		Date date = null;
		DateFormat df=DateFormat.getDateTimeInstance();
		try {
			date=df.parse("1988-10-15 10:30:30");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(date);
		String d=df.format(date);
		System.out.println(d);
		Date date2=new Date();
		System.out.println(date2);
		System.out.println(df.format(date2));
	}

}
