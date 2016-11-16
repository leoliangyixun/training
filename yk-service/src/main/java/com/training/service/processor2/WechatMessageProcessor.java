/**
 * 
 */
package com.training.service.processor2;

import com.training.yk.model.processor.WechatMessage;

/**
 * @author yangkai
 *
 */


public class WechatMessageProcessor extends AbstractProcessor<WechatMessageProcessor, WechatMessage> implements MessageProcessor<WechatMessageProcessor,WechatMessage> {

    
    public WechatMessageProcessor() {
        
    }
    


    
    public static class WechatPushChannel implements MessageChannel<WechatMessage> {

        @Override
        public boolean send(WechatMessage message) {
          
            return false;
        }
        
        
       
        
    }





}
