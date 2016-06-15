package com.training.concurrent;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by izene on 2016/6/15.
 */
public class Fuck {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition cond = lock.newCondition();

    @Test
    public void test() throws InterruptedException {
        String[] letters = new String[]{
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        };
        final NumberPrinter np = new NumberPrinter(1, 63, 5);
        final LetterPrinter lp = new LetterPrinter(letters,2);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                np.print();
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lp.print();
            }
        });
        t1.start();
        t2.start();

        Thread.sleep(10000);
        System.out.println("main thread end ......");
    }

    private static class LetterPrinter{
        private String[] letters;
        private int continuous;
        private boolean stop = false;
        public LetterPrinter (String[] letters,int continuous) {
        this.letters = letters;
            this.continuous = continuous;
        }

        public void print() {
           int index = 0;
            lock.lock();
            try {
                while (index < letters.length) {
                    if (stop) {
                        cond.await();
                    } else {
                        for (int i = 0; i < continuous; i++) {
                            System.out.print(letters[index++]);
                        }
                        stop = true;
                        cond.signalAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private static class NumberPrinter {
        private int begin;
        private int end;
        private int continuous;
        private boolean stop = false;

        public NumberPrinter(int begin, int end, int continuous) {
            this.begin = begin;
            this.end = end;
            this.continuous = continuous;
        }

        public void print() {
            int curr = begin;
            lock.lock();
            try {
                while (curr <= end) {
                    if (stop) {
                        cond.await();
                    } else {
                        for (int i = 0; i < continuous; i++) {
                            System.out.print(curr++);
                        }
                        stop = true;
                        cond.signalAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
