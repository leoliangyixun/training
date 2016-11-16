package com.training.test;

import com.training.yk.model.processor.WechatMessage;

/**
 * Created by yangkai on 2016/11/11.
 */
public class WechatMessageProcessor implements MessageProcessor<WechatMessage> {

    @Override
    public void send() {

    }

    @Override
    public void check(WechatMessage wechatMessage) {

    }

    @Override
    public void process() {
        this.check(null);
        this.send();
    }
}
