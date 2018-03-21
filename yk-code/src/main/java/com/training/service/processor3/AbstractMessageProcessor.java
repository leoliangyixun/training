/**
 * 
 */
package com.training.service.processor3;

import com.training.yk.model.processor.Message;

/**
 * @author yangkai
 *
 */
public abstract class AbstractMessageProcessor<R extends MessageProcessor<?,?>, T extends Message> implements MessageProcessor<R, T> {
    
    public MessageChannel<?> messageChannel;
    
    
    //public abstract boolean push(T message);

    @Override
    public void setChoice(MessageProcessor<?, ?> choice) {
      
        
    }


    @Override
    public MessageProcessor<?, ?> getChoice() {
       
        return null;
    }
    
   
    
}
