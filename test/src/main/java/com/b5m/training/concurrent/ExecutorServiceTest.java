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
public class ExecutorServiceTest {

    public ExecutorServiceTest() {

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
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(5);
        Task task1 = new Task("task1", 3000);
        Task task2 = new Task("task2", 2000);
        Task task3 = new Task("task3", 1000);
        Future<String> future1 = executor.submit(task1);
        Future<String> future2 = executor.submit(task2);
        Future<String> future3 = executor.submit(task3);

        new PrintResult(future1).print();
        new PrintResult(future2).print();
        new PrintResult(future3).print();
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

    public static class PrintResult {
        private Future<String> future;

        public PrintResult(Future<String> future) {
            this.future = future;
        }

        public void print() {
            String result = null;
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


}
