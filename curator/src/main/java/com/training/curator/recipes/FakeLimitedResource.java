/**
 * 
 */
package com.training.curator.recipes;

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
public class FakeLimitedResource {

	/**
	 * 
	 */
	public FakeLimitedResource() {
		// TODO Auto-generated constructor stub
	}
    public void use(String clientName) throws InterruptedException {
   	 System.out.println(clientName + " is  using");
       try {
           Thread.sleep((long) (1000* Math.random()));
       } finally {
          
       }
   }
}
