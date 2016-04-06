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
public class ExceptionTraining4 {

	/**
	 * 
	 */
	public ExceptionTraining4() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		ExceptionTraining4 t = new ExceptionTraining4();
		try {
			for (int i = 0; i < 20; i++) {
				t.test(i);
				System.out.println(i);
			}
		} catch (Exception e) {
			System.out.println("main catch " + e.getMessage());
		}
	}
	
	public void test(int i) {

		if (i == 5) throw new RuntimeException("test2");

	}

}
