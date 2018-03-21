package com.training.service.processor2;

import com.training.yk.model.processor.Message;

interface MessageProcessor extends Processor<MessageProcessor, Message>{
    
    void next(MessageProcessor choice);

    MessageProcessor next();

}
