/**
 * 
 */
package com.training.rabbitmq.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.hujiang.basic.framework.plugin.mq.MqConfig;



/**
 * @author yangkai
 *
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(value={"com.training.rabbitmq"})
@Import(value={MqConfig.class})
public class RabbitMQAppication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(RabbitMQAppication.class, args);
    }

}
