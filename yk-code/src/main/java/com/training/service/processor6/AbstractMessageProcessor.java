/**
 * 
 */
package com.training.service.processor6;

import com.training.yk.model.processor.Message;

/**
 * @author yangkai
 *
 */
public abstract class AbstractMessageProcessor implements MessageProcessor {

    
    protected MessageProcessor next;

    @Override
    public <T extends Message> void process(MessageChannel<T> channel, T message) {
        System.out.println("in abstract message processor");
        channel.send(message);
        if (this.next != null) {
            this.next.process(channel, message);
        }
    }

    @Override
    public void setNext(MessageProcessor next) {
        this.next = next;
    }

    @Override
    public MessageProcessor getNext() {
        return this.next;
    }
    

}
