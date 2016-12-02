/**
 * 
 */
package com.yk.generic.test;

import org.junit.Test;

import com.yk.generic.Cache;
import com.yk.generic.CachePlugin;

/**
 * @author yangkai
 *
 */
public class TestGeneric {

    @Test
    public void testGeneric() {
        CachePlugin<String, User> plugin = CachePlugin.getInstance().;
        //plugin.put("name", "yangkai");
        plugin.put("user", new User("yangkai"));
        System.out.println(plugin.get("user"));
      
        
    }
    
    @Test
    public void testGeneric2() {
       Cache<String, String> plugin = Cache.getInstance();
      
        plugin.put("name", "yangkai");
        System.out.println(plugin.get("name"));
      
        
    }
    
    public class User {
        private String name;

        public User(String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return "User[name=" + name + "]";
        }
        
    }
    


}
