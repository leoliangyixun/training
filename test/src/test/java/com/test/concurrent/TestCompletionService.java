/**
 * 
 */
package com.test.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年5月13日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年5月13日       jiqingchuan          1.0             Why & What is modified
 */
public class TestCompletionService {

    public TestCompletionService() {

    }

    public static class Task implements Callable<String> {
        private String name;
        private Long time;
        public Task(String name, long time){
            this.name = name;
            this.time = time;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(time);
            System.out.println(Thread.currentThread().getName() + "-->" + this.name + "执行完任务,花费时间" + time + "ms");
            return this.name + "任务已完成.";
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        TestCompletionService test = new TestCompletionService();
        //ExecutorService是阻塞式的
        ExecutorService executor = Executors.newFixedThreadPool(5);
        //CompletionService是非阻塞式的
        CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
        Task task1 = new Task("task1", 3000);
        Task task2 = new Task("task2", 2000);
        Task task3 = new Task("task3", 1000);
        Future<String> future1 = completionService.submit(task1);
        Future<String> future2 = completionService.submit(task2);
        Future<String> future3 = completionService.submit(task3);

/*        System.out.println(completionService.take().get());
        System.out.println(completionService.take().get());
        System.out.println(completionService.take().get());*/
/*
        System.out.println(completionService.poll());
        System.out.println(completionService.poll());
        System.out.println(completionService.poll());*/

        System.out.println(completionService.poll(200, TimeUnit.MILLISECONDS));
        System.out.println(completionService.poll(200, TimeUnit.MILLISECONDS));
        System.out.println(completionService.poll(200, TimeUnit.MILLISECONDS));

    }


}
