/**
 *
 */
package com.yk.spring.boot.demo;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yk.spring.boot.demo.component.ScheduleTask;
import com.yk.spring.boot.demo.config.TestConfig;

import lombok.extern.slf4j.Slf4j;


/**
 * @author yangkai
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan({"com.yk.spring.boot.demo.component"})
@Import(value = {TestConfig.class})// can't work if not import
public class SpringTestCase {
    
    @Resource
    private ScheduleTask scheduleTask;

    @Test
    public void start() throws Exception {
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        log.info("{}");
    }




}
