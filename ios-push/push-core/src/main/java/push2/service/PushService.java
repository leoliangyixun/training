/**
 * 
 */
package push2.service;


import com.training.push.model.dto.Payload;

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
