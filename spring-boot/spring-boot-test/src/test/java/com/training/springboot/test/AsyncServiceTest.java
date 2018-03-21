package com.training.springboot.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.training.springboot.config.TestConfig;
import com.training.springboot.test.async.AsyncService;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan({"com.training.springboot"})
@Import(value = {TestConfig.class})
public class AsyncServiceTest {
    
    @Autowired
    private AsyncService service;

    //@Scheduled(cron = "*/1 * * * * ? ")
    public void reportCurrentTime() {
            service.performTask();
    }


    @Test
    public void testStart() {
        service.start();
        System.out.println("main ......");
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }
    
    
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}
