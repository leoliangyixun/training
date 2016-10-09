/**
 * 
 */
package com.training.spring.data.redis;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.training.spring.data.redis.config.RedisConfig;

/**
 * @author yangkai
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RedisConfig.class})
//@Import(value = { RedisConfig.class })
public class TestConnection {

    //@Autowired
    private RedisTemplate<?, ?> redisTemplate;

    @Test
    public void test() {
       Assert.assertNull(redisTemplate);
    }

}
