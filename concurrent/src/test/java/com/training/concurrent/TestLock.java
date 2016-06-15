package com.training.concurrent;

import org.junit.Test;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

    public final ExecutorService executor = Executors.newFixedThreadPool(200);
    public final CompletionService<Void> completionService = new ExecutorCompletionService<Void>(executor);
    @Test
    public void testMultiLock() {

        final BoundedBuffer2<Integer> bb = new BoundedBuffer2<Integer>(20);

        for (int i = 0; i < 3; i++) {
            final int n = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bb.put(n);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }




    }
    @Test
    public void testMultiCondition() {

         BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<Integer>();
         
        for (int i = 0; i < 100; i++) {
        
        }
        
        for (int i = 0; i < 100; i++) {
          
        }


    }

    @Test
    public void testLockInterruptibly() {


    }

    public  static  class BoundedBuffer<E> {
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition notFull = lock.newCondition();
        private final Condition notEmpty = lock.newCondition();
        private final Object[] items = new Object[100];
        private int putIndex, takeIndex, count;

        public void put(E e) {

        }
        public E take() {
            return null;
        }
    }

    public  static  class Source {
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition cond = lock.newCondition();

        public void use() {

        }
    }

    public  static  class BoundedBuffer2<E> {
        private final ReentrantLock putLock = new ReentrantLock();
        private final ReentrantLock takeLock = new ReentrantLock();
        private final Condition notFull = putLock.newCondition();
        private final Condition notEmpty = takeLock.newCondition();
        private final Object[] items;
        private int putIndex, takeIndex, count;
        public BoundedBuffer2(int capacity) {
            items = new Object[capacity];
        }

        public void put(E e) throws InterruptedException {
            putLock.lock();
            try {
             //  notEmpty.await();//java.lang.IllegalMonitorStateException
               notFull.await();
            } finally {
                putLock.unlock();
            }
        }
        public E take() {
            return null;
        }
    }
}
