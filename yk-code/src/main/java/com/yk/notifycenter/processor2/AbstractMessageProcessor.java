/**
 * 
 */
package com.hujiang.notifycenter.tencent.processor;


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



    public AbstractMessageProcessor() {

    }

	@Override
    public void process(T message) {

    }
}
