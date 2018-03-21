package com.training.apns.api.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.training.apns")
public class PushApiRunner implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PushApiRunner.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
