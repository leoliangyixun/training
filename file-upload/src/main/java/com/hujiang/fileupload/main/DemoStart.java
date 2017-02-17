package com.hujiang.fileupload.main;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.hujiang.basic.framework.rest.main.HJApplication;

@SpringBootApplication
@ComponentScan(value ={ "com.hujiang.basic.framework.rest", "com.hujiang.basic.framework.plugin.dfs", "com.hujiang.fileupload"})
public class DemoStart implements CommandLineRunner {

	
    public static void main(String[] args) {
    	HJApplication.start(DemoStart.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    	
    }
}
