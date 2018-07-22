package com.training.springboot.test;

import com.training.springboot.test.AsyncTest.Config;
import com.training.springboot.test.async.task.AsyncTask;
import com.training.springboot.test.async.task.SyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yangkai
 * @date 2018/4/20
 * @email yangkai@hujiang.com
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Import({Config.class})
public class AsyncTest {

    @Configuration
    @ComponentScan({"com.training.springboot.test"})
    @EnableAsync
    public static class Config {

        @Bean("taskExecutor")
        public Executor taskExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(10);
            executor.setMaxPoolSize(20);
            executor.setQueueCapacity(200);
            executor.setKeepAliveSeconds(60);
            executor.setAwaitTerminationSeconds(60);
            executor.setWaitForTasksToCompleteOnShutdown(true);
            executor.setThreadNamePrefix("taskExecutor-");
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            return executor;
        }

    }

    @Autowired
    private SyncTask syncTask;

    @Autowired
    private AsyncTask task2;

    @Test
    public void test1() throws Exception {
        syncTask.doTaskOne();
        syncTask.doTaskTwo();
        syncTask.doTaskThree();
    }

    @Test
    public void test2() throws Exception {
        task2.doTask1();
        task2.doTask2();
        task2.doTask3();
        task2.doTask4();
        task2.doTask5();

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }


    @Component
    public static class Task {

        public static Random random =new Random();

        @Async
        public void doTask1() throws Exception {
            System.out.println(Thread.currentThread().getName() +"开始做任务一");
            long start = System.currentTimeMillis();
            Thread.sleep(random.nextInt(100));
            long end = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() +"完成任务一，耗时：" + (end - start) + "毫秒");
        }

        @Async
        public void doTask2() throws Exception {
            System.out.println(Thread.currentThread().getName() +"开始做任务二");
            long start = System.currentTimeMillis();
            Thread.sleep(random.nextInt(100));
            long end = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() +"完成任务二，耗时：" + (end - start) + "毫秒");
        }

        @Async
        public void doTask3() throws Exception {
            System.out.println(Thread.currentThread().getName() + "开始做任务三");
            long start = System.currentTimeMillis();
            Thread.sleep(random.nextInt(100));
            long end = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() +"完成任务三，耗时：" + (end - start) + "毫秒");
        }

    }

    @Component
    public static class Task2 {

        public static Random random =new Random();

        @Async("taskExecutor")
        public void doTask1() throws Exception {
            System.out.println(Thread.currentThread().getName() +"开始做任务一");
            long start = System.currentTimeMillis();
            Thread.sleep(random.nextInt(100));
            long end = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() +"完成任务一，耗时：" + (end - start) + "毫秒");
        }

        @Async("taskExecutor")
        public void doTask2() throws Exception {
            System.out.println(Thread.currentThread().getName() +"开始做任务二");
            long start = System.currentTimeMillis();
            Thread.sleep(random.nextInt(100));
            long end = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() +"完成任务二，耗时：" + (end - start) + "毫秒");
        }

        @Async("taskExecutor")
        public void doTask3() throws Exception {
            System.out.println(Thread.currentThread().getName() + "开始做任务三");
            long start = System.currentTimeMillis();
            Thread.sleep(random.nextInt(100));
            long end = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() +"完成任务三，耗时：" + (end - start) + "毫秒");
        }

    }

    @Autowired
    private Task task3;
    @Autowired
    private Task2 task4;

    @Test
    public void test3() throws Exception {
        task3.doTask1();
        task3.doTask2();
        task3.doTask3();
        System.out.println("主函数" + Thread.currentThread().getName());

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }


    @Test
    public void test4() throws Exception {
        task4.doTask1();
        task4.doTask2();
        task4.doTask3();
        System.out.println("主函数" + Thread.currentThread().getName());

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }



}
