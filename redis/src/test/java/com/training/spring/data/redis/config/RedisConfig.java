/**
 * 
 */
package com.training.spring.data.redis.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author yangkai
 *
 */
@Configuration
@EnableConfigurationProperties
@ComponentScan({ "com.training.redis" })
@PropertySource({ "classpath:redis.properties" })
public class RedisConfig {
    
    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration().sentinel("192.168.177.90", 7001);
        return new JedisConnectionFactory(sentinelConfig);
    }
    
    @Bean
    RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory){
        
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }

}
