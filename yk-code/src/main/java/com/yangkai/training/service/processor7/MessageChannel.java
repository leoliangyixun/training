/**
 * 
 */
package com.training.service.processor7;

import com.training.yk.model.processor.Message;

/**
 * @author yangkai
 *
 */
public interface MessageChannel<T extends Message> {
    
    boolean send(T message);

}
