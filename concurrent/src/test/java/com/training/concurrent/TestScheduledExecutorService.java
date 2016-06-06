package com.training.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestScheduledExecutorService {
    
    @Test
    public void test () throws Exception {
        
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(5);
        System.out.println(Thread.currentThread().getName() + " start run at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
//------------------------------------------------------------------------------------------------------
        //        for (int i = 0; i < 5; i++) {
//
////            ScheduledFuture<?> sf = scheduledExecutor.schedule(new Task("task" + i), 2, TimeUnit.SECONDS);
////            Object s = sf.get();
////            System.out.println("result " + s);
//            
//           scheduledExecutor.schedule(new Task("task" + i), 2, TimeUnit.SECONDS);
//
//          
//        }
        
//------------------------------------------------------------------------------------------------------       
//        ScheduledFuture<?> sf1 = scheduledExecutor.schedule(new Task("task1", 1000), 2, TimeUnit.SECONDS);
//        ScheduledFuture<?> sf2 = scheduledExecutor.schedule(new Task("task2", 500) , 2, TimeUnit.SECONDS);
//        ScheduledFuture<?> sf3 = scheduledExecutor.schedule(new Task("task3", 100) , 2, TimeUnit.SECONDS);
//        ScheduledFuture<?> sf4 = scheduledExecutor.schedule(new Task("task4", 500) , 2, TimeUnit.SECONDS);
//        ScheduledFuture<?> sf5 = scheduledExecutor.schedule(new Task("task5", 100) , 2, TimeUnit.SECONDS);
//        Object result1 = sf1.get();
//        Object result2 = sf2.get();
//        Object result3 = sf3.get();
//        Object result4 = sf4.get();
//        Object result5 = sf5.get();
//------------------------------------------------------------------------------------------------------   
        scheduledExecutor.schedule(new Task("task1", 1000), 1, TimeUnit.SECONDS);
        scheduledExecutor.schedule(new Task("task2", 500) , 1, TimeUnit.SECONDS);
        scheduledExecutor.schedule(new Task("task3", 100) , 1, TimeUnit.SECONDS);
        scheduledExecutor.schedule(new Task("task4", 500) , 1, TimeUnit.SECONDS);
        scheduledExecutor.schedule(new Task("task5", 100) , 1, TimeUnit.SECONDS);
 
        System.out.println(Thread.currentThread().getName() + " end run at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
        //注意：主线程结束后就不会再打印输出了
        Thread.sleep(100000);
        
        scheduledExecutor.shutdown();
       
    }
    
    public static class Task implements Runnable {
        private String name;
        private int millis;
        public Task(String name, int millis) {
            this.name = name;
            this.millis = millis;
        }

        public void run() {
            System.out.println(this.name+ " start run at "  + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
            try {
                Thread.sleep(this.millis);
            } catch (InterruptedException e) {
               
                e.printStackTrace();
            }
            System.out.println(this.name + " end run at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
        }

        
    }

}
