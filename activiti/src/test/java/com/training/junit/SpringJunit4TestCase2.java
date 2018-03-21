/**
 * 
 */
package com.training.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations="{classpath:spring-context.xml}")
public class SpringJunit4TestCase2 {
    
    @Test
    public void test() {
        System.out.println("test");
    }

}
