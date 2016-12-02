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
public class CachePlugin<K, V> {
    
    private Map<K, V> map = new HashMap<>();
  
    private  static  CachePlugin cachePlugin;
    
    public CachePlugin() {
        
    }
    
    public  static <K, V> CachePlugin<K, V> getInstance() {
        return new  CachePlugin<K, V>();
    }
    
    public  static <K, V> CachePlugin<K, V> getInstance2() {
        if (cachePlugin == null) {
            cachePlugin = new  CachePlugin<K, V>();
        }
        return cachePlugin;
    }
    
    
    public V get(K key) {
        return map.get(key);
    }
    
    public void put(K key, V value) {
        map.put(key, value);
    }
    
    
    
    

}
