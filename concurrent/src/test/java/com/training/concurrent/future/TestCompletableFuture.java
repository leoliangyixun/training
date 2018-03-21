/**
 * 
 */
package com.training.concurrent.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Delayed;

import org.junit.Test;

/**
 * @author yangkai
 *
 */
public class TestCompletableFuture {
    
    @Test
    public void test() {
        CompletableFuture<T> future = null;
    }
    
    public static class Shop {
        
        private String name;
        
        public Shop(String name) {
            this.name = name;
        }
        
        public String getPrice(String product) {
            
        }
        
        private double calculatePrice(String product) {
            delay();
            return new Random(name.charAt(0) * name.charAt(1) * name.charAt(2)).nextDouble() * product.charAt(0) + product.charAt(1);
        }
        
        private void delay() {
            int delay = 1000;
            //int delay = 500 + RANDOM.nextInt(2000);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
