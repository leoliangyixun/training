/**
 * 
 */
package com.training.service.processor3;

import com.training.yk.model.processor.Message;

/**
 * @author yangkai
 *
 */
public interface MessageChannel<T extends Message> {
    
    boolean send(T message);

}
