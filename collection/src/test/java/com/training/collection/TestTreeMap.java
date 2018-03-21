/**
 * 
 */
package com.training.collection;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

/**
 * @author yangkai
 *
 */
public class TestTreeMap {

    /**
     * 
     */
    public TestTreeMap() {
        // TODO Auto-generated constructor stub
    }
    
    
    @Test
    public void test() {
        
        Map<String, String> map = new TreeMap<String, String>();
       
        map.put("xxx", null);
        map.put("yyy", null);
        System.out.println(map);
    }

}
