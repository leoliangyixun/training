package com.yk.processor;


public interface MessageProcessor<T> {
	void process(T message);
}
