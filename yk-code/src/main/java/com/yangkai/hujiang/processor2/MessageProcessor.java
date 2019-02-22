package com.yangkai.hujiang.processor2;


public interface MessageProcessor<T> {
	void process(T message);
}
