package com.b5m.bailongma;

import com.taobao.metamorphosis.client.consumer.MessageListener;

public interface MessageQueue {
	
	public <T extends Object>  void sendMessage(final String topic, final T msg);
	
	public void subscribe(String topic, MessageListener listener) ;
	
	/**
	 * 订阅消息<br>
	 * @param fetchRunnerCount (fetchRunnerCount <= 0 使用默认配置）
	 */
	public void subscribe(String topic, MessageListener listener, String group, int fetchRunnerCount);
}
