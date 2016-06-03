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
public class TestExecutorService {

    public TestExecutorService() {

    }

    public static class Task implements Callable<String>{
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
    public static void main(String[] args) throws InterruptedException, ExecutionException{

        ExecutorService executor = Executors.newFixedThreadPool(5);
        Task task1 = new Task("task1", 3000);
        Task task2 = new Task("task2", 2000);
        Task task3 = new Task("task3", 1000);
        Future<String> future1 = executor.submit(task1);
        Future<String> future2 = executor.submit(task2);
        Future<String> future3 = executor.submit(task3);

        new Printer(future1).print();
        new Printer(future2).print();
        new Printer(future3).print();
    }

    public <T> void printResult(Future<T> future) throws InterruptedException, ExecutionException {
        T result  = future.get();
        System.out.println(result);
    }

    public static class Printer {
        private Future<String> future;

        public Printer(Future<String> future) {
            this.future = future;
        }

        public void print() throws InterruptedException, ExecutionException {
            String result = future.get();
            System.out.println(result);
        }
    }


}
