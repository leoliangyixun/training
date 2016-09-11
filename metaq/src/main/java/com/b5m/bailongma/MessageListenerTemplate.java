package com.b5m.bailongma;

import com.alibaba.fastjson.JSON;
import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.consumer.MessageListener;

import java.util.concurrent.Executor;

public abstract class MessageListenerTemplate<T extends Object> implements
		MessageListener {
	
	Thread thread = new Thread();
	
	public abstract void recieveData(Message Message, T data);

	public void recieveMessages(Message message) {
		String data = new String(message.getData());
		@SuppressWarnings("unchecked")
		T objData  = (T)JSON.parse(data);
		recieveData(message, objData);
	}

	public Executor getExecutor() {
		return new Executor() {
			@Override
			public void execute(Runnable command) {
				command.run();
			}
		};
	}
}
