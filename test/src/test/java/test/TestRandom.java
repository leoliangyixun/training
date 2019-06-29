package test;

import org.junit.Test;

import java.util.Random;

/**
 * @author yangkai
 * @date 2019-06-17
 * @email yangkai@hujiang.com
 * @description
 */
public class TestRandom {


    @Test
    public void test1() {
        System.out.println(new Random().longs(1000, 1, 100).count());

    }
}
