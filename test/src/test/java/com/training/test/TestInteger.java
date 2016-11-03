/**
 * 
 */
package com.training.test;

import org.junit.Test;

/**
 * @author yangkai
 *
 */
public class TestInteger {
    
    @Test
    public void test() {
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        Integer c = new Integer(2);
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a < c);
        
    }
    
    @Test
    public void test2() {
        int a = 0;
        Integer x = new Integer(0);
        Calculator calculator = new Calculator();
        System.out.println(calculator.add(a));
        System.out.println(a);
        System.out.println(calculator.add(x));
        System.out.println(x);
       
        
    }
    
    public static class Calculator {
        public int add(Integer i) {
            return ++i;
        }
    }

}
