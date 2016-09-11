/**
 * 
 */
package com.b5m.test.exception;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年3月23日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年3月23日       jiqingchuan          1.0             Why & What is modified
 */
public class ExceptionTest {

	public ExceptionTest() {
		// TODO Auto-generated constructor stub
	}
	
	public void test() {
		for (int i = 0; i < 20; i++) {
			try {
				test2(i);
			} catch (Exception e) {
				System.out.println("i=" + i);
			//	 throw new RuntimeException("test");
			} finally {
				
			}
			System.out.println("i=" + i);
		}
	}
	public void test2(int i) {
			if (i == 5) throw new RuntimeException("test2");
	}
	
	public void run() {
		for (int j = 0; j < 10; j++) {
			test();
			System.out.println("j=" + j);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExceptionTest test = new ExceptionTest();

		try {
			test.test();
		} catch (Exception e) {
			
		}
	}

}
