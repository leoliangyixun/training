package com.training.service.processor4;


public interface MessageProcessor<R, T> {

    void process(T message);

    void setNext(R next);
    
    R getNext();

}
