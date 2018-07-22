/**
 * 
 */
package push.service;

import com.hujiang.notifycenter.push.model.dto.Payload;

/**
 * @author yangkai
 *
 */
public interface PushService {
    //send notification to mq
    void push(Payload payload);
    //send notification to apns
    void send(Payload payload);

}
