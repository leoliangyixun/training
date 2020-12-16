/**
 * 
 */
package com.training.springboot.test.main;

import com.training.springboot.test.scheduler.Scheduler;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@EnableScheduling
@Import(Scheduler.class)
public class AppStart implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }

}
