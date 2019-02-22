/**
 * 
 */
package com.yangkai.hujiang.processor;


import com.yk.processor.model.Message;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangkai
 *
 */
@Slf4j
public class QQMessageApiProcessor extends AbstractMessageProcessor<Message> {


    public QQMessageApiProcessor() {
        
    }
	@Override
	public void send(Message message) {
		log.info("send qq message to mq for those users: {}", message.getBody().getTouser());
	}

	@Override
	public boolean support(Message message) {
		boolean support = false;
		switch (message.getSendType()) {
		case QQ:
			support = true;
			break;
		default:
			support = false;
			break;
		}
		return support;
	}

}
