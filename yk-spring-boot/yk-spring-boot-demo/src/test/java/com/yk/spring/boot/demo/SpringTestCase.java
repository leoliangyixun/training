/**
 *
 */
package com.yk.spring.boot.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yk.spring.boot.demo.config.TestConfig;

import lombok.extern.slf4j.Slf4j;


/**
 * @author yangkai
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan({"com.yk.spring.boot.demo"})
@Import(value = {TestConfig.class})// can't work if not import
public class SpringTestCase {
    public ApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfig.class);


    @Test
    public void start() {
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        log.info("{}");
    }




}
