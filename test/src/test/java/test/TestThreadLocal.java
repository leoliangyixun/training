package test;

import com.hujiang.basic.framework.core.threads.NamedThreadFactory;

import com.alibaba.ttl.TransmittableThreadLocal;

import lombok.extern.log4j.Log4j2;

import org.junit.Test;

import test.TestSE.User;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangkai
 * @date 2021/8/17
 * @email yangkai@hujiang.com
 * @description
 */
@Log4j2
public class TestThreadLocal {

    private static final ThreadLocal<Integer> tl1 = new ThreadLocal<>();
    private static final ThreadLocal<String> tl2 = new ThreadLocal<>();
    private static final ThreadLocal<User> tl3 = new ThreadLocal<>();

    @Test
    public void test1() {
        Thread t1 = new Thread(() -> {
            int i = 10;
            tl3.set(new User("t1", 1));
            while (i > 0) {
                User user = tl3.get();
                System.out.println(Thread.currentThread().getName() + "-" + user);
                i--;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            int i = 10;
            tl3.set(new User("t2", 2));
            while (i > 0) {
                User user = tl3.get();
                System.out.println(Thread.currentThread().getName() + "-" + user);
                i--;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        Thread t3 = new Thread(() -> {
            int i = 10;
            tl3.set(new User("t3", 3));
            while (i > 0) {
                User user = tl3.get();
                System.out.println(Thread.currentThread().getName() + "-" + user);
                i--;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t3");

        t1.start();
        t2.start();
        t3.start();

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }


    @Test
    public void test2() {
        int[] a = new int[16];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        System.out.println(Arrays.toString(a));

        int start = 2;
        for (int k = a[start]; k != 0; k = a[start = nextIndex(start, a.length)]) {
            System.out.println(start + ":" + k);
        }

    }

    private static int nextIndex(int i, int len) {
        return ((i + 1 < len) ? i + 1 : 0);
    }

    private static final ExecutorService EXECUTOR1 = new ThreadPoolExecutor(5, 10, 10L, TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(1000), new NamedThreadFactory("线程池1", true),
        (r, executor) -> {
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                log.error("[error stack]: ", e);
            }
        });

    private static final ExecutorService EXECUTOR2 = new ThreadPoolExecutor(5, 10, 10L, TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(1000), new NamedThreadFactory("线程池2", true),
        (r, executor) -> {
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                log.error("[error stack]: ", e);
            }
        });

    //private static final ThreadLocal<Integer> th = ThreadLocal.withInitial(() -> -1);
    private static final ThreadLocal<Integer> th = new TransmittableThreadLocal<>();

    @Test
    public void testTransmittableThreadLocal1() {
      for (int i = 0; i < 5 ;i ++) {
          EXECUTOR1.submit(new Parent("父线程-" + i));
      }

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

    }

    public static class Parent implements Runnable {
        private String name;

        public Parent(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            int k = new Random().nextInt(100);
            //System.out.println(name + " begin " );
            try {
                Thread.sleep(k);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            th.set(k);
            System.out.println(name + " set " + k);
            EXECUTOR2.submit(new Children(name + "-子"));

        }
    }

    public static class Children implements Runnable {
        private String name;

        public Children(String name) {
            this.name = name ;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer k = th.get();
            System.out.println(name + " get " + k);
        }
    }


}
