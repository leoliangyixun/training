package test;

import org.junit.Test;

import java.time.LocalTime;

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

}
