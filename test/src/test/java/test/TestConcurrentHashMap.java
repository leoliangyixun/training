package test;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangkai
 * @date 2021/8/5
 * @email yangkai@hujiang.com
 * @description
 */
public class TestConcurrentHashMap {

    @Test
    public void test_capacity() {
        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<String,Integer>(100, 0.85f, 16);
    }

}
