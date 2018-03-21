/**
 * 
 */
package com.training.service.processor7;

import com.training.yk.model.processor.Message;

/**
 * @author yangkai
 *
 */
public abstract class AbstractMessageProcessor<T extends Message> implements MessageProcessor<T> {

    protected MessageProcessor<T> next;

    @Override
    public void process(MessageChannel<T> channel, T message) {
        System.out.println("enter  abstract message processor >>>>>>");
        channel.send(message);
        System.out.println("<<<<<< out  abstract message processor");
    }

    @Override
    public void setNext(MessageProcessor<T> next) {
        this.next = next;
    }

    @Override
    public MessageProcessor<T> getNext() {
        return this.next;
    }

}
