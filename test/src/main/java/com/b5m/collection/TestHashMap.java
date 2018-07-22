package com.b5m.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangkai on 16/5/22.
 */
public class TestHashMap {
    public static void main(String[] args) {
/*        Map<Employee, String> map = new HashMap<>();

        Employee u1 = new Employee("yangkai", 28);
        Employee u2 = new Employee("yangkai", 28);
        map.put(u1, "xxx");
        map.put(u2, "yyy");
        System.out.println(map);*/

        System.out.println(1024 & 1024);

    }
    

    public void testContains() {
        Map<String, Object> map = new HashMap<>();
        String s1 = new String("yangkai");
        String s2 = new String("yangkai");
        String s3 = "yangkai";
        map.put("yangkai", "xxxxxx");
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
        System.out.println(map.containsKey(s3));
        
    }

}
