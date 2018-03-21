package com.b5m.bailongma;

import com.alibaba.fastjson.JSON;
import com.taobao.metamorphosis.Message;
import com.taobao.metamorphosis.client.consumer.MessageConsumer;
import com.taobao.metamorphosis.client.consumer.MessageListener;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.client.producer.SendResult;
import com.taobao.metamorphosis.exception.MetaClientException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * MetaQ消息发送工具<br>
 * *每个serverUrl对应的MetaQLogHelper都是单例的
 */
public class MetaQLogHelper implements MessageQueue {
	
	private Logger logger = Logger.getLogger(MetaQLogHelper.class);
	
	private final MessageConfig config;
	
	private final MessageProducer producer;
	
	public MetaQLogHelper(final String serverUrl, final String[] topics){
//		 初始化producer<br>
//		 初始化比较耗时，必须在调用该实例前被初始化
		try {
			config = new MessageConfig(serverUrl);
			producer = config.getMessageProducer();
			//发布话题
			if(null == topics || topics.length <1) return;
			
			for(String topic : topics){
				producer.publish( topic );
			}
		} catch (Exception e) {
			throw new RuntimeException("MetaQ初始化出错！", e);
		}
	}
	
	/**
	 * 发送消息，线程安全的方法
	 * @param topic
	 * @param msg
	 */
	public <T extends Object>  void sendMessage(final String topic, final T msg){

		if (StringUtils.isBlank(topic) || null == msg) {
			return;
		}
		
		MessageThreadPool.instance.pool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String data = serialization(msg);
					if(null != data){
						SendResult sendResult = producer.sendMessage(new Message(topic, data.getBytes()));
						if(logger.isInfoEnabled()){
							logger.info("[sendMessage]SendResult:" + sendResult.isSuccess());
						}
					}else{
						logger.warn("[sendMessage]接收到空消息.");
					}
				} catch (MetaClientException e) {
					logger.warn("[sendMessage]", e);
				} catch (InterruptedException e) {
					logger.warn("[sendMessage]", e);
				}

			}
		});
	}

	public void subscribe(String topic, MessageListener listener) {
		MessageConsumer consumer = config.getMessageConsumer("group");
		// subscribe topic
		try {
			consumer.subscribe(topic, 1024 * 1024, listener);
			// complete subscribe
			consumer.completeSubscribe();
		} catch (MetaClientException e) {
			logger.warn("[subscribe]", e);
		}
	}
	
	public void subscribe(String topic, MessageListener listener, String group, int fetchRunnerCount) {
		MessageConsumer consumer = config.getMessageConsumer(group, fetchRunnerCount);
		// subscribe topic
		try {
			consumer.subscribe(topic, 1024 * 1024, listener);
			// complete subscribe
			consumer.completeSubscribe();
		} catch (MetaClientException e) {
			logger.warn("[subscribe]", e);
		}
	}
	
	private <T extends Object> String serialization(T obj) {
		if (obj == null) {
			return null;
		}
		String v = null;

		if (obj instanceof String) {
			v = (String) obj;
		} else {
			v = JSON.toJSONString(obj);
		}
		return v;
	}
			

}
