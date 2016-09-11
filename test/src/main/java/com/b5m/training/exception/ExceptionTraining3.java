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
public class ExceptionTraining3 {

	/**
	 * 
	 */
	public ExceptionTraining3() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	
		ExceptionTraining3 t = new ExceptionTraining3();

		for (int i = 0; i < 20; i++) {
			try {
				t.test(i);			
			} catch (Exception e) {
				System.out.println("main catch " + e.getMessage());
			} 
			
			System.out.println(i);
		}

	}
	
	public void test(int i) {

		if (i == 5) throw new RuntimeException("test2");

	}

}
