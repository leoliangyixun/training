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

public class Main {
    public static void main(String[] args) {
        //new Base();
        Derived d = new Derived();
        d.display();
        
    }
    
    public static class Derived extends Base {
        private int i = 22;
        
        public Derived() {
           
            i = 222;
        }
        
        @Override
        public void display () {
            System.out.println(i);
        }
        public void print () {
            System.out.println(i);
        }
    }
    
    public static class Base {
        private int i = 2;
        
        public Base () {
            System.out.println(this.getClass().getName());
            System.out.println(this.i);
           // this.show();
            this.display();
        }
        
        public Base (int j) {
            System.out.println(this.getClass().getName());
            System.out.println(j);
        }
        
        public void display () {
            System.out.println(i);
        }
        
        private void show () {
            System.out.println(i);
        }
        
    }
}
