package push2.apns.service;


import com.training.push.apns.ApnsClient;
import com.training.push.apns.connection.Connection;
import com.training.push.apns.model.ApnsNotification;

import lombok.extern.slf4j.Slf4j;

import push2.apns.connection.Connection;
import push2.apns.model.ApnsNotification;

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
