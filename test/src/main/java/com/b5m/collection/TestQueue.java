/**
 * 
 */
package com.b5m.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author yangkai
 *
 */
public class TestQueue {

 
    public static void main(String[] args) {
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
        try {
            bq.put("xx");
            bq.put("mm");
          
            System.out.println(bq);
            bq.put("yy");;
        } catch (InterruptedException e) {
            
            e.printStackTrace();
        }
        

    }
    
    public static class User {
        
    }

}
