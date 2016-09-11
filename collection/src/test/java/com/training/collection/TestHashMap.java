package com.training.collection;

import org.junit.Test;

/**
 * Created by izene on 2016/7/5.
 */
public class TestHashMap {

    @Test
    public void test() {
        int capacity = 12;
        int newCapacity = Integer.highestOneBit((capacity - 1) << 1);
        System.out.println(newCapacity);
    }
}
