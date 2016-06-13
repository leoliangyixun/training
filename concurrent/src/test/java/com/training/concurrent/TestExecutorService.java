package com.training.concurrent;

import com.sun.org.apache.xalan.internal.utils.FeatureManager;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class TestExecutorService {
    @Test
    public void test() throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Void> f = executor.submit(new Task2());
       // executor.submit(new Task2());
       // executor.submit(new Task2());
        executor.awaitTermination(5, TimeUnit.SECONDS);
        executor.shutdown();
    }
    
    public static class Task1 implements Runnable{

        public void run() {
            System.out.println(Thread.currentThread().getName() + " start run ......");
            try {
                
                Thread.sleep(100);
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end run ......");
        }

        
    }
    
    public static class Task2 implements Callable<Void> {

        public Void call() throws Exception {
            System.out.println(Thread.currentThread().getName() + " start run ......");
            try {
                
                Thread.sleep(100);
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end run ......");
            return null;
        }
        
    }

}
