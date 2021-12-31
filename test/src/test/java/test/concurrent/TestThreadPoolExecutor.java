package test.concurrent;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yangkai
 * @date 2021/7/23
 * @email yangkai@hujiang.com
 * @description
 */
public class TestThreadPoolExecutor {

    public static class MyThreadPoolExecutor {

        private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
        private static final int COUNT_BITS = Integer.SIZE - 3;
        private static final int CAPACITY = (1 << COUNT_BITS) - 1;

        // runState is stored in the high-order bits
        private static final int RUNNING = -1 << COUNT_BITS;
        private static final int SHUTDOWN = 0 << COUNT_BITS;
        private static final int STOP = 1 << COUNT_BITS;
        private static final int TIDYING = 2 << COUNT_BITS;
        private static final int TERMINATED = 3 << COUNT_BITS;

        // Packing and unpacking ctl
        private static int runStateOf(int c) {
            return c & ~CAPACITY;
        }

        private static int workerCountOf(int c) {
            return c & CAPACITY;
        }

        private static int ctlOf(int rs, int wc) {
            return rs | wc;
        }

        public static void main(String[] args) {
//            AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
//            int c = ctl.get();
//            System.out.println(c);
//
//            System.out.println(CAPACITY);
            System.out.println(RUNNING);
            System.out.println(SHUTDOWN);
            System.out.println(STOP);
            System.out.println(TIDYING);
            System.out.println(TERMINATED);
        }

    }

    @Test
    public void test_ctl() {

    }

    @Test
    public void test() {
        TimeUnit unit;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20,5 ,TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        executor.allowCoreThreadTimeOut(true);
    }

}
