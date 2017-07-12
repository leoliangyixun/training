/**
 * 
 */
package com.yk.processor2;


import com.yk.processor.model.Message;

import lombok.extern.slf4j.Slf4j;


/**
 * @author yangkai
 *
 */
@Slf4j
public abstract class AbstractMessageProcessor<T extends Message> implements MessageProcessor<T> {

    protected MessageProcessor<T> next;

	public abstract boolean support(T message);

	public abstract void send(T message);

    @Override
    public void process(T message) {

    	if (support(message)) {
    		send(message);
    	} else {
    		if (next != null) {
    			next.process(message);
    		}
    	}
    }

    public MessageProcessor<T> next(MessageProcessor<T> next) {
        this.next = next;
        return this;
    }

	

    
}
