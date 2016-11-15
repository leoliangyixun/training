package com.training.test;

/**
 * Created by yangkai on 2016/11/11.
 */
public interface MessageProcessor<T> extends Processor{

    void send();
    void check(T t);
}
