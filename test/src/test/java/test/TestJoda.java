package test;

import com.hujiang.basic.framework.core.util.DateUtil;
import com.training.Utils;
import org.joda.time.*;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

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

}
