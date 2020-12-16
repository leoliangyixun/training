package test.concurrent;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yangkai
 * @date 2020/4/28
 * @email yangkai@hujiang.com
 * @description
 */
public class ThreadPoolTest {
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

    @Test
    public void testRateLimit_slide_window() {
        scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {

            }
        }, 1000, 1000, TimeUnit.SECONDS);



    }

}
