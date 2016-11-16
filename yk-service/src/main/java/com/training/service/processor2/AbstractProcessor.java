/**
 * 
 */
package com.training.service.processor2;

/**
 * @author yangkai
 *
 */
public abstract class AbstractProcessor<R extends Processor<?,?>, T> implements Processor<R, T> {
    
    public MessageChannel<?> messageChannel;

    @Override
    public void process(T t) {
        
    }

    @Override
    public R get() {
        return null;
    }
    
    
    //public abstract boolean push(T message);

    
   
    
}
