package com.yk.concurrent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    MyAsyncService service;

    @Scheduled(cron = "*/1 * * * * ? ")
    public void reportCurrentTime() {
            service.performTask();
    }
}