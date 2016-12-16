/**
 * //TODO
 */
package com.b5m.test.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * @description: TODO
 * Copyright 2011-2015 B5M.COM. All rights reserved.
 * @author Lawrence.Yang
 * @email jiqingchuan@b5m.com
 * @version: 1.0
 * @since 2016年1月25日 下午1:24:30
 * Modification  History:
 * Date         Author               Version         Discription
 * -----------------------------------------------------------------------------------
 * 2016年1月25日      jiqingchuan         1.0             Why & What is modified
 */
public class TestDate {

    /**
     * //TODO
     */
    public TestDate() {
        // TODO Auto-generated constructor stub

    }
    public static void main(String[] args) throws ParseException {
        String s1 = "201601";
        String s2 = "201602";
        String s3 = "201512";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Date d1 = sdf.parse(s1);
/*        Date d2 = sdf.parse(s2);
        Date d3 = sdf.parse(s3);*/
        Date d = new Date();
        String str1 = sdf.format(d1);
/*        String str2 = sdf.format(d2);
        String str3 = sdf.format(d3);*/
        System.out.println(d1); 
/*        System.out.println(str2); 
        System.out.println(str3); */
/*        int a = 1;
        int b = 2;
        System.out.println(a > b);*/
        System.out.println(s3.equals(s2));
        System.out.println(s2.compareTo(s3));
    }
    
    @Test
    public void testDate() {
    	System.out.println(new Date().getTime());
        new Date().getSeconds();
    }
    
    @Test
    public void testInstant() {
/*        Date date= new Date();
        Calendar c = Calendar.getInstance();*/
        Object obj = null;

        String s = (String) null;
        System.out.println(s);

        
    }

}
