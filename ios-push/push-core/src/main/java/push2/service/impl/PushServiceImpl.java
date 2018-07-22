/**
 * 
 */
package push2.service.impl;


import com.training.push.core.PushCenter;
import com.training.push.model.dto.Payload;
import com.training.push.service.PushService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import push2.service.PushService;

/**
 * @author yangkai
 *
 */
@Service
public class PushServiceImpl implements PushService {

    @Autowired
    private PushCenter<Payload> pushCenter;

    /**
     * 参数处理—> Resolver
     * 参数校验—> Validator
     * 推送类别处（all | tag | alias | user id |device id | device token） —> Handler
     * 推送策略(所有设备 | 指定设备|最近使用设备 | 经常使用设备 ......) -->Send Strategy
     *
     * asynchronous send notification, api ---> mq --> job --> apns
     * @param payload
     */
    @Override
    public void push(Payload payload) {
        //all, tag, alias, user,device, token
        pushCenter.execute(payload);

    }

    /**
     * synchronous send notification, direct to apns
     * @param payload
     */
    @Override
    public void send(Payload payload) {


    }

}
