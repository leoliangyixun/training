/**
 * 
 */
package com.training.service.processor;

import com.training.yk.model.processor.WechatMessage;

/**
 * @author yangkai
 *
 */


public class WechatMessageProcessor extends AbstractMessageProcessor<WechatMessageProcessor, WechatMessage> {

    
    public WechatMessageProcessor() {
        
    }

    public static class WechatPushChannel implements MessageChannel<WechatMessage> {

        @Override
        public boolean send(WechatMessage message) {
          
            return false;
        }

    }

    @Override
    public void process(WechatMessage message) {
        // shit
       this.setNext(null);

        
    }



}
