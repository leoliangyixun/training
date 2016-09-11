/**
 * 
 */
package com.b5m.test.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年2月29日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年2月29日       jiqingchuan          1.0             Why & What is modified
 */
public class MapTest {

	public MapTest() {
		
	}
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String,String>();
		String key = "jiqingchuan";
		String key2 = "leoliangyixun";
		map.put(key, key);
		map.put(key, key);
		map.put(key2, key2);
		System.out.println(map);
		
	}

}
