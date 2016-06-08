package com.training.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.junit.Test;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class TestExecutorService {
    
    @Test
    public void test () {
        
        ExecutorService executor = Executors.newCachedThreadPool();
        
     
        executor.submit(new Task2());
        executor.submit(new Task2());
        executor.submit(new Task2());
        
        
    
        
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
       
    }
    
    public static class Task1 implements Runnable{

        public void run() {
            System.out.println(Thread.currentThread().getName() + " start run ......");
            try {
                
                Thread.sleep(100);
                
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end run ......");
            return null;
        }
        
    }

}
