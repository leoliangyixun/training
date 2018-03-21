package com.hujiang.push.apns.service;

import com.hujiang.push.apns.ApnsClient;
import com.hujiang.push.apns.connection.Connection;
import com.hujiang.push.apns.model.ApnsNotification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApnsServiceImpl extends AbstractApnsService {
    private ApnsClient client;
    private Connection connection;
    
    public ApnsServiceImpl(ApnsClient client) {
        this.client = client;
    }
    
    public void send(ApnsNotification notification) {
        log.info("todo");
    }
}
