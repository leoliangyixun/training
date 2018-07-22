package com.training.springboot.test;

import com.training.springboot.config.ThreadPoolConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yangkai
 * @date 2018/6/20
 * @email yangkai@hujiang.com
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Import(ThreadPoolConfig.class)
public class ScheduleTest5 {

    @Autowired
    private Scheduler scheduler;

    @Test
    public void test1() {
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }

    @Component
    public static class Scheduler {
        private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss SSS");
        //@Scheduled(fixedRate = 1000)
        public void run1() throws Exception {
            System.out.println(sdf.format(new Date()) + " start " + Thread.currentThread().getName());
            Thread.sleep(2000);
            //Thread.sleep(100);
            System.out.println(sdf.format(new Date()) + " end " + Thread.currentThread().getName());
        }

        @Scheduled(fixedRate = 1000)
        public void run2() throws Exception {
            System.out.println(sdf.format(new Date()) + " start " + Thread.currentThread().getName());
            Thread.sleep(2000);
            //Thread.sleep(100);
            System.out.println(sdf.format(new Date()) + " end " + Thread.currentThread().getName());
        }

        //@Scheduled(fixedDelay = 1000)
        public void run3() throws Exception {
            System.out.println(sdf.format(new Date()) + " start " + Thread.currentThread().getName());
            Thread.sleep(2000);
           // System.out.println(sdf.format(new Date()) + " end " + Thread.currentThread().getName());
        }

        //@Async("taskExecutor")
        //@Scheduled(fixedRate = 1000)
        public void run4() throws Exception {
            System.out.println(sdf.format(new Date()) + " start run4 " + Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println(sdf.format(new Date()) + " end run4 " + Thread.currentThread().getName());
        }
    }

}
