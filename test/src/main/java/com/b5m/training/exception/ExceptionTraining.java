/**
 * 
 */
package com.b5m.training.exception;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年3月25日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年3月25日       jiqingchuan          1.0             Why & What is modified
 */
public class ExceptionTraining {

	/**
	 * 
	 */
	public ExceptionTraining() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExceptionTraining t = new ExceptionTraining();
		try {
			t.test();
		} catch (Exception e) {
			System.out.println("catch");
			return;
		} finally {
			System.out.println("finally");
		}

	}
	
	public void test() {
		throw new RuntimeException("test");
	}

}
