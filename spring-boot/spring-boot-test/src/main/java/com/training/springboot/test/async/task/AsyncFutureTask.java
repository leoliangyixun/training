package com.training.springboot.test.async.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * @author yangkai
 * @date 2018/4/20
 * @email yangkai@hujiang.com
 * @description
 */
@Component
public class AsyncFutureTask {
    public static Random random =new Random();


    @Async
    public Future<String> doTask1() throws Exception {
        System.out.println(Thread.currentThread().getName() + " 开始做任务1");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(100));
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " 完成任务1，耗时：" + (end - start) + "毫秒");
        return null;
    }

    @Async
    public Future<String> doTask2() throws Exception {
        System.out.println(Thread.currentThread().getName() + " 开始做任务2");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(100));
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " 完成任务2，耗时：" + (end - start) + "毫秒");
        return null;
    }

    @Async
    public Future<String> doTask3() throws Exception {
        System.out.println(Thread.currentThread().getName() + " 开始做任务3");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(100));
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + " 完成任务3，耗时：" + (end - start) + "毫秒");
        return null;
    }

}
