package com.training.service.processor3;

import com.training.yk.model.processor.Message;

public interface MessageProcessor<R extends MessageProcessor<?, ?>, T extends Message> {

    void process(T message);

    void setChoice(MessageProcessor<?, ?> choice);

    MessageProcessor<?, ?> getChoice();
}
