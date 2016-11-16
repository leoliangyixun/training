/**
 * 
 */
package com.training.service.processor4;

import com.training.yk.model.processor.Message;

/**
 * @author yangkai
 *
 */
public abstract class AbstractMessageProcessor<R, T extends Message> implements MessageProcessor<R, T> {
    
    private R next;
    @Override
    public void process(T message) {
       
        
    }

    @Override
    public void setNext(R next) {
       this.next = next;
       // can't find any method if R not extends one
       
        
    }

    @Override
    public R getNext() {
        // TODO Auto-generated method stub
        return null;
    }
    
    

}
