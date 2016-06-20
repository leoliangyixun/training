/**
 * 
 */
package com.training.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

/**
 * @author yangkai
 *
 */
public class TestProxy {
    
    @Test 
    public void test() {
        
        Service target = new ServiceImpl();
        MyInvocationHandler h = new MyInvocationHandler();
        h.setTarget(target);
        Class<?>[] clazzes = target.getClass().getInterfaces();
        Service service = (Service) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), h);
        
        
        service.exec();
        
        
        
    }
    
    
    public static interface Service extends Base {
        String show();
    }
    
    public static interface Base {
        void exec();
    }
    
    public static class ServiceImpl implements Service {

        @Override
        public String show() {
           System.out.println("show");
           return "show";
            
        }

        @Override
        public void exec() {
           
            System.out.println("exec");
        }
        
    }
    
    public static class MyInvocationHandler implements InvocationHandler {
        
        private Object target;
        

        public Object getTarget() {
            return target;
        }


        public void setTarget(Object target) {
            this.target = target;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before invoke");
            Object result = method.invoke(target, args);
            System.out.println("after invoke");
            return result;
        }
        
    }
    


}
