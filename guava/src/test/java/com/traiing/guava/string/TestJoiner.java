/**
 * 
 */
package com.traiing.guava.string;

import org.junit.Test;

import com.google.common.base.Joiner;

/**
 * @author yangkai
 *
 */
public class TestJoiner {
    
    @Test
    public void test() {
        String s = Joiner.on(":").join(new String[]{"1", "2", "3"});
        System.out.println(s);
        
    }

}
