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
    public void testMultiCondition() {

         BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<Integer>();
         
        for (int i = 0; i < 100; i++) {
        
        }
        
        for (int i = 0; i < 100; i++) {
          
        }


    }
    public  static  class BoundedBuffer<E> {
        public final ReentrantLock lock = new ReentrantLock();
        public final Condition cond1 = null;
        public final Condition cond2 = null;

        public void put(E e) {

        }
    }
}
