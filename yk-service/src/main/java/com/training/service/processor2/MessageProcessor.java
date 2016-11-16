package com.training.service.processor2;

import com.training.yk.model.processor.Message;

interface MessageProcessor<R extends MessageProcessor<?,?>, T extends Message> extends Processor<R, T>{
    
    void next(R choice);

    R next();

}
