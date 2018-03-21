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
}
