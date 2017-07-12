package com.yk.notifycenter.processor;

public interface MessageProcessor<T> {
	void process(T message);
}
