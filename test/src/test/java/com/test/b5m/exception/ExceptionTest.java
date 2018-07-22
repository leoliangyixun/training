/**
 * 
 */
package com.test.b5m.exception;

import org.junit.Test;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年3月23日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年3月23日       jiqingchuan          1.0             Why & What is modified
 */
public class ExceptionTest {

	public ExceptionTest() {
		// TODO Auto-generated constructor stub
	}
	
	public void test() {
		for (int i = 0; i < 20; i++) {
			try {
				test2(i);
			} catch (Exception e) {
				System.out.println("i=" + i);
			//	 throw new RuntimeException("test");
			} finally {
				
			}
			System.out.println("i=" + i);
		}
	}
	public void test2(int i) {
			if (i == 5) throw new RuntimeException("test2");
	}
	
	public void run() {
		for (int j = 0; j < 10; j++) {
			test();
			System.out.println("j=" + j);
		}
	}
	
    @Test
    public void test_() {
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExceptionTest test = new ExceptionTest();

		try {
			test.test();
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testComplexException() {
	    Customer customer = new Customer();
	    String s = customer.consume();
	    System.out.println(s);
	}
	
	public static class Customer {
	    public String consume() {
	        
	        try {
	            execute();
	            return "success";
	        } catch (Exception e) {
	            System.out.println("exception");
	        } finally {
	            System.out.println("finally");
	            // can't throw exception in finally block
	            //throw new RuntimeException("finally  error");
	        }
	        return null;
	    }
	    
	    public void execute() {
	        throw new RuntimeException("execute error");
	    }
	}

}
