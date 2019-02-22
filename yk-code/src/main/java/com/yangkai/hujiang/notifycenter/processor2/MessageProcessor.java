package com.yangkai.hujiang.notifycenter.processor2;

public interface MessageProcessor<T> {
	void process(T message);
}
