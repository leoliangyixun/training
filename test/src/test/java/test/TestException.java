/**
 * 
 */
package test;

import org.junit.Test;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年6月16日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年6月16日       jiqingchuan          1.0             Why & What is modified
 */
public class TestException {
    
    @Test
    public void test() {
        Printer  printer = new Printer(30);
        printer.print();
        
    }
    
    public static class Printer {
        private int num;
        
        public Printer(int num) {
            this.num = num;
        }
        public void print() {
            
            int i = 0;
            try {
                while(i < num) {
                    exec(i);
                    i++;
                   // if (i == 20) return;
                }
            
                System.out.println("all elements show finished.");
            } catch (Exception e) {
               System.out.println("catch exception " + e.getMessage());
            } finally {
                System.out.println("finally ......");
            }

            
        }
        
        public void exec(int i) throws Exception {
            if (i == 22) throw new Exception("Are you kidding me!");
            System.out.println(i);
        }
    }

}
