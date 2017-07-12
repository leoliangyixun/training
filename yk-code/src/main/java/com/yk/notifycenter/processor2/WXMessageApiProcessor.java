/**
 * 
 */
package com.hujiang.notifycenter.tencent.processor;

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


	@Override
	public void process(Message message) {
		super.process(message);
		//TODO for future
	}


}
