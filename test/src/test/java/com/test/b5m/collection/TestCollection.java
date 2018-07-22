/**
 * 
 */
package com.test.b5m.collection;

import java.util.List;

import org.junit.Test;

/**
 * @author yangkai
 *
 */
public class TestCollection {
    
    @Test
    public void test () {
        Object object = null;
        List<User> users = (List<User>) object;
        System.out.println(users);
        
    }
    
    public static class User {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        
        
    }
}
