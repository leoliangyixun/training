package com.test.concurrent.queue;

import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yangkai
 * @date 2018/5/12
 * @email yangkai@hujiang.com
 * @description
 */
public class TestBlockingQueue2 {
    public static class Producer implements Runnable {
        private final BlockingQueue<String> queue;
        Producer(BlockingQueue<String> q) { queue = q; }
        public void run() {

                while (true) {
                   //queue.add(produce());
                    try {
                        queue.put(produce());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

        }

        public String produce() {
            return UUID.randomUUID().toString();
        }
    }

    public static class Consumer implements Runnable {
        private final BlockingQueue<String> queue;
        Consumer(BlockingQueue<String> q) { queue = q; }
        public void run(){

                while (true) {
                    try {
                        consume(queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                   // consume(queue.remove());

                }

        }

        public void consume(String x) {
            System.out.println(x);
        }
    }

    @Test
    public void test() {

        BlockingQueue<String> q = new LinkedBlockingQueue<>();
        Producer p = new Producer(q);
        Consumer c1 = new Consumer(q);
        Consumer c2 = new Consumer(q);
        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }

}
