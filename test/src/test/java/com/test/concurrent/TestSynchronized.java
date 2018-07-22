/**
 * 
 */
package com.test.concurrent;

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
    
    @Test
    public void test2() {
        final Resource r = new Resource();
        final Resource r2 = new Resource();
/*        new Thread(new Runnable() {
            
            @Override
            public void run() {
               r.add();
                
            }
        }).start();
        

        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
               r.remove();
                
            }
        }).start();*/
        
/*        new Thread(new Runnable() {
            
            @Override
            public void run() {
               r.show();
                
            }
        }).start();*/
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
               r.get();
                
            }
        }).start();
        new Thread(new Runnable() {
            
            @Override
            public void run() {
               r.pop();
                
            }
        }).start();

        // 保证前面的线程都执行完
        while (Thread.activeCount() > 1) { 
            Thread.yield();
        }
    }
    
    static class Resource {
        public synchronized void add() {
            System.out.println("begin add");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("end add");
            
        }
        
        public synchronized void remove() {
            System.out.println("begin remove");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("end remove");
        }
        
        public void show () {
            System.out.println("begin show");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("end show");
        }
        
        public synchronized static void get () {
            System.out.println("begin get");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("end get");
        }
        
        public synchronized static void pop () {
            System.out.println("begin pop");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("end pop");
        }
        

    }
    
    @Test
    public void test3() {

    }

}
