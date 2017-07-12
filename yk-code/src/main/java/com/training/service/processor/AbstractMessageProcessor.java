/**
 * 
 */
package com.training.service.processor;

import com.training.yk.model.processor.Message;

/**
 * @author yangkai
 *
 */
public abstract class AbstractMessageProcessor<R extends MessageProcessor<?,?>, T extends Message> implements MessageProcessor<R, T> {
    
    public MessageChannel<?> messageChannel;
    
    
    //public abstract boolean push(T message);

    @Override
    public void setNext(R choice) {
      
        
    }


    @Override
    public R getNext() {
       
        return null;
    }
    
   
    
}
