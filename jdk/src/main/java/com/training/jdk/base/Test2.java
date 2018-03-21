/**
 * 
 */
package com.training.jdk.base;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年6月8日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年6月8日       jiqingchuan          1.0             Why & What is modified
 */
public class Test2 {

    public Test2() {
       
    }

    public static void main(String[] args) {
        Apple a = new Apple();
     //   Fruit f = a.getFruit();
      //  System.out.println(f.count);

    }
    
    public static class Apple extends Fruit {
        private int count = 2;
        {
            System.out.println(count);
        }
        
        public Apple() {
            getFruit();
            
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        @Override
        public void info() {
            System.out.println(count);
        }
        
        public Fruit getFruit() {
            return super.get();
        }
    }
    
    public static class Fruit {
        private int count = 1;

        public Fruit get() {
            System.out.println(this.getClass().getName());
            info();
            return this;
        }
        
        public void info() {
            System.out.println(count);
        }
        
    }
    
}

