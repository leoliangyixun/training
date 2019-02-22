package com.yangkai.hujiang.processor;


public interface MessageProcessor<T> {
	void process(T message);
}
