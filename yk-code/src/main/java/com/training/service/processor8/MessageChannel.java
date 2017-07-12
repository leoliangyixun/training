/**
 * 
 */
package com.training.service.processor8;

import com.training.yk.model.processor.Message;

/**
 * @author yangkai
 *
 */
public interface MessageChannel {
    
    boolean send(Message message);

}
