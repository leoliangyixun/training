/**
 * 
 */
package com.training.service.processor7;

import com.training.yk.model.processor.WechatMessage;

/**
 * @author yangkai
 *
 */
public class WechatMessageProcessor extends AbstractMessageProcessor<WechatMessage> {

    public WechatMessageProcessor() {
      
    }

    
    @Override
    public void process(MessageChannel<WechatMessage> channel, WechatMessage message) {
        System.out.println("enter  wechat message processor  >>>>>>");
        super.process(channel, message);
        System.out.println(" <<<<<< out  wechat message processor");
        
        if (this.next != null ) {
            this.next.process(channel, message);
        }
        
    }
    
    public static class WechatMessageChannel implements MessageChannel<WechatMessage> {

        @Override
        public boolean send(WechatMessage message) {
            System.out.println(" send in wechat message channel");
            return false;
        }
        
    }
    
}
