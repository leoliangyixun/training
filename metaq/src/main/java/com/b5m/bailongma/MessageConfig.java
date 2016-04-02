package com.b5m.bailongma;

import com.taobao.metamorphosis.client.MessageSessionFactory;
import com.taobao.metamorphosis.client.MetaClientConfig;
import com.taobao.metamorphosis.client.MetaMessageSessionFactory;
import com.taobao.metamorphosis.client.consumer.ConsumerConfig;
import com.taobao.metamorphosis.client.consumer.MessageConsumer;
import com.taobao.metamorphosis.client.producer.MessageProducer;
import com.taobao.metamorphosis.utils.ZkUtils.ZKConfig;
import org.apache.commons.lang.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author xiao.zhao
 * 
 */
public class MessageConfig {
	/**
	 * 消息服务器地址
	 */
	private String serverUrl;
	/**
	 * 消费者
	 */
	private ConcurrentHashMap<String, MessageConsumer> groupMessageConsumer = new ConcurrentHashMap<String, MessageConsumer>();
	/**
	 * 会话工厂
	 */
	private MessageSessionFactory sessionFactory;
	/**
	 * 生产者
	 */
	private MessageProducer producer;

	public MessageConfig(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public MessageSessionFactory getMessageSessionFactory() throws Exception {
		return this.getMessageSessionFactory(serverUrl);
	}

	public MessageSessionFactory getMessageSessionFactory(String url)
			throws Exception {
		if (sessionFactory != null) {
			return sessionFactory;
		}
		if (StringUtils.isBlank(url)) {
			return null;
		}
		final MetaClientConfig metaClientConfig = new MetaClientConfig();

		final ZKConfig zkConfig = new ZKConfig();

		zkConfig.zkConnect = serverUrl;

		metaClientConfig.setZkConfig(zkConfig);

		sessionFactory = new MetaMessageSessionFactory(metaClientConfig);

		return sessionFactory;

	}

	/**
	 * 获取MessageConsumer. <br>
	 * 默认fetchRunnerCount为CPU数目
	 */
	public MessageConsumer getMessageConsumer(String group) {
		return getMessageConsumer(group, 0);
	}
	
	/**
	 * 获取MessageConsumer. <br>
	 * @param fetchRunnerCount (fetchRunnerCount <= 0 使用默认配置）
	 */
	public MessageConsumer getMessageConsumer(String group, int fetchRunnerCount) {
		if (StringUtils.isBlank(group)) {
			return null;
		}
		MessageConsumer consumer = groupMessageConsumer.get(group);
		if (consumer != null) {
			return consumer;
		}
		try {
			sessionFactory = this.getMessageSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		ConsumerConfig consumerConfig = new ConsumerConfig(group);
		if(fetchRunnerCount > 0){
			consumerConfig.setFetchRunnerCount(fetchRunnerCount);
		}
		consumer = sessionFactory.createConsumer(consumerConfig);
		return consumer;
	}

	public MessageProducer getMessageProducer() throws Exception {
		if (producer != null) {
			return producer;
		}
		sessionFactory = this.getMessageSessionFactory();
		if (sessionFactory == null) {
			return null;
		}
		producer = sessionFactory.createProducer();

		return producer;

	}
}
