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
public class ExceptionTraining2 {

	/**
	 * 
	 */
	public ExceptionTraining2() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//循环里抛的异常会导致下面的逻辑不再执行
		ExceptionTraining2 t = new ExceptionTraining2();
		try {
			t.test();
		} catch (Exception e) {
			System.out.println("main catch");
			return;
		} finally {
			System.out.println("main finally");
		}

	}
	
	public void test() {
		for (int i = 0; i < 20; i++) {
		
			if (i == 5) throw new RuntimeException("test2");
			
			System.out.println("i=" + i);
		}
	}

}
