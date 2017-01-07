package com.yk.notifycenter.processor;

import com.hujiang.notifycenter.tencent.core.Executor;
import com.hujiang.notifycenter.tencent.service.MessageService;
import com.hujiang.notifycenter.tencent.validator.Validator;

public class MessageExecutor<T> {

    protected Validator<T> validator;
    protected MessageService<T> messageService;
    protected Executor<T> executor;

    public MessageExecutor(Validator<T> validator, MessageService<T> messageService, Executor<T> executor) {
        this.validator = validator;
        this.messageService = messageService;
        this.executor = executor;
    }

/*	public void execute(MessageProcessor<T> messageProcessor, T message) {
		boolean passed = true;
		if (message != null) {
			if (CollectionUtils.isNotEmpty(messageValidators)) {
				for (Validator<T> v : messageValidators) {
					try {
						passed = v.validate(validateContext, message);
					} catch (Exception exception) {
						v.onException(validateContext, exception, message);
					}
				}
			}

			if (passed) {
				try {
					messageProcessor.process(message);
				} catch (Exception e) {
					messageProcessor.onError(message, messageContext, e);
				}
				
			}
		}
	}*/
}
