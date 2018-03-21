package com.training.rabbitmq.config;

import java.nio.charset.StandardCharsets;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.hujiang.basic.framework.core.config.BaseProperties;

@Configuration
public class RabbitMQConfig {  
  
    public static final String EXCHANGE   = "spring-boot-exchange";  
    public static final String ROUTINGKEY = "spring-boot-routingKey";  
  
    //@Bean  
    public ConnectionFactory connectionFactory() {  
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();  
        connectionFactory.setAddresses("192.168.36.77");  
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("us_chao");  
        connectionFactory.setPassword("us_chao");  
        connectionFactory.setVirtualHost("/");  
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;  
    }  
    
    @Bean
    public BaseProperties properties(){
        BaseProperties baseProperties = new BaseProperties(); 
        baseProperties.setLocation(new ClassPathResource("application.properties"));
        baseProperties.setFileEncoding(StandardCharsets.UTF_8.name());
        return baseProperties;
    }
} 