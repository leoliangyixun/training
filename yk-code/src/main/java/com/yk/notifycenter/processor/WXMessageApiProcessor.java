/**
 * 
 */
package com.yk.notifycenter.processor;

import com.hujiang.notifycenter.tencent.service.MessageService;
import com.hujiang.notifycenter.tencent.validator.Validator;
import com.hujiang.notifycenter.tencent.vo.Message;
import com.hujiang.notifycenter.wechat.Executor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangkai
 *
 */
@Slf4j
public class WXMessageApiProcessor extends AbstractMessageProcessor<Message> {

	public WXMessageApiProcessor(Validator<Message> validator, MessageService<Message> messageService, Executor<Message> executor) {
		super(validator, messageService, executor);
	}

	@Override
	public void process(Message message) {
		super.process(message);
		boolean valid = this.validator.validate(message);
		if (valid) {
			this.messageService.send(message);
		}
	}


}
