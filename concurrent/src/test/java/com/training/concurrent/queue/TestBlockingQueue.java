/**
 * 
 */
package com.training.concurrent.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.junit.Test;

/**
 * @author yangkai
 *
 */
public class TestBlockingQueue {


    @Test
    public void test() {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(3);

        for (int i = 0; i < 5; i++) {
            new Thread(new Producer(queue, i)).start();
        }

        for (int j = 0; j < 10; j++) {
            new Thread(new Consumer(queue)).start();
        }

        // 保证前面的线程都执行完
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

    }

    public static class Producer implements Runnable {
        
        BlockingQueue<Integer> queue = null;
        Integer i = null;

        public Producer(BlockingQueue<Integer> queue,Integer i) {
            this.queue = queue;
            this.i = i;
        }
        
        @Override
        public void run() {
            try {
                queue.put(i);
                System.out.println(Thread.currentThread().getName() + " put " + i);
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " finish put");
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

        }

    }

    
    public static class Consumer implements Runnable {
        
        BlockingQueue<Integer> queue = null;
        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }
        
        @Override
        public void run() {
            
            try {
                Integer i = queue.take();
                System.out.println(Thread.currentThread().getName() + " take " + i);
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " finish take");
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

}
