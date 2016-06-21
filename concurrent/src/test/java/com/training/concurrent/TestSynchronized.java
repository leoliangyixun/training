/**
 * 
 */
package com.training.concurrent;

import org.junit.Test;

/**
 * @description: Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年6月21日 Modification History: Date Author Version Discription
 *              ----------------------------------------------------------------
 *              ------------------- 2016年6月21日 jiqingchuan 1.0 Why & What is
 *              modified
 */
public class TestSynchronized {

    @Test
    public void test() throws InterruptedException {
        BoundedBuffer bb = new BoundedBuffer();

        Thread t1 = new Thread(new Producer(bb));
        Thread t2 = new Thread(new Producer(bb));
        Thread t3 = new Thread(new Consumer(bb));
        Thread t4 = new Thread(new Consumer(bb));
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        
        // 保证前面的线程都执行完
        while (Thread.activeCount() > 1) { 
            Thread.yield();
        }

    }

    public static class Producer implements Runnable {
        private BoundedBuffer bb;

        public Producer(BoundedBuffer bb) {
            this.bb = bb;
        }

        @Override
        public void run() {
            while (true) {
                bb.put(new Object());
            }
        }

    }

    public static class Consumer implements Runnable {
        private BoundedBuffer bb;

        public Consumer(BoundedBuffer bb) {
            this.bb = bb;
        }

        @Override
        public void run() {
            while (true) {
                bb.take();
            }
        }

    }

    public static class BoundedBuffer {
        
        private final Object[] elements = new Object[5];
        private int count = 0;

        public void put(Object x){
            
            synchronized (this) {
                
                try {
                    while (count == elements.length){
                        wait();
                    }
                    elements[count++] = x;
                    System.out.println("put an element, remaining elements: " + count);
                    notifyAll();
                   
                   
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    
                }
                
            }
            

        }

        public Object take() {
            synchronized (this) {
                
                try {
                    while (count == 0) {
                        wait();
                    }
                    Object x = elements[--count];
                    System.out.println("take an element, remaining elements: " + count);
                    notifyAll();
                    return x;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    
                }
                
            }
           return null;

        }
    }

}
