/**
 * 
 */
package com.training.springboot.test.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangkai
 *
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class MyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
