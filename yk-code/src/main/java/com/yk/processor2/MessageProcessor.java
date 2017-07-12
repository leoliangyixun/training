package com.yk.processor2;


public interface MessageProcessor<T> {
	void process(T message);
}
