package com.training.springboot.test.async.task;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author yangkai
 * @date 2018/4/20
 * @email yangkai@hujiang.com
 * @description
 */
@Component
public class SyncTask {
    public static Random random =new Random();

    public void doTaskOne() throws Exception {
        System.out.println(Thread.currentThread().getName() + "开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "完成任务一，耗时：" + (end - start) + "毫秒");
    }

    public void doTaskTwo() throws Exception {
        System.out.println(Thread.currentThread().getName() + "开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "完成任务二，耗时：" + (end - start) + "毫秒");
    }

    public void doTaskThree() throws Exception {
        System.out.println(Thread.currentThread().getName() + "开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "完成任务三，耗时：" + (end - start) + "毫秒");
    }
}
