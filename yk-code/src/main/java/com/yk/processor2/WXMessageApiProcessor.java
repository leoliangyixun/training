/**
 * 
 */
package com.yk.processor2;

import com.yk.processor.model.*;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangkai
 *
 */
@Slf4j
public class WXMessageApiProcessor extends AbstractMessageProcessor<Message> {


    public WXMessageApiProcessor() {
        
    }

	@Override
	public void send(Message message) {
		log.info("send wechat message to mq for those users: {}", message.getBody().getTouser());
	}

	@Override
	public boolean support(Message message) {
		boolean support = false;
		switch (message.getSendType()) {
			case WX:
			support = true;
			break;
		default:
			support = false;
			break;
		}
		return support;
	}
}
