/**
 * 
 */
package com.training.concurrent.base;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年6月3日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年6月3日       jiqingchuan          1.0             Why & What is modified
 */
public class Main {


    public Main() {
 
    }


    public static void main(String[] args) {
        Runner runner = new Runner();
        Thread task1 = new Thread(runner, "task1");
        Thread task2 = new Thread(runner, "task2");
        task1.start();
        //task1.start();//throw  java.lang.IllegalThreadStateException
        task2.start();
        
        FutureTask<V>
    

    }
    
    public static class Runner implements Runnable {

        @Override
        public void run() {
           System.out.println(Thread.currentThread().getName() + " start running ......");
           try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
           System.out.println(Thread.currentThread().getName() + " run over ......");
            
        }
        
    }
    
    public static class Caller implements Callable<String>{

        @Override
        public String call() throws Exception {
           
            return null;
        }
    }

}
