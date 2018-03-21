/**
 * 
 */
package com.b5m.concurrent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年5月19日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年5月19日       jiqingchuan          1.0             Why & What is modified
 */
public class TestConcurrentHashMap {
    private static final String NUM = "num";
    private static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>(100, 0.85f, 100); 
    static {
        map.put(NUM, 0);
    }

    public static void main(String[] args) throws InterruptedException {  
        for (int i = 0; i < 50; i++) {
            final int count = i;
            new Thread("Thread" + i) {
                @Override
                public void run() {
                    
                    //Integer num = map.get(NUM);
                   // System.out.println("Thread" + count + " before update:--------------------" + num + "--------------------");
                    map.put(NUM, count);
                    
                    
                    System.out.println("Thread" + count +" before update:--------------------" +map.get(NUM) + "--------------------");
                }
            }.start();
        }

        Thread.sleep(3000);
        System.out.println(map);   
    }
}
