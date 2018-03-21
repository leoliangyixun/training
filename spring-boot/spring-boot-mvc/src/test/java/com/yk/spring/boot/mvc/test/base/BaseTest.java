/**
 * 
 */
package com.yk.spring.boot.mvc.test.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yk.spring.boot.mvc.test.config.TestConfig;

/**
 * @author yangkai
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
//@ComponentScan({ /*"com.hujiang.basic.framework", */"com.hujiang.messagecenter.tencent.service", "com.hujiang.messagecenter.tencent.cache"})
@Import(value = {TestConfig.class})
public class BaseTest {

    @Test
    public void start() {

    }

}
