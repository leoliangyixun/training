/**
 * 
 */
package com.training.collection;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

/**
 * @description:
 * Copyright 2011-2015 B5M.COM. All rights reserved
 * @author: jiqingchuan
 * @version: 1.0
 * @createdate: 2016年6月30日
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 * 2016年6月30日       jiqingchuan          1.0             Why & What is modified
 */
public class TestCollection {
    
    @Test
    public void testLinkedHashMap() {
        
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(16);
       
        map.put("name", "jiqingchuan");
        map.put("email", "jiqingchuan@b5m.com");
        System.out.println(map);
    }
    
    @Test
    public void testArrayList() {
        ArrayList<String> list = new ArrayList<String>();
       
    }
    
    @Test
    public void testArray() {
        Object[] objs = new  Object[10];
        System.out.println(objs[9]);
       
    }
    
    @Test
    public void testLinkedList() {
        LinkedList<String> list = new LinkedList<String>();
        list.add("jiqingchuan");
        list.add("jiqingchuan@b5m.com");
        System.out.println(list);
    }
    
    @Test
    public void testTreeMap() {
        TreeMap<String, String> map = new TreeMap<String, String>();
       
    }

    @Test
    public void testOperator() {
        int j = 12 >> 1;
        System.out.println(j);
    }
}
