/**
 * 
 */
package com.training.service.processor6;

import com.training.yk.model.processor.Message;
import com.training.yk.model.processor.QQMessage;

/**
 * @author yangkai
 *
 */
public class QQMessageProcessor extends AbstractMessageProcessor {

    public QQMessageProcessor() {
      
    }

    @Override
    public <T extends Message> void process(MessageChannel<T> channel, T message) {
      
        super.process(channel, message);
        System.out.println("in qq message processor");
    }

    public static class QQMessageChannel implements MessageChannel<QQMessage> {

        @Override
        public boolean send(QQMessage message) {
            System.out.println("send qq message");
            return false;
        }
        
    }
    
}
