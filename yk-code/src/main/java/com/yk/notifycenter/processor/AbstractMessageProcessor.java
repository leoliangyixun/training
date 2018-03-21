/**
 * 
 */
package com.yk.notifycenter.processor;


import com.hujiang.notifycenter.tencent.service.MessageService;
import com.hujiang.notifycenter.tencent.utils.Preconditions;
import com.hujiang.notifycenter.tencent.validator.Validator;
import com.hujiang.notifycenter.tencent.vo.Message;
import com.hujiang.notifycenter.wechat.Executor;

import lombok.extern.slf4j.Slf4j;


/**
 * @author yangkai
 *
 */
@Slf4j
public abstract class AbstractMessageProcessor<T extends Message> implements MessageProcessor<T> {

    protected Validator<T> validator;
	protected MessageService<T> messageService;
    protected Executor<T> executor;

    public AbstractMessageProcessor() {

    }

	public AbstractMessageProcessor(Validator<T> validator, MessageService<T> messageService, Executor<T> executor) {
        this.validator = validator;
        this.messageService = messageService;
        this.executor = executor;
	}

	@Override
    public void process(T message) {
		Preconditions.checkNotNull(this.messageService, "message service is null, can't process message.");
    }
}
