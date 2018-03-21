/**
 * 
 */
package com.yk.generic;

import java.util.HashMap;
import java.util.Map;


/**
 * @author yangkai
 *
 */
public class Cache<K, V> {
    private Node<K, V> node = new Node<>();
    private static Cache cache;
    
    public Cache() {
        
    }
    
    public static <K, V>  Cache<K, V> getInstance() {
        if (cache == null) {
            cache = new Cache<>();
        }
        return cache;
    }
    
    public  V get(K key) {
        return null;
    }
    
    public  void put(K key, V value) {
       
    }
    
    public class Node<K,V> {
        
    }



}
