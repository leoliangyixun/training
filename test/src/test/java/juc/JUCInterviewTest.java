package juc;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.junit.Test;

/**
 * @author yangkai
 * @date 2019-03-14
 * @email yangkai@hujiang.com
 * @description
 */
public class JUCInterviewTest {

    public static class Printer {

        private boolean isPrintOdd = false;

        public synchronized void print(int num) {
            System.out.println(Thread.currentThread().getName() + ":" + num);
        }

        public boolean isPrintOdd() {
            return isPrintOdd;
        }

        public void setPrintOdd(boolean isPrintOdd) {
            this.isPrintOdd = isPrintOdd;
        }
    }

    /**
     * 两个线程交替打印0~100的奇偶数, 一个线程打印奇数另一个打印偶数，它们交替输出
     */
    @Test
    public void test1() throws Exception {
        Printer printer = new Printer();
        new Thread(() -> {
            int i = 1;
            while (i <= 99) {
                synchronized (printer) {
                    if (printer.isPrintOdd()) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                        i += 2;
                        printer.setPrintOdd(false);
                        printer.notify();
                    } else {
                        try {
                            printer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "奇数线程").start();

        new Thread(() -> {
            int i = 0;
            while (i <= 100) {
                synchronized (printer) {
                    if (!printer.isPrintOdd()) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                        i += 2;
                        printer.setPrintOdd(true);
                        printer.notify();
                    } else {
                        try {
                            printer.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }, "偶数线程").start();

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }


    @Data
    @AllArgsConstructor
    public static class Lock {

        private int curr;
    }

    /**
     * 如果有N个线程，要求让它们交替输出 1、2、3、... N，即: 线程1：1 线程2：2 线程3：3 线程1：1 线程2：2 线程3：3 ……
     */
    @Test
    public void test2() {
        int start = 1, end = 3, cur = 1;
        Lock lock = new Lock(cur);
        for (int i = start; i <= end; i++) {
            final int p = i;
            new Thread(() -> {
                while (true) {
                    synchronized (lock) {
                        if (lock.getCurr() == p) {
                            System.out.println(Thread.currentThread().getName() + ":" + p);
                            if (lock.getCurr() == end) {
                                lock.setCurr(start);
                            } else {
                                lock.setCurr(lock.getCurr() + 1);
                            }
                            lock.notifyAll();
                        } else {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }, "线程" + i).start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }

    /**
     * 通过N个线程顺序循环打印从0至100， 如给定N=3则输出: thread0: 0 thread1: 1 thread2: 2 thread0: 3 thread1: 4 .....
     */
    @Test
    public void test3() {
        int start = 0, end = 5, cur = 0, n = 100;
        Lock lock = new Lock(cur);
        for (int i = start; i < end; i++) {
            final int p = i;
            new Thread(() -> {
                int k = p;
                //不能使用for循环
                while (k <= n) {
                    synchronized (lock) {
                        if (lock.getCurr() != p) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println(Thread.currentThread().getName() + ":" + k);
                            k += end;
                            if (lock.getCurr() == end - 1) {
                                lock.setCurr(start);
                            } else {
                                lock.setCurr(lock.getCurr() + 1);
                            }
                            lock.notifyAll();
                        }
                    }
                }
            }, "线程" + i).start();
        }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }
}
