package com.training.fileupload.main;

import com.hujiang.basic.framework.rest.main.HJApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value ={ 
		"com.hujiang.basic.framework.rest",
		"com.hujiang.basic.framework.plugin.dfs",
		"com.hujiang.basic.framework.plugin.picidentify",
		"com.hujiang.fileupload"})
public class FileUploadStart implements CommandLineRunner {

    public static void main(String[] args) {
    	HJApplication.start(FileUploadStart.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}