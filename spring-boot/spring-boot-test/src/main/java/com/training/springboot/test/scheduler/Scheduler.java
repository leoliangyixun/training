package com.training.springboot.test.scheduler;

import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yangkai
 * @date 2019-03-01
 * @email yangkai@hujiang.com
 * @description
 */
@Slf4j
@Component
public class Scheduler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //每隔2秒执行一次
    //每隔1秒执行一次
    @Scheduled(fixedRate = 1000)
    public void scheduler1() {
        log.info("每隔1秒执行一次：{}", dateFormat.format(new Date()));
    }

    @Scheduled(fixedRate = 2000)
    public void scheduler2() {
        log.info("每隔2秒执行一次：{}", dateFormat.format(new Date()));
    }
}
