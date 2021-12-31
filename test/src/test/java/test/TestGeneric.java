package test;

import com.hujiang.basic.framework.core.util.JsonUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


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

    @Test
    public void test2() {
        Cache cache = new Cache();
        cache.set("p",  new Pair<>(8, 'a'));
        cache.set("p2", 2);
        cache.set("p3", "p3");
    }


    public static class FruitCol<E> {
        private List<E> list = Lists.newArrayList();

        public boolean add(E e) {
            return list.add(e);
        }

        public void addAll(List<E> list) {
            for (E e : list) {
                //add(e);
                list.add(e);
            }
        }

        public void addAll2(List<? extends E> all) {
            for (E e : all) {
                //add(e);
                list.add(e);
            }
        }

    }
    @Test
    public void test3() {
        List<Fruit> list = Lists.newArrayList();
        list.add(new Apple("苹果1","hh", "china"));
        list.add(new Banala("香蕉1","mm", "usa"));
        System.out.println(list);
/*
        List<? extends Fruit> list2 = Lists.newArrayList();
        list2.add(new Apple("苹果2","hh", "china"));
        list2.add(new Banala("香蕉2","mm", "usa"));
*/


/*
        List<? extends Fruit> list3 = list;

        list3.add((Fruit) new Banala("香蕉1","mm", "usa"));
*/
        FruitCol<Fruit> col = new FruitCol<>();
        col.add(new Banala("香蕉1","mm", "usa"));
        col.addAll(Lists.newArrayList(new Fruit("test", "sh")));
        List<? extends Fruit> fruits = Lists.newArrayList(new Apple("苹果1", "hh", "china"), new Banala("香蕉1", "mm", "usa"));
        col.addAll2(fruits);
        System.out.println(col);

    }





    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Fruit {
        private String name;
        private String category;

    }

    @Data

    public static class Apple extends Fruit{
        private String country;

        public Apple(String name,String category,String country) {
            super(name,category);
            this.country = country;
        }

    }

    @Data

    public static class Banala  extends Fruit{
        private String area;
        public Banala(String name,String category,String area) {
            super(name,category);
            this.area = area;
        }

    }
}
