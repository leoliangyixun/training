package com.training.springboot.mvc.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackages = "com.training.springboot.mvc")
public class AppRunner implements CommandLineRunner {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(AppRunner.class, args);

/*        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }*/
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("running......");
    }
}
