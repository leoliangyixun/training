/**
 * //TODO
 */
package test;

import com.hujiang.basic.framework.core.util.DateUtil;

import com.google.common.collect.Sets;

import org.assertj.core.util.Lists;
import org.joda.time.DateTime;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

    @Test
    public void testEpoch() {
       long seconds =  Instant.now().getEpochSecond();
        System.out.println(seconds);
        Date date = new Date(seconds * 1000L);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
    }

    @Test
    public void test4() {
        long seconds =  Instant.now().getEpochSecond();
        Date date1 = new Date(seconds * 1000L);
        System.out.println(date1);
        Date date2 = Calendar.getInstance().getTime();
        System.out.println(date1.getTime());
        System.out.println(date2.getTime());

    }


    @Test
    public void test5() {
        Date expire = DateUtil.toDateTime("2017-10-21 08:30:30");
        Date now = DateUtil.toDateTime("2017-10-21 08:30:31");
        System.out.println(expire);
        System.out.println(new Date());

        System.out.println(expire.compareTo(now));
    }

    @Test
    public void test6() {
        Date date = Date.from(LocalDateTime.of(1900, 1, 1, 0, 0, 59)
                .atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(date);
    }

    @Test
    public void test7() {
        Date d = new Date();
        Date d2 = Date.from(LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault()).plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(d2);
        Date d3 = Date.from(LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault()).plusDays(30).atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(d3);
    }

    @Test
    public void test8() {
        Date date1 = DateUtil.toDateTime("2017-10-22 16:30:30");
        Date date2 = new Date();
        Date date3 = DateUtil.toDateTime("2017-10-22 16:30:30");
        System.out.println(date1.getTime());
        System.out.println(date2.getTime());
        System.out.println(date3.getTime());
        System.out.println(date1.compareTo(date3));
        Date d = Calendar.getInstance().getTime();
        System.out.println(d.getTime());
    }

    @Test
    public void test9() {
        Date d = new Date();
        Date d1 = Calendar.getInstance().getTime();
        Date d2 =  DateUtil.toDateTime(DateUtil.toDateString(d1, DateUtil.DEFAULT_DATETIME_PATTERN));
        System.out.println(d1.getTime());
        System.out.println(d2.getTime());
        System.out.println(d1.compareTo(d2));
    }

    @Test
    public void testLocalDateTime2Seconds() {
        long seconds = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println(seconds);
        seconds = Instant.now().toEpochMilli();
        System.out.println(seconds);



    }

    @Test
    public void testDate2Seconds() {
        //Instant.now().toEpochMilli();
        Date date = new Date();
        long milli = date.toInstant().toEpochMilli();
        System.out.println(milli);
        long seconds = date.toInstant().getEpochSecond();
        System.out.println(seconds);
        System.out.println(date.getTime());//  seconds != date.getTime()

    }

    @Test
    public void testDate2LocalDateTime() {
        Date date = new Date();
    }

    @Test
    public void testDateCompare() {
        Date date1 = DateUtil.toDateTime("2017-10-21 08:30:30");
        Date date2 = DateUtil.toDateTime("2017-10-21 08:30:30");
        System.out.println(date1.before(date2));
        System.out.println(date1.compareTo(date2));
        Date now = new Date();
        System.out.println(DateUtil.toDateString(now, DateUtil.DEFAULT_DATETIME_PATTERN));
        System.out.println(DateUtil.toDateString(now, DateUtil.DEFAULT_DATEDETAIL_PATTERN));
    }

    @Test
    public void testTimestamp2Date_1() {
        Date d = new Date(1L);
        System.out.println(d);

    }

    @Test
    public void testTimestamp2Date() {
        System.out.println(new Date(1560877667338L));
        System.out.println(new Date(1560877678663L));
    }

    @Test
    public void testTimestamp2Date2() {
        Date date = new Date();
        Date d1 = new Date(date.getTime() + 3000);
        Date d2 = new Date(date.getTime() + 2000);
        Date d3 = new Date(date.getTime() + 1000);
        Date d4 = new Date(date.getTime() + 5000);
        Date d5 = new Date(date.getTime() + 4000);
        List<Date> list = Lists.newArrayList(d1, d2, d3,d4,d5);
        System.out.println(list);
        list.sort((e1, e2) ->
            e2.compareTo(e1)
        );
        System.out.println(list);
    }

    @Test
    public void test_date_util() {
        Date from = DateUtil.toDate("2020-02-01", "yyyy-MM-dd");
        Date to = DateUtil.toDate("2020-02-14", "yyyy-MM-dd");
        System.out.println(from.getTime());
        System.out.println(to.getTime());

    }

    @Test
    public void test_date_compare() throws Exception {
        Date from = new Date();
        Thread.sleep(100);
        Date to = new Date();
        System.out.println(to.after(from));

    }

    @Test
    public void test_date_opt1() throws Exception {
        Date date1 = new Date();
        Thread.sleep(10);
        Date date2 = new Date();
        Set<Long> list = Sets.newHashSet();
        for (int i = 0; i < 20; i++) {
//            new Thread(() -> {
//                Date now1 = new DateTime(date1).withSecondOfMinute(0).toDate();
//                Date fromTime = new DateTime(date1).withSecondOfMinute(0).minusMinutes(3).toDate();
//                Date toTime = new DateTime(date1).withSecondOfMinute(0).plusMinutes(3).toDate();
////                System.out.println(Thread.currentThread().getName() + ": " + Utils.dateFormat(now1, "yyyy-MM-dd HH:mm:ss"));
////                System.out.println(Thread.currentThread().getName() + ": " + Utils.dateFormat(fromTime, "yyyy-MM-dd HH:mm:ss"));
////                System.out.println(Thread.currentThread().getName() + ": " + Utils.dateFormat(toTime, "yyyy-MM-dd HH:mm:ss"));
//                System.out.println(Thread.currentThread().getName() + ": " +now1.getTime());
////                System.out.println(Thread.currentThread().getName() + ": " + Utils.md5Hex("test001" + fromTime.getTime() + toTime.getTime()));
////                System.out.println(Thread.currentThread().getName() + ": " + Utils.md5Hex("test001" + fromTime.getTime() + toTime.getTime()));
////                System.out.println(Thread.currentThread().getName() + ": " + Utils.md5Hex("test001" + fromTime.getTime() + toTime.getTime()));
//            }).start();


//            Date now1 = new DateTime(date1).withSecondOfMinute(0).toDate();
//            Date now2 = new DateTime(date2).withSecondOfMinute(0).toDate();
            Date now1 = new DateTime(date1).withSecondOfMinute(0).withMillisOfSecond(0).toDate();
            Date now2 = new DateTime(date2).withSecondOfMinute(0).withMillisOfSecond(0).toDate();
            list.add(now1.getTime());
            list.add(now2.getTime());
        }
        System.out.println(list);

    }

    @Test
    public void 计算下班时间() throws Exception {
        String OnTimeString= "2021-12-30 11:34:14";
        Date onTime = DateUtil.toDateTime(OnTimeString);
        Date offTime = new DateTime(onTime).plusHours(8).plusMinutes(30).toDate();
        System.out.println(DateUtil.toDateString(offTime, DateUtil.DEFAULT_DATETIME_PATTERN));
    }

}
