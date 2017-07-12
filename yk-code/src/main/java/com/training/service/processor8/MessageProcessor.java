/**
 * 
 */
package com.training.service.processor8;

import com.training.yk.model.processor.Message;

/**
 * @author yangkai
 *
 */
public interface MessageProcessor<T extends Message> {
    
    void  process (T message);
    
    <M extends Message>void setNext(MessageProcessor<M> next);
    
    MessageProcessor<? extends Message> getNext();
    
}
