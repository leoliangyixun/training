/**
 * 
 */
package com.test.concurrent;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年6月20日 Modification History: Date Author Version Discription
 *              ----------------------------------------------------------------
 *              ------------------- 2016年6月20日 jiqingchuan 1.0 Why & What is
 *              modified
 */
public class TestProducerConsumer {

    @Test
    public void test() throws InterruptedException {
        BoundedBuffer bb = new BoundedBuffer();
        Producer p = new Producer(bb);
        Consumer c = new Consumer(bb);
        new Thread(p).start();
        new Thread(c).start();
        Thread.sleep(1000);

    }

    public static class Producer implements Runnable {
        private BoundedBuffer bb;

        public Producer(BoundedBuffer bb) {
            this.bb = bb;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    bb.put(new Object());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                try {
                    bb.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static class BoundedBuffer {
        final Lock lock = new ReentrantLock();
        final Condition notFull = lock.newCondition();
        final Condition notEmpty = lock.newCondition();
        final Object[] items = new Object[2];
        int putptr, takeptr, count;

        public void put(Object x) throws InterruptedException {
            lock.lock();
            try {
                while (count == items.length) {
                    notFull.await();
                }
                items[putptr] = x;
                if (++putptr == items.length) {
                    putptr = 0;
                }
                ++count;
                System.out.println("put a object, remaining object: " + count);
                notEmpty.signal();
            } finally {
                lock.unlock();
            }

        }

        public Object take() throws InterruptedException {
            lock.lock();
            try {
                while (count == 0) {
                    notEmpty.await();
                }
                Object x = items[takeptr];
                if (++takeptr == items.length) {
                    takeptr = 0;
                }
                --count;
                System.out.println("take a object, remaining object: " + count);
                notFull.signal();
                return x;
            } finally {
                lock.unlock();
            }

        }
    }

}
