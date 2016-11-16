package com.training.service.processor5;


public interface MessageProcessor<R extends Command, T> {

    void process(T message);

    void setNext(R next);
    
    R getNext();

}
