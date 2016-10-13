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

}
