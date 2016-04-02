/**
 * 
 */
package com.curator.recipes;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年4月1日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年4月1日       jiqingchuan          1.0             Why & What is modified
 */
public class CuratorClient {
	private static final String PATH = "/example/basic";

	public CuratorClient() {
		
	}

	public static void main(String[] args) throws Exception {
		CuratorFramework client = null;
		TestingServer server = new TestingServer();
		String connStr = server.getConnectString();
		System.out.println("connStr: " + server.getConnectString());
		
/*		 ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
	   
		 client = CuratorFrameworkFactory.newClient("172.16.11.143:2181", retryPolicy);
		
		 client.create().creatingParentsIfNeeded().forPath(PATH, "jiqingchuan".getBytes());*/
		 
		 
		 
	}

}
