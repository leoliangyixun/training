package test;

import com.hujiang.basic.framework.core.util.DateUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangkai on 2017/10/15.
 */
public class TestLong {

    @Test
    public void test() {
        Date date = new Date();
        date = DateUtil.toDateTime("9999-12-30 23:59:59");
        System.out.println(date.getTime());

        System.out.println( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        System.out.println( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(date));
        System.out.println( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date));
        System.out.println( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").format(date));
    }

    @Test
    public void test2() {
        Long a = 64L;
        System.out.println(a % 64);
        System.out.println(a % 64L);
    }

    @Test
    public void test3() {
       int a = 10;
       int b = 3;
        System.out.println(a / b);
        System.out.println(a % b);
        System.out.println((a % b) == 0 ? (a / b) : (a / b) + 1);
    }

    @Test
    public void test4() {
        Long a = 1L;
        Long b = new Long(1L);
        System.out.println(a == b.longValue());
    }

    @Test
    public void test5() {
        Long a = 1L;
        Long b = new Long(1L);
        long c = 0L;
        Long d = null;
        System.out.println(Long.compare(a,c));
        System.out.println(Long.compare(a,b));
        System.out.println(a > c);
        System.out.println(d > c);
    }

    @Test
    public void test6() {
        System.out.println(0x7fffffffffffffffL);
    }

    @Test
    public void test_int_long_compare() {
        int a = 10000;
        long b = 10000L;
        long c = 10001L;
        Long d = 10001L;
        Long e = new Long(10000);
        Long f = new Long(10000);

        System.out.println(a == b);
        System.out.println(a == e);
        System.out.println(b < c);
        System.out.println(c == d);
        System.out.println(d == e);
        System.out.println(e == f);
    }
}
