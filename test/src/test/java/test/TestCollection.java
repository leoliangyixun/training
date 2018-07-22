package test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hujiang.basic.framework.core.exception.SysException;
import com.hujiang.basic.framework.core.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangkai on 2017/8/14.
 */
public class TestCollection {
    private final static int MAX_TOUSER = 10000;
    private final static int MAX_TOUSER_PER_ROUND = 100;

    public List<List<String>> separate(Collection<String> source, int quantity) {
        if (CollectionUtils.isNotEmpty(source)) {
            List<List<String>> separatedSources = Lists.newArrayList();
            int count = quantity <= 0 ? MAX_TOUSER_PER_ROUND : quantity;
            List<String> target = Lists.newArrayList(source);
            int size = target.size();
            int loop = size % count == 0 ? size / count : (size / count) + 1;
            for (int i = 0; i < loop; i++) {
                int start = i * count;
                int end = (i * count) + count;
                List<String> subSeparatedSource = target.subList(start, Math.min(end, size));
                separatedSources.add(Lists.newArrayList(subSeparatedSource));
            }

            return separatedSources;
        }

        return null;
    }

    public List<Set<String>> separate(Set<String> source, int quantity) {
        if (CollectionUtils.isNotEmpty(source)) {
            List<Set<String>> separatedSources = Lists.newArrayList();
            int count = quantity <= 0 ? MAX_TOUSER_PER_ROUND : quantity;
            List<String> target = Lists.newArrayList(source);
            int size = target.size();
            int loop = size % count == 0 ? size / count : (size / count) + 1;
            for (int i = 0; i < loop; i++) {
                int start = i * count;
                int end = (i * count) + count;
                List<String> subSeparatedSource = target.subList(start, Math.min(end, size));
                separatedSources.add(Sets.newHashSet(subSeparatedSource));
            }

            return separatedSources;
        }

        return null;
    }

    @Test
    public void test() {
        Set<String> ids = Sets.newHashSet("aaa", "bbb", "ccc", "aaa","ddd","eee","fff","ggg","hhh", "iii", "jjj");
        List<Set<String>> separatedIds = separate(ids, 3);
        System.out.println(separatedIds);
    }

    @Test
    public void test2() {
        //List<Integer> ids = Lists.newArrayList(1);
        List<Integer> ids = Lists.newArrayList(1,2,3);
        //List<Integer> ids = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10,11);
        //List<Integer> ids = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
        //List<Integer> ids = Lists.newArrayList(1,2,3,4,5,6,7,8,9);
        System.out.println(ids);

        int length = ids.size();
        int offset = 7;
        int loop = length % offset == 0 ? length / offset : (length / offset) + 1;
        System.out.println(loop);

        for (int i = 0; i < loop; i++) {
            int start = i * offset;
            int end = (i * offset) + offset;
            List<Integer> _ids = ids.subList(start, Math.min(end, length));
            System.out.println(_ids);
        }

    }

    @Test
    public void test3() {
        Integer id = null;
        try {
            List<Integer> ids = Lists.newArrayList(1, 2, 3);
            id = ids.stream().filter(e -> e.equals(4)).findAny().map(e -> {
                System.out.println(e);
                e = 10;
                return e;
            }).orElseGet(() -> {
                System.out.println("fuck");
                throw new SysException(50000, "error");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(id == null);
    }

    @Test
    public void test4() {
        Map<Integer, Object> map = new HashMap<>();
        map.put(1, new User(10, "cc"));
        map.put(2, new User(12, "cc"));
        System.out.println(JsonUtil.object2JSON(map));
    }

    @Test
    public void test5() {
        Map<String, Object> map = new HashMap<>();
        map.put("1", new User(10, "cc"));
        map.put("2", new User(12, "cc"));

        System.out.println(JsonUtil.object2JSON(map));
    }

    @Test
    public void test6() {
        //String json = "{1:{\"userDomain\":\"cc\",\"userId\":10},2:{\"userDomain\":\"cc\",\"userId\":12}}";
        String json = "{\"1\":{\"userDomain\":\"cc\",\"userId\":10},\"2\":{\"userDomain\":\"cc\",\"userId\":12}}";
        Map<String, Object> map = JsonUtil.json2Map(json);
        System.out.println(map);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        private Integer userId;
        private String userDomain;

        @Override
        public String toString() {
            return JsonUtil.object2JSON(this);
        }
    }

    @Test
    public void test7() {
        Long id = null;
        Set<Long> ids = Sets.newHashSet(id);
        System.out.println(ids);
        System.out.println(CollectionUtils.isEmpty(ids));
        System.out.println(ids.contains(id));
    }


    @Test
    public void testEmptyCollection() {
       List<String> list = Collections.emptyList();
        System.out.println(CollectionUtils.isEmpty(list));
    }

    @Test
    public void testCollection() {
        int limit = 10000000;
        long start = System.currentTimeMillis();
        List<Integer> ids = new ArrayList<>();
        for (int i =0; i< limit; i++) {
            ids.add(i);
        }
        long end = System.currentTimeMillis();
       // System.out.println(ids);
        System.out.println("costs:" + (end -start));

    }

    @Test
    public void testCollection2() {
        int limit = 10000000;
        long start = System.currentTimeMillis();
        List<Integer> ids = new ArrayList<>(limit);
        for (int i =0; i< limit; i++) {
            ids.add(i);
        }
        long end = System.currentTimeMillis();
        //System.out.println(ids);
        System.out.println("costs:" + (end -start));
    }

    @Test
    public void testConcurrentHashMap() {
        Map<String, String> map = new ConcurrentHashMap<>();
        //System.out.println(map.get("hjauth-uuid"));
      //  System.out.println(map.get(null));
        System.out.println(map.get(""));
    }

    @Test
    public void testCollection3() {
        String a = null;
        List<String> list = Lists.newArrayList(a);
        System.out.println(list);
        System.out.println(CollectionUtils.isEmpty(list));
    }

    @Test
    public void testInverseMap() {
       Map<String, String> map = Maps.newHashMap();
       map.put("1", "yk");
       map.put("2", "gwj");
       map.put("3", "gwj");
     Map<String, String> map1 = MapUtils.invertMap(map);
        System.out.println(map1);

    }

    @Test
    public void test_Apache_CollectionUtils() {
        Set<String> a = Sets.newHashSet( "a", "b", "c");
        Set<String> b =Sets.newHashSet( "a", "b", "c");
        List<String> c = Lists.newArrayList("a", "b", "c", "d");
        List<String> d = Lists.newArrayList("b", "c","a");
        //并集
        Collection<String> union = CollectionUtils.union(a, b);
        //交集
        Collection<String> intersection = CollectionUtils.intersection(a, b);
        //交集的补集
        Collection<String> disjunction = CollectionUtils.disjunction(a, b);
        //集合相减
        Collection<String> subtract = CollectionUtils.subtract(a, b);

        System.out.println("A: " + ArrayUtils.toString(a.toArray()));
        System.out.println("B: " + ArrayUtils.toString(b.toArray()));
        System.out.println("--------------------------------------------");
        System.out.println("并集                   Union(A, B): " + ArrayUtils.toString(union.toArray()));
        System.out.println("交集          Intersection(A, B): " + ArrayUtils.toString(intersection.toArray()));
        System.out.println("交集的补集Disjunction(A, B): " + ArrayUtils.toString(disjunction.toArray()));
        System.out.println("集合相减        Subtract(A, B): " + ArrayUtils.toString(subtract.toArray()));

    }

    @Test
    public void test_collection() {
        Set<String> a = Sets.newHashSet( "a", "b", "c");
        Set<String> b =Sets.newHashSet( "a", "b", "c");
        System.out.println(CollectionUtils.isEmpty(CollectionUtils.subtract(a, b)));

        a.retainAll(b);
        System.out.println(a);
    }

    @Test
    public void test_collection2() {
        List<String> a = Lists.newArrayList("a", "b", "c", "d");
        List<String> b = Lists.newArrayList("b", "c","a");

       // a.retainAll(b);
        a.removeAll(b);
        System.out.println(a);
    }


    @Test
    public void test_map() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("user_id", 0L);
        Map<String, Object> data = Maps.newHashMap();
        data.putAll(map);
        System.out.println(data);
    }
}