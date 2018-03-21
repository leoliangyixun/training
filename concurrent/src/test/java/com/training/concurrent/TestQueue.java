package com.training.concurrent;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by izene on 2016/6/15.
 */
public class TestQueue {

    @Test
    public void test() throws InterruptedException {
        ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(3);
        q.put(1);

    }
}
