package com.training.concurrent;

import java.util.concurrent.*;

import org.junit.Assert;
import org.junit.Test;

public class TestForkJoinPool {
    public static ExecutorService executor = new ForkJoinPool();;
    public static final CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);

    @Test
    public void test() {

        Future<String> f = completionService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(10000);
                return "fuck";
            }
        });
        Assert.assertNotNull(f);
    }

    @Test
    public void testUsingCompletionService() {

        Future<String> f = completionService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, new String());
        Assert.assertNotNull(f);
    }

    @Test
    public void testRecursiveAction() throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new PrintAction(1,500));
        pool.awaitTermination(10, TimeUnit.SECONDS);
        pool.shutdown();

    }

    public static class PrintAction extends RecursiveAction {
        private  static final int THRESHOLD = 50;
        private  int start;
        private  int end;
        //private int[] arr;

        public PrintAction(int start, int end) {
            this.start = start;
            this.end = end;
        }


        @Override
        protected void compute() {
            if (end - start < THRESHOLD) {
                for (int i = start; i < end; i++) {
                    System.out.println(Thread.currentThread().getName() + " print " + i);
                }
            } else {
                int middle = (end + start) / 2;
                new PrintAction(start, middle).fork();
                new PrintAction(middle, end).fork();
               // this.partition(new PrintAction(start, middle), new PrintAction(middle, end));
            }
        }

        protected void partition(ForkJoinTask<Void> left, ForkJoinTask<Void> right) {
            left.fork();
            right.fork();
        }
    }

}
