/**
 * 
 */
package com.training.curator.recipes;

import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingServer;
import org.apache.curator.test.TestingZooKeeperServer;
import org.apache.curator.utils.CloseableUtils;

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

		TestingServer server = new TestingServer();
		CuratorFramework client = CuratorFrameworkFactory.newClient(server.getConnectString(),  new ExponentialBackoffRetry(1000, 3));
		server.start();
		client.start();
		client.create().creatingParentsIfNeeded().forPath(PATH, "main".getBytes());
		client.create().creatingParentsIfNeeded().forPath(PATH+"/jiqingchuan", "jiqingchuan".getBytes());
		client.create().creatingParentsIfNeeded().forPath(PATH+"/leoliangyixun", "leoliangyixun".getBytes());
		client.create().creatingParentsIfNeeded().forPath(PATH+"/shit", "shit".getBytes());
	
		List<String> paths = client.getChildren().forPath(PATH);
		System.out.println(paths);
		client.close();
		server.stop();
/*		 
        TestingCluster cluster = new TestingCluster(5);
        
        System.out.println("cluster connStr: " + cluster.getConnectString());
        System.out.println("cluster instances: " + cluster.getInstances());*/
        
        
/*		TestingCluster cluster = new TestingCluster(5);
		cluster.start();
		for (TestingZooKeeperServer server : cluster.getServers()) {
			System.out.println(server.getInstanceSpec());

		}
		cluster.stop();
		CloseableUtils.closeQuietly(cluster);*/
		
		

       
	}

}
