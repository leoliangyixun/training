/**
 * 
 */
package com.b5m.training.concurrent;

import java.util.concurrent.*;

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
public class CompletionServiceTest {

    public CompletionServiceTest() {

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
    public static void main(String[] args) {
        CompletionServiceTest test = new CompletionServiceTest();
        //ExecutorService是阻塞式的
        ExecutorService executor = Executors.newFixedThreadPool(5);
        //CompletionService是非阻塞式的
        CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
        ExecutorServiceTest.Task task1 = new ExecutorServiceTest.Task("task1", 3000);
        ExecutorServiceTest.Task task2 = new ExecutorServiceTest.Task("task2", 2000);
        ExecutorServiceTest.Task task3 = new ExecutorServiceTest.Task("task3", 1000);
        Future<String> future1 = completionService.submit(task1);
        Future<String> future2 = completionService.submit(task2);
        Future<String> future3 = completionService.submit(task3);

        test.printResult(future1);
        test.printResult(future2);
        test.printResult(future3);


    }

    public <T> void printResult(Future<T> future) {
        T result = null;
        try {
            result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
