package test;

import com.hujiang.basic.framework.core.util.DateUtil;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by yangkai on 2017/6/21.
 */
public class TestJoda {

    @Test
    public void testJoda() {
        int days = Days.daysBetween(new LocalDateTime(2017, 06,20, 15, 22 ,00), new LocalDateTime(new Date())).getDays();
        System.out.println(days);
    }

    @Test
    public void testJoda2() {
        LocalDateTime ldt = new LocalDateTime(new Date());
        LocalDateTime ldt2 = new LocalDateTime(2016, 06,20, 15, 22 ,00);
        System.out.println(ldt.getYear());
        System.out.println(ldt2.getYear());

    }

    @Test
    public void testJoda3() {
        LocalDateTime ldt = new LocalDateTime(2016, 06,20, 15, 22 ,00);
        System.out.println(ldt.getMonthOfYear());
        ldt = ldt.plusDays(40);
        System.out.println(ldt.getMonthOfYear());
    }

    @Test
    public void testJoda4() {
        Date d = DateUtil.toDateTime("2017-06-22T16:20:50");
        System.out.println(d);
    }

    @Test
    public void testJoda5() {

        LocalDateTime start = new LocalDateTime(2016, 01,01, 00, 00, 00);
        LocalDateTime end = new LocalDateTime(2016, 01,02, 00, 00, 00);
        int days = Days.daysBetween(end, start.plusDays(180)).getDays();
        System.out.println(days);

    }

    @Test
    public void testJoda6() {
        Date d = DateTime.parse("1900-01-01 21:00:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        System.out.println(d);
        String s = new DateTime(d).toString("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        System.out.println(s);
    }

    @Test
    public void testJoda7() throws Exception {
        Date start = new Date();
        Thread.sleep(200);
        Date end = new Date();
        int seconds = Seconds.secondsBetween(new LocalDateTime(start), new LocalDateTime(end)).getSeconds();

        System.out.println(seconds);
    }


    @Test
    public void testJoda8() throws Exception {
        Date start = new Date();
       Thread.sleep(200);
        Date end = new Date();
      //  Date end = Date.from(java.time.LocalDateTime.of(2017, 7, 23, 21, 40, 0).atZone(ZoneId.systemDefault()).toInstant());
        //Date end = DateUtil.toDateTime("2017-07-23T21:00:00"); //不能识别
       // Date end = DateUtil.toDateTime("2017-07-23 21:00:00");
        int minutes = Minutes.minutesBetween(new LocalDateTime(start), new LocalDateTime(end)).getMinutes();

        System.out.println(minutes);
    }

    @Test
    public void testJoda9() throws Exception {
        Date d = new Date();
        Date d1 = new LocalDateTime(d).toDate();
        Date d2 = new LocalDateTime(d).plusDays(1).toDate();
        Date d3 = new LocalDateTime(d).plusSeconds(1).toDate();
        System.out.println(d1 );
        System.out.println(d2);
        System.out.println(d3);

    }
    @Test
    public void testJoda10() throws Exception {
        System.out.println(new Date().getTime());
        System.out.println(Instant.now().getMillis());
    }

    @Test
    public void testPlus() {
        Date now = new Date();
        System.out.println(now);
        Date date = new LocalDateTime(now).plusDays(30).toDate();
        System.out.println(date);
    }

    @Test
    public void testContain() {
        DateTime now = DateTime.now();
        DateTime start = new DateTime(0000, 1, 1, 9, 30);
        DateTime end = new DateTime(9999, 12, 31, 21, 30);
        Interval interval = new Interval(start, end);
        System.out.println(interval.contains(now));
        System.out.println(start.toDate());
        System.out.println(end.toDate());

    }

    @Test
    public void test() {
        LocalTime now = new LocalTime(new Date());
        System.out.println(now);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        LocalTime lt1 = LocalTime.parse("08:00", formatter);
        LocalTime lt2 = LocalTime.parse("22:00", formatter);
        LocalTime lt3 = LocalTime.parse("08:00", formatter);
        System.out.println(lt1.equals(lt3));
        System.out.println(lt1.compareTo(lt2));
        System.out.println(lt1);
        System.out.println(lt2);
        System.out.println(now.isAfter(lt1));
        System.out.println(now.isBefore(lt2));
    }

    @Test
    public void test11() {
        Date now = new Date();
        Date last = new Date();
        int days = Days.daysBetween(new LocalDateTime(now), new LocalDateTime(last).plusDays(180)).getDays();
        LocalDateTime ldt = new LocalDateTime(now);
        int hour = ldt.getHourOfDay();
        System.out.println(hour);

    }

    @Test
    public void test12() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse("2019-5-21 00:30:30", formatter);
        System.out.println(ldt.getHourOfDay());

        //ldt = new LocalDateTime(new Date());
        System.out.println(ldt.getHourOfDay());
        System.out.println(ldt.getSecondOfMinute());

    }

    @Test
    public void test13() {
        //DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        //DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        //LocalDateTime ldt = new LocalDateTime(new Date());
        LocalDateTime ldt = LocalDateTime.parse("2019-5-09 01:30:30", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(formatter.print(ldt));
    }

    @Test
    public void test14() {
        //DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        //DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        //LocalDateTime ldt = new LocalDateTime(new Date());
        LocalDateTime ldt = LocalDateTime.parse("2019-5-09", DateTimeFormat.forPattern("yyyy-MM-dd"));
        System.out.println(formatter.print(ldt));
        Date date = ldt.toDate();
        //Date date = ldt.toDate(TimeZone.getTimeZone(ZoneId.systemDefault()));
        System.out.println(date);
    }

    @Test
    public void test15() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dt = DateTime.parse("2015-12-21 23:22:45", formatter);
        //System.out.println(dateTime.toDate());
        //System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));
        dt.withMinuteOfHour(1);
        dt.withSecondOfMinute(1);
       // System.out.println(dt.toDate());
        System.out.println(dt.toString("yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void test16() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt1 = LocalDateTime.parse("2015-12-21 00:22:45", formatter);

        LocalDateTime ldt2 = new LocalDateTime(ldt1)
            //.withHourOfDay(5)
            .withMinuteOfHour(0)
            .withSecondOfMinute(0);
        System.out.println(ldt2.toString("yyyy-MM-dd HH:mm:ss"));

        LocalDateTime ldt3 = new LocalDateTime(new Date());
        System.out.println(ldt3.toString("yyyy-MM-dd HH:mm:ss"));

    }

    @Test
    public void test17() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        LocalDateTime ldt1 = LocalDateTime.parse("2015-12-21", formatter);

        LocalDateTime ldt2 = new LocalDateTime(ldt1)
            .withHourOfDay(5)
            .withMinuteOfHour(0)
            .withSecondOfMinute(0);
        System.out.println(ldt2.toString("yyyy-MM-dd-HH-mm-ss"));
    }


    @Test
    public void test18() {
        LocalDateTime from = LocalDateTime.parse("2019-07-15 00:00:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime to = LocalDateTime.parse("2019-07-17 23:59:59", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime time = LocalDateTime.parse("2019-07-18 00:00:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        long t1 = from.toDate().getTime();
        long t2 = to.toDate().getTime();
        long t3 = time.toDate().getTime();
        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);


        System.out.println((t2 - t1) /  (60 * 60 * 1000));
        System.out.println((t3 - t1) /  (60 * 60 * 1000));
        System.out.println(t2 - t1);
        System.out.println(t3 - t1);
        System.out.println(t3 - t2);
    }

    @Test
    public void test_datetimeformat() {
        Date date = null;
        DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");
        System.out.println(FORMATTER.print(new LocalDateTime(date)));
        //System.out.println(LocalDateTime.parse("2019-05-21", FORMATTER).toDate());
    }

    @Test
    public void testJavaDate2JodaDate() {
        Date now = new Date();
        DateTime dateTime = new DateTime(now);
        Date date1 = dateTime.withTimeAtStartOfDay().toDate();
        Date date2 = dateTime.plusDays(1).withTimeAtStartOfDay().toDate();
        System.out.println(dateTime.dayOfMonth().withMaximumValue().plusDays(1).withTimeAtStartOfDay().toDate());
    }

    @Test
    public void testJavaDate2JodaDate2(){
        System.out.println(DateTime.now().withDayOfMonth(1).withTimeAtStartOfDay().toDate());
        System.out.println(DateTime.now().dayOfMonth().withMaximumValue().plusDays(1).withTimeAtStartOfDay().toDate());
        System.out.println(DateTime.now().dayOfMonth().withMinimumValue().toDate());
        System.out.println(DateTime.now().dayOfMonth().withMinimumValue().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).toDate());
        System.out.println(DateTime.now().dayOfMonth().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toDate());

        System.out.println(DateTime.now().minusDays(1).withTimeAtStartOfDay().toDate());
        System.out.println(DateTime.now().withDayOfMonth(1).withTimeAtStartOfDay().toDate());
    }

    @Test
    public void testJavaDate2JodaDate3(){
        Date now = new Date();
        System.out.println(new DateTime(now).dayOfMonth().withMinimumValue().withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).toDate());
        System.out.println(new DateTime(now).dayOfMonth().withMaximumValue().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toDate());

    }

    @Test
    public void test2() {
        Date date1 = DateTime.now().minusDays(1).withTimeAtStartOfDay().toDate();
        Date date2 = DateTime.now().withDayOfMonth(1).withTimeAtStartOfDay().toDate();
        Date date3 =DateTime.now().toDate();
        System.out.println(DateUtil.toDateString(date1, "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtil.toDateString(date2, "yyyy-MM-dd HH:mm:ss"));
        System.out.println(DateUtil.toDateString(date3, "yyyy-MM-dd HH:mm:ss"));
    }

}
