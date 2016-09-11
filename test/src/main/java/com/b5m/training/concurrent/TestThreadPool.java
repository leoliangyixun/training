package com.b5m.training.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by yangkai on 16/6/2.
 */
public class TestThreadPool {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> f1 = executorService.submit(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.out.println("f1 start run ......");
                        Thread.sleep(2000);
                        System.out.println("f1 run over");
                        return "f1";
                    }
                }
        );
        Future<String> f2 = executorService.submit(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.out.println("f2 start run ......");
                        Thread.sleep(2000);
                        System.out.println("f2 run over");
                        return "f2";
                    }
                }
        );
        String result1 = f1.get();
        System.out.println("f1 return result " + result1);

        String result2 = f2.get();
        System.out.println("f2 return result " + result2);
    }
}
