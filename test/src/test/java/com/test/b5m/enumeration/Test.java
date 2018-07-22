/**
 * 
 */
package com.test.b5m.enumeration;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年3月1日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年3月1日       jiqingchuan          1.0             Why & What is modified
 */
public class Test {


	public Test() {

	}
	public static void main(String[] args) {
		SourceType type =SourceType.CPS;
		switch (type) {
		case GRAB:
			System.out.println("GRAB");
			break;
/*		case CPS:
			
			break;*/
		default:
			System.out.println("exception");
			break;
		}
		
		
		SourceType type2 = type.parse(2);
		System.out.println(type2);
	}

}
