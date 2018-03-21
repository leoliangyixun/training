/**
 * 
 */
package com.training.service.processor6;

import com.training.yk.model.processor.Message;
import com.training.yk.model.processor.WechatMessage;

/**
 * @author yangkai
 *
 */
public class WechatMessageProcessor extends AbstractMessageProcessor {

    public WechatMessageProcessor() {
      
    }

    @Override
    public <T extends Message> void process(MessageChannel<T> channel, T message) {
     
        super.process(channel, message);
        System.out.println("in wechat message processor");  

    }

    public static class WechatMessageChannel implements MessageChannel<WechatMessage> {

        @Override
        public boolean send(WechatMessage message) {
            System.out.println("send wechat message");
            return false;
        }
        
    }
    
}
