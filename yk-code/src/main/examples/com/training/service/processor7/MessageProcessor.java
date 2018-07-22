/**
 * 
 */
package com.training.service.processor7;

import com.training.yk.model.processor.Message;

/**
 * @author yangkai
 *
 */
public interface MessageProcessor <T extends Message> {
    
     void  process (MessageChannel<T> channel, T t);
     
     void setNext(MessageProcessor<T> next);
     
     MessageProcessor<T> getNext();
}
