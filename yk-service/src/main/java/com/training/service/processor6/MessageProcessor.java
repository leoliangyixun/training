/**
 * 
 */
package com.training.service.processor6;

import com.training.yk.model.processor.Message;

/**
 * @author yangkai
 *
 */
public interface MessageProcessor {
    
    <T extends Message> void  process (MessageChannel<T> channel, T message);
    // error
   // <T> void  process (MessageChannel<T> channel);
    // error
    //<T> void  process (MessageChannel<T extends Message> channel);
    //error
    //<T extends Message> void  process (MessageChannel<T extends Message> channel);
    
    
    void setNext(MessageProcessor next);
    
    MessageProcessor getNext();
    
}
