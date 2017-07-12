/**
 * 
 */
package test;

import java.io.IOException;

import org.junit.Test;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
    
    
    @Test
    public void testEx() {
    	UserProxy userProxy = new UserProxy();
    	userProxy.exec();
    	
    	
    }
    
    public static class UserProxy {
    	private SmsService smsService = new SmsService();
    	public void exec(){
    		try {
    			smsService.sendAll();
			} catch (Exception e) {
				System.out.println(e);
			}
    	}
    }
    
   
    public static class SmsService {
    	public void sendAll(){
    		for (int i = 0; i<10; i++) {
    			try {
    				send(i);
				} catch (Exception e) {
					// TODO: handle exception
				}
    			
    		}
    	}
    	
    	public void send(int i){
    		if(i == 5) {
    			throw new RuntimeException("ERROR");
    		}
    		System.out.println(i);
    	}
    }

    @Test
    public void test2() {
        UserService userService = new  UserService();
        try {
            userService.exec();
            System.out.println("1");
            System.out.println("2");
        } catch (Exception e) {
            System.out.println("error");
        }

    }

    @Test
    public void test3() {
        UserService userService = new  UserService();
        try {
            for (int i = 0; i<10; i++) {
                userService.exec(i);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    @Test
    public void test4() {
        UserService userService = new  UserService();

            for (int i = 0; i<10; i++) {
                try {
                    userService.exec(i);
                } catch (Exception e) {
                    System.out.println("error");
                }
            }

    }

    public class UserService {
        public boolean exec() {
            return true;
            //throw new RuntimeException("system error");
        }

        public void exec(int i) {
            if (i == 5) {
                throw new RuntimeException("system error");
            }
            System.out.println(i);
        }
    }
    
    @Data
    @EqualsAndHashCode(callSuper=true)
    public static class SysException extends RuntimeException {

        private static final long serialVersionUID = -6817534485465703972L;
        private int errorCode;
        private String errorMsg;
        
        public SysException(int errorCode, String errorMsg) {
            this.errorCode = errorCode;
            this.errorMsg = errorMsg;
        }
    }
    
    @Data
    @EqualsAndHashCode(callSuper=true)
    public static class AppException extends RuntimeException {

        private static final long serialVersionUID = -6817534485465703972L;
        private int errorCode;
        private String errorMsg;
        
        public AppException(int errorCode, String errorMsg) {
            this.errorCode = errorCode;
            this.errorMsg = errorMsg;
        }
    }
    
    public static class Runner {
        public String run(int i) {
            try {
                if (i == 0) {
                    return "ok";
                }
                throw new SysException(0, "error");
            } catch (AppException e) {
               System.out.println(e.getErrorMsg());
               return "exception";
            }
        }
    }
    
    @Test
    public void testTryCatch() {
        Runner runner = new Runner();
        String result = runner.run(400);
        System.out.println(result);

    }

}
