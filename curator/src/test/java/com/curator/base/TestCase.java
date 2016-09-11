/**
 * 
 */
package com.curator.base;

import java.io.IOException;
import java.net.BindException;

import org.apache.curator.test.TestingCluster;
import org.apache.curator.test.TestingServer;
import org.junit.After;
import org.junit.Before;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年5月19日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年5月19日       jiqingchuan          1.0             Why & What is modified
 */
public class TestCase {
    
    protected TestingServer server;
    protected TestingCluster cluster;
    
    @Before
    public void setup() throws Exception {
        System.out.println("---------------------setup()----------------------");
        if (server == null) {
            try {
                server = new TestingServer();
                cluster = new TestingCluster(5);
            } catch (BindException e) {
                e.printStackTrace();
                server = null;
            }
        }
    }

    @After
    public void teardown() throws Exception {
        System.out.println("---------------------teardown()----------------------");
        if (server != null) {
            try {
                server.close();
                cluster.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            server = null;
            cluster = null;
        }

    }

}
