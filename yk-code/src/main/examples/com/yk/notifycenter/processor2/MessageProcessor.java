package com.hujiang.notifycenter.tencent.processor;

public interface MessageProcessor<T> {
	void process(T message);
}
