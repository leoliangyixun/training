package com.test.concurrent;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author yangkai
 * @date 2018/5/5
 * @email yangkai@hujiang.com
 * @description
 */
public class TestCompletableFuture {

    @Test
    public void test_join_get() throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 1/0;
            return 100;
        });

        future.join();
      //  future.get();
    }



}
