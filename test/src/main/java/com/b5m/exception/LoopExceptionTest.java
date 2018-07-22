/**
 * 
 */
package com.b5m.exception;

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
public class LoopExceptionTest {

	/**
	 * 
	 */
	public LoopExceptionTest() {

	}
	
	public void test(int i) {
		if (i == 5) throw new RuntimeException("test");
	}
	
	public static void main(String[] args) {
		LoopExceptionTest t = new LoopExceptionTest();
		try {
			for (int i = 0; i<10; i++) {
				System.out.println("i: " + i);
				t.test(i);	
			}
		} catch(Exception e){
			System.out.println("exception");
		} finally {
			
		}
	}

}
