/**
 * 
 */
package com.b5m.concurrent;

import java.util.HashMap;
import java.util.Map;

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
public class TestHashMap {
    private static final String NUM = "num";
    private static Map<String, Integer> map = new HashMap<String, Integer>(); 
    static {
        map.put(NUM, 0);
    }
    public static void main(String[] args) throws InterruptedException {  
        for (int i = 0; i < 50; i++) {
            new Thread() {
                @Override
                public void run() {
                    Integer num = map.get(NUM);

                    map.put(NUM, num + 1);
                    System.out.println("--------------------" + (num + 1) + "--------------------");
                }
            }.start();
        }
        Thread.sleep(3000);
        System.out.println(map);  
    }

}
