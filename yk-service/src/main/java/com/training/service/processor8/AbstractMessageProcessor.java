/**
 * 
 */
package com.training.service.processor8;

import com.training.yk.model.processor.Message;

/**
 * @author yangkai
 * @param <T>
 *
 */
public abstract class AbstractMessageProcessor<T extends Message> implements MessageProcessor<T> {

    
    protected MessageProcessor<T> next;
    protected MessageChannel channel;
    
    @Override
    public  void process(T message) {
        System.out.println("in abstract message processor");
        channel.send(message);
        if (this.channel != null) {
            this.channel.send(message);
        }
        
        if (this.next != null) {
            this.next.process(message);
        }
    }
    
/*    @Override
    public  void process(T message) {
        System.out.println("in abstract message processor");

        if (this.channel != null) {
            this.channel.send(message);
        }
        
        if (this.next != null) {
            this.next.process(message);
        }
    }
*/
    @Override
    @SuppressWarnings("unchecked")
    public <M extends Message> void setNext(MessageProcessor<M> next) {
        this.next = (MessageProcessor<T>) next;
    }

    @Override
    public MessageProcessor<? extends Message> getNext() {
        return this.next;
    }

    public MessageChannel getChannel() {
        return channel;
    }

    public void setChannel(MessageChannel channel) {
        this.channel = channel;
    }
}
