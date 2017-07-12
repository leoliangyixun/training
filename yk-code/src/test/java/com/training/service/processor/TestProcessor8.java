/**
 * 
 */
package com.training.service.processor;

import org.junit.Test;

import com.training.service.processor8.MessageProcessor;
import com.training.service.processor8.QQMessageProcessor;
import com.training.service.processor8.WechatMessageProcessor;
import com.training.yk.model.processor.QQMessage;
import com.training.yk.model.processor.WechatMessage;

/**
 * @author yangkai
 *
 */
public class TestProcessor8 {
    
    @Test
    public void test() {
        MessageProcessor<WechatMessage> messageProcessor = new WechatMessageProcessor();
        //MessageChannel<WechatMessage> channel = new WechatMessageChannel();
        
        MessageProcessor<QQMessage> qqMessageProcessor = new QQMessageProcessor();
        //MessageChannel<QQMessage> qqChannel = new QQMessageChannel();
        // do not work.
        messageProcessor.setNext(qqMessageProcessor);
        
       messageProcessor.process(new WechatMessage());
    }

}
