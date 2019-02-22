/**
 * 
 */
package com.training.service.processor2;

import com.training.yk.model.processor.Message;
import com.training.yk.model.processor.WechatMessage;

/**
 * @author yangkai
 *
 */


public class MessageProcessorImpl extends AbstractProcessor<MessageProcessor, Message> implements MessageProcessor {

    
    public MessageProcessorImpl() {
        
    }
    


    
    public static class WechatPushChannel implements MessageChannel<WechatMessage> {

        @Override
        public boolean send(WechatMessage message) {
          
            return false;
        }
        
        
       
        
    }




    @Override
    public void next(MessageProcessor choice) {
        // TODO Auto-generated method stub
        
    }




    @Override
    public MessageProcessor next() {
        // TODO Auto-generated method stub
        return null;
    }





}
