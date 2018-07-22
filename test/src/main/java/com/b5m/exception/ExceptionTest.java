/**
 * 
 */
package com.b5m.exception;

/**
 * @author yangkai
 *
 */
public class ExceptionTest {
    
    public static void main (String[] args) {
        
/*        SysException ex1 = new SysException();
        MMPSysException ex2 = new MMPSysException();
        System.out.println(ex1 instanceof Exception);
        System.out.println(ex2 instanceof Exception);*/
        
        Apple a = new Apple();
        RedApple a1 = new RedApple();
        LightApple a2 = new LightApple();
        YellowApple a3 = new YellowApple();

        
    }
    
    public static class SysException extends Exception {

        private static final long serialVersionUID = 20746484247783517L;
        
    }
    public static class MMPSysException extends SysException {

        private static final long serialVersionUID = -5049608496678846580L;

        
    }
    public static class Apple {


        
    }
    
    public static class RedApple extends Apple {


        
    }
    
    public static class LightApple extends RedApple {


        
    }
    
    public static class YellowApple extends Apple {


        
    }

}
