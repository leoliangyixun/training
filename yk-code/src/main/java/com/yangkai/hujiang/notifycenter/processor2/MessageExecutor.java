package com.yangkai.hujiang.notifycenter.processor2;

import com.hujiang.notifycenter.tencent.validator.Validator;
import com.hujiang.notifycenter.wechat.Executor;

public class MessageExecutor<T> {

    protected Validator<T> validator;
    protected MessageProcessor<T> messageProcessor;
    protected Executor<T> executor;

    public MessageExecutor(Validator<T> validator, MessageProcessor<T> messageProcessor, Executor<T> executor) {
        this.validator = validator;
        this.messageProcessor = messageProcessor;
        this.executor = executor;
    }

    public void execute(T message) {
        boolean valid = true;
        try {
            valid = this.validator.validate(message);

        } catch (Exception e) {

        }

        if (valid) {
            this.messageProcessor.process(message);
        }
    }
}
