package com.b5m.training.collection;

import java.util.HashMap;
import java.util.Map;

import static com.b5m.training.collection.Test.*;

/**
 * Created by yangkai on 16/5/22.
 */
public class TestHashMap {
    public static void main(String[] args) {
        Map<Employee, String> map = new HashMap<>();

        Employee u1 = new Employee("yangkai", 28);
        Employee u2 = new Employee("yangkai", 28);
        map.put(u1, "xxx");
        map.put(u2, "yyy");
        System.out.println(map);

    }

}
