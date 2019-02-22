package com.yangkai.hujiang.notifycenter.processor;

public interface MessageProcessor<T> {
	void process(T message);
}
