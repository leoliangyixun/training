package push2.apns.service;

import com.training.push.apns.ApnsClient;
import com.training.push.apns.connection.ApnsConnectionPool;
import com.training.push.apns.model.ApnsNotification;

import lombok.extern.slf4j.Slf4j;

import push2.apns.connection.ApnsConnectionPool;
import push2.apns.model.ApnsNotification;

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
