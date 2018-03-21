package com.hujiang.push.apns.service;

import com.hujiang.push.apns.ApnsClient;
import com.hujiang.push.apns.connection.ApnsConnectionPool;
import com.hujiang.push.apns.connection.Connection;
import com.hujiang.push.apns.model.ApnsNotification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyApnsService extends AbstractApnsService {
    private ApnsClient client;
    private ApnsConnectionPool pool = new ApnsConnectionPool();
    
    public NettyApnsService(ApnsClient client) {
        this.client = client;
    }
    
    public void send(ApnsNotification notification) {
        log.info("todo");
    }
}
