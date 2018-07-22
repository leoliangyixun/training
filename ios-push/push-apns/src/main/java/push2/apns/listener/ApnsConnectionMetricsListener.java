/**
 * 
 */
package push2.apns.listener;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangkai
 *
 */
@Slf4j
public class ApnsConnectionMetricsListener implements ConnectionMetricsListener {

    public ApnsConnectionMetricsListener() {
     
    }

    @Override
    public void onCreate() {
        log.info("create a new apns connection to connection pool");
    }

    @Override
    public void onRemove() {
        log.info("remove an apns connection from connection pool");
    }

    @Override
    public void onFail() {
        log.info("create a new apns connection failed");
    }

    @Override
    public void onClose() {
        
    }

}
