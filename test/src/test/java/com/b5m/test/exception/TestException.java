/**
 * 
 */
package com.b5m.test.exception;

import org.junit.Test;

/**
 * @author yangkai
 *
 */
public class TestException {
    
    @Test
    public void test() {
        User user = new User();
        
        String s = user.action();
        System.out.println( s == null);
    }
    

    
    
    public static class User {
        
        public String action() {
            try {
                eat();

            } catch (Exception e) {
               System.out.println(e);
              // return " catch exception";
               throw new RuntimeException("catch exception");
            } finally {
                System.out.println("finally");
            }
            return "aaa";
           // sleep();
           // paly(); 
        }
        
        private void eat(){
            System.out.println("eatting");
            throw new RuntimeException();
            
        }
        
        private void sleep(){
            System.out.println("sleeping");
            
        }
        
        private void paly(){
            System.out.println("playing");
        }
    }

}
