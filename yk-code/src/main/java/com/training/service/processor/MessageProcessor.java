package com.training.service.processor;

import com.training.yk.model.processor.Message;

public interface MessageProcessor<R extends MessageProcessor<?, ?>, T extends Message> {

    void process(T message);

    void setNext(R choice);

    R getNext();
}
