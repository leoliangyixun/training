package com.test.concurrent;

import org.junit.Test;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangkai
 * @date 2018/4/30
 * @email yangkai@hujiang.com
 * @description
 */
public class TestThreadPool {

    private ExecutorService executor = Executors.newFixedThreadPool(10);
    private CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

    @Test
    public void test_getActiveCount() {


        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        ThreadPoolExecutor executor2 =new ThreadPoolExecutor(10, 20,
            10L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());
        executor.submit(() -> {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("active count " + executor.getActiveCount());
        System.out.println("completed task count " + executor.getCompletedTaskCount());
        System.out.println("largest pool size " + executor.getLargestPoolSize());
        System.out.println("pool size " + executor.getPoolSize());
        System.out.println("task count " +executor.getTaskCount());

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

    }

    @Test
    public void testCachedThreadPool() {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {

            executor.submit(() -> System.out.println(Thread.currentThread()));

        }

        executor.shutdown();

        try {

            executor.awaitTermination(1, TimeUnit.SECONDS);

        } catch (InterruptedException ex) {

            ex.printStackTrace();

        }

    }
}
