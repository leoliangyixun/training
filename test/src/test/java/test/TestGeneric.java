package test;

import com.hujiang.basic.framework.core.util.JsonUtil;
import lombok.Data;
import org.junit.Test;


public class TestGeneric {
    @Data
    public static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Test
    public void test() {
        //Pair<int, char> p = new Pair<>(8, 'a');  // compile-time error
        Pair<Integer, Character> p = new Pair<>(8, 'a');
    }

    @Test
    public void test2() {
        Cache cache = new Cache();
        cache.set("p",  new Pair<>(8, 'a'));
        cache.set("p2", 2);
        cache.set("p3", "p3");
    }



    public static class Cache {
        public <V> boolean set(String key , V value) {
            System.out.println("generic method");
            System.out.println(JsonUtil.object2JSON(value));
            return true;
        }

        public  boolean set(String key , String value) {
            System.out.println("string method");
            System.out.println(value);
            return true;
        }
    }
}
