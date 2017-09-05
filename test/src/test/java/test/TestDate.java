/**
 * //TODO
 */
package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import com.hujiang.basic.framework.core.util.DateUtil;
import org.joda.time.*;
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
    
    @Test
    public void testFormat() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    	System.out.println(sdf.format(new Date()));
    }
    
    @Test
    public void test() {
/*        Date now = new Date();
        System.out.println(new SimpleDateFormat("yyyy年MM月dd日HH:mm").format(now));*/
        int minute = 30;
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
        c.add(Calendar.MINUTE, -minute);
        Date end = c.getTime();
        c.add(Calendar.MINUTE, -minute);
        Date start = c.getTime();
        System.out.println(date);
        System.out.println(end);
        System.out.println(start);

    }

    @Test
    public void test2() {
        long cur = System.currentTimeMillis();

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        int s = (int)((c.getTimeInMillis() - cur) / 1000);
        System.out.println(s);
        System.out.println("-----------------------------------------------------------");
        Calendar c2 = Calendar.getInstance();
        Calendar tommorowDate = new GregorianCalendar(c2
                .get(Calendar.YEAR), c.get(Calendar.MONTH), c2
                .get(Calendar.DATE) + 1, 0, 0, 0);
        int re = (int)(tommorowDate.getTimeInMillis() - c2 .getTimeInMillis()) / 1000;
        System.out.println(re);
    }

    @Test
    public void test3() {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Date d1 = c.getTime();
        System.out.println(d1);
/*        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        Date d2 = c.getTime();
        System.out.println(d2);

        Date now = new Date();
        System.out.println(now);
        System.out.println(now.after(d1));
        System.out.println(now.before(d2));*/

    }
    
    @Test
    public void testJdk8Date() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
 /*       System.out.println(now.getYear());
        System.out.println(now.getMonth());
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());*/
        //System.out.println(now.toEpochSecond(ZoneOffset.UTC));
        //Date d = Date.from(Instant.from(now));
        ;
/*        Date d = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(d);//Instant.ofEpochMilli*/
  /*      System.out.println(Duration.between(now, end).getSeconds());
        System.out.println(Duration.between(now, end).toHours());*/

/*        Period p = Period.between(now.toLocalDate(), end.toLocalDate());
        System.out.println(p.getYears() + " years " +
                p.getMonths() + " months " +
                p.getDays() + " days ");*/

        Duration d = Duration.between(now, end);
        System.out.println(d.getSeconds());
        System.out.println(LocalDateTime.from(end).until(now, ChronoUnit.SECONDS));


    }

    @Test
    public void testJdk8Date2() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime t1 = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 00, 00, 00);
        LocalDateTime t2 = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 00, 01, 01);

        Duration d = Duration.between(t1, t2);
        System.out.println(d.getSeconds());
    }

    @Test
    public void testJdk8Date3() {
//        DateTime.MaxValue:9999/12/31 23:59:59
//        DateTime.MinValue:0001/1/1 0:00:00
        LocalDateTime ldt = LocalDateTime.of(0001, 01, 01, 00, 00, 00);
        //LocalDateTime ldt = LocalDateTime.now();
        Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
        Date d = Date.from(instant);
        System.out.println(d);
    }

    @Test
    public void testTimeUnit() {
        long a = TimeUnit.SECONDS.toMillis(1) ;   // 1秒转换为毫秒数
        long b = TimeUnit.SECONDS.toMinutes(60) ; // 60秒转换为分钟数
       // TimeUnit.SECONDS.sleep(5);  //线程休眠5秒
        long c = TimeUnit.SECONDS.convert(20, TimeUnit.MINUTES); //1分钟转换为秒数
        long d = TimeUnit.MINUTES.convert(30, TimeUnit.SECONDS);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }

    @Test
    public void testLocalDateTime() {
        long seconds = Duration.between(LocalDateTime.of(2017, 6, 21, 14, 50, 00), LocalDateTime.now()).getSeconds();
        System.out.println(seconds);
    }

    @Test
    public void testDateUtil() {
        Date d = DateUtil.toDate("1900-01-01T21:00:00");
        System.out.println(d.toString());
        String s = DateUtil.toDateString(d, DateUtil.DEFAULT_DATE_PATTERN);
        System.out.println(s);

    }

    @Test
    public void testDateUtil2() {

        System.out.println(DateUtil.toDateTimeString(new Date()));

    }

    @Test
    public void testJdk8Date4() throws Exception{

        LocalDateTime from = LocalDateTime.now();
        Thread.sleep(100);
       // LocalDateTime to = LocalDateTime.of(from.getYear(), from.getMonth(), from.getDayOfMonth(), 23, 59, 59);
        LocalDateTime to = LocalDateTime.now();
        long seconds = Duration.between(from, to).getSeconds();
        long milliseconds = Duration.between(from, to).toMillis();
        System.out.println(seconds);
        System.out.println(milliseconds);
    }
    
    @Test
    public void format() {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ssssss").format(new Date()));
        System.out.println(new SimpleDateFormat("yyyyMM").format(new Date()));
        System.out.println(new SimpleDateFormat("yyyy-MM").format(new Date()));
    }


}
