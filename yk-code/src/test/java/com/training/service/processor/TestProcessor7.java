/**
 * 
 */
package com.training.service.processor;

import org.junit.Test;

import com.training.service.processor8.MessageChannel;
import com.training.service.processor8.MessageProcessor;
import com.training.service.processor8.QQMessageChannel;
import com.training.service.processor8.QQMessageProcessor;
import com.training.service.processor8.WechatMessageChannel;
import com.training.service.processor8.WechatMessageProcessor;
import com.training.yk.model.processor.QQMessage;
import com.training.yk.model.processor.WechatMessage;
/**
 * @author yangkai
 *
 */
public class TestProcessor7 {
    
    @Test
    public void test() {
        MessageProcessor<WechatMessage> wechatMessageProcessor = new WechatMessageProcessor();
        MessageChannel wechatMessageChannel = new WechatMessageChannel();
        
        MessageProcessor<QQMessage> qqMessageProcessor = new QQMessageProcessor();
        MessageChannel qqMessageChannel = new QQMessageChannel();
        
        wechatMessageProcessor.process(new WechatMessage());
        // do not work, fuck you little bitch
        wechatMessageProcessor.setNext(qqMessageProcessor);
    }

}
