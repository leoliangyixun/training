package com.test.concurrent;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TestThread {
    
    @Test
    public void test () {
        System.out.println(Thread.currentThread().getName() + " start run at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
        
        for (int i = 0; i < 5; i++) {
            new Thread(new Task()).start();
        }
        
        System.out.println(Thread.currentThread().getName() + " end run at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
       
    }
    
    public static class Task implements Runnable {

        public void run() {
            System.out.println(Thread.currentThread().getName() + " start run at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
            try {
                
                Thread.sleep(new Random().nextInt(1000));
                
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end run at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
        }

        
    }
    

}
