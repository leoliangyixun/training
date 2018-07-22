package com.test.concurrent.pool;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

/**
 * @author yangkai
 * @date 2018/5/29
 * @email yangkai@hujiang.com
 * @description
 */
public class TestForkJoinPool {

    @Test
    public void test() {
        ForkJoinPool pool = new ForkJoinPool(3);
        //ForkJoinPool pool = ForkJoinPool.commonPool();
       // pool.submit()
    }

}
