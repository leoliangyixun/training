/**
 * 
 */
package com.training.validator.common;

/**
 * @author yangkai
 *
 */
public interface Caller<T> {

    void execute(Object... input);


    T getResult();


    T executeAndGetResult(Object... input);

}
