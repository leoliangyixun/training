/**
 * 
 */
package com.training.service.processor2;

/**
 * @author yangkai
 *
 */
interface Processor<R extends Processor<?, ?>, T> {
    void process(T t);

    R get();
}
