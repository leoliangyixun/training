package com.training.concurrent;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Fuck {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition cond = lock.newCondition();
    private static boolean letterPrinterStop = true;

    private static final BlockingQueue<Printer> completionQueue = new ArrayBlockingQueue<Printer>(2);

    @Test
    public void test() throws InterruptedException {
        String[] letters = new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
                "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
        final NumberPrinter np = new NumberPrinter(1, 63, 5);
        final LetterPrinter lp = new LetterPrinter(letters, 3);

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

    }

    public static abstract class Printer {
        public abstract void print();
    }

    private static class LetterPrinter extends Printer {
        private String[] letters;
        private int continuous;

        public LetterPrinter(String[] letters, int continuous) {
            this.letters = letters;
            this.continuous = continuous;
        }

        @Override
        public void print() {
            int index = 0;
            lock.lock();
            try {
                while (index < letters.length) {
                    if (letterPrinterStop) {
                        cond.await();
                    } else {
                        for (int i = 0; i < continuous; i++) {
                            if (index < letters.length) {
                                System.out.print(letters[index++] + ",");
                            }
                        }
                        letterPrinterStop = true;
                        cond.signal();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private static class NumberPrinter extends Printer {
        private int begin;
        private int end;
        private int continuous;

        public NumberPrinter(int begin, int end, int continuous) {
            this.begin = begin;
            this.end = end;
            this.continuous = continuous;
        }

        @Override
        public void print() {
            int curr = begin;
            lock.lock();
            try {
                while (curr <= end) {
                    if (letterPrinterStop) {
                        for (int i = 0; i < continuous; i++) {
                            if (curr <= end) {
                                System.out.print((curr++) + ",");
                            }
                        }
                        letterPrinterStop = false;
                        cond.signal();
                    } else {
                        cond.await();
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
