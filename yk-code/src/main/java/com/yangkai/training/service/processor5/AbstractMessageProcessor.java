/**
 * 
 */
package com.training.service.processor5;

import com.training.yk.model.processor.Message;

/**
 * @author yangkai
 *
 */
public abstract class AbstractMessageProcessor<R extends Command, T extends Message> implements MessageProcessor<R, T> {
    
    private R next;
    @Override
    public void process(T message) {
       
        
    }

    @Override
    public void setNext(R next) {
       this.next = next;
       next.execute();
       
        
    }

    @Override
    public R getNext() {
        // TODO Auto-generated method stub
        return null;
    }
    
    

}
