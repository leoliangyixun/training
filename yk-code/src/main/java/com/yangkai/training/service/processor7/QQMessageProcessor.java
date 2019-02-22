/**
 * 
 */
package com.training.service.processor7;

import com.training.yk.model.processor.QQMessage;

/**
 * @author yangkai
 *
 */
public class QQMessageProcessor extends AbstractMessageProcessor<QQMessage> {

    public QQMessageProcessor() {
      
    }

    
    @Override
    public void process(MessageChannel<QQMessage> channel, QQMessage message) {
        System.out.println("enter  qq message processor  >>>>>>");
        super.process(channel, message);
        System.out.println(" <<<<<< out  qq message processor");
        
    }
    
    public static class QQMessageChannel implements MessageChannel<QQMessage> {

        @Override
        public boolean send(QQMessage message) {
            System.out.println(" send in qq message channel");
            return false;
        }
        
    }
    
}
