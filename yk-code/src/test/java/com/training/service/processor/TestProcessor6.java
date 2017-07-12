/**
 * 
 */
package com.training.service.processor;

import org.junit.Test;

import com.training.service.processor6.MessageChannel;
import com.training.service.processor6.MessageProcessor;
import com.training.service.processor6.QQMessageProcessor;
import com.training.service.processor6.WechatMessageProcessor;
import com.training.service.processor6.WechatMessageProcessor.WechatMessageChannel;
import com.training.service.processor6.QQMessageProcessor.QQMessageChannel;
import com.training.yk.model.processor.QQMessage;
import com.training.yk.model.processor.WechatMessage;

/**
 * @author yangkai
 *
 */
public class TestProcessor6 {
    
    @Test
    public void test() {
        MessageProcessor messageProcessor = new WechatMessageProcessor();
        MessageChannel<WechatMessage> channel = new WechatMessageChannel();
        
        MessageProcessor qqMessageProcessor = new QQMessageProcessor();
        MessageChannel<QQMessage> qqChannel = new QQMessageChannel();
      
        messageProcessor.setNext(qqMessageProcessor);
        messageProcessor.process(channel, new WechatMessage());
    }

}
