package test;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author yangkai
 * @date 2019-01-05
 * @email yangkai@hujiang.com
 * @description
 */
public class TestJdk8Date {

    @Test
    public void test1() {
        LocalTime.now();
    }

    @Test
    public void test2() {
      //  DateTime dt3 = new DateTime(new Date());
    }


    @Test
    public void test3() {
        long timestamp = Duration.between(LocalDateTime.now(), LocalDateTime.of(1970, 1, 1, 0, 0, 0)).getSeconds();

        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = LocalDateTime.of(from.getYear(), from.getMonth(), from.getDayOfMonth(), 23, 59, 59);
        long seconds = Duration.between(from, to).getSeconds();


        Date lastLogin = Date.from(LocalDateTime.of(0001, 01, 01, 00, 00, 00).atZone(ZoneId.systemDefault()).toInstant());

    }

    @Test
    public void test4() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse("2013-12-31 01:59:59", formatter);
        int hour = ldt.getHour();
        int second = ldt.getSecond();
        System.out.println(hour);
        System.out.println(second);

    }

    @Test
    public void test_day_starttime_and_endtime() {
        LocalDate now = LocalDate.now();
        LocalDateTime start = LocalDateTime.of(now, LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(now.plusDays(1), LocalTime.MAX);

        System.out.println(start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

}
