package com.training.service.processor8;

import com.training.yk.model.processor.Message;

public class QQMessageChannel implements MessageChannel {

    @Override
    public boolean send(Message message) {
        System.out.println("send qq message");
        return false;
    }
    
}