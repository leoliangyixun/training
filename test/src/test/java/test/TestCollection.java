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
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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
        System.out.println("并集 Union(A, B): " + ArrayUtils.toString(union.toArray()));
        System.out.println("交集 Intersection(A, B): " + ArrayUtils.toString(intersection.toArray()));
        System.out.println("交集的补集 Disjunction(A, B): " + ArrayUtils.toString(disjunction.toArray()));
        System.out.println("集合相减 Subtract(A, B): " + ArrayUtils.toString(subtract.toArray()));

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

    @Test
    public void test_collection_addAll() {
        Collection<String> col = new HashSet<>();
        Collection<String> col1 = new HashSet<>();
        col.addAll(col1);
        System.out.println(col);
        //col.addAll(null);
    }

    @Test
    public void test_collection_substract() {
        Set<Long> all = Sets.newHashSet(1L,2L, 3L, 4L, 5L);
        Set<Long> set1 = Sets.newHashSet(2L, 5L);

        System.out.println(CollectionUtils.subtract(all, set1));
        Set<Long> set2 = Sets.newHashSet();
        Set<Long> set = Sets.newHashSet();
        set.addAll(set2);
        System.out.println(set);
        set.addAll(all);
        System.out.println(set);

        Set<Long> set3 = Sets.newHashSet(6L, 7L);
        System.out.println(CollectionUtils.subtract(all, set3));
        Set<Long> set4 = Sets.newHashSet(1L,2L, 3L, 4L, 5L);
        System.out.println(CollectionUtils.subtract(all, set4));
        Set<Long> set5 = Sets.newHashSet();
        set5.addAll(CollectionUtils.subtract(all, set4));
        System.out.println(set5);


    }

    @Test
    public void test_empty_collection() {
        Set<Long> nums = Sets.newHashSet();
        for (Long num : nums) {
            System.out.println(num);
        }

        System.out.println(nums.contains(1));
    }


    @Test
    public void test_collection_generic() {
        Set<Serializable> set = Sets.newHashSet();
        set.add(1);
        set.add(2);
        set.add(new String("xxx"));

        for (Serializable e : set) {
            Integer a = (Integer)  e;
            System.out.println(a);
        }
    }

    @Test
    public void test_collection_sort() {
/*        List<Integer> list1 = Lists.newArrayList(1,3,5,9,7,5,6,2);
        System.out.println(list1);
        List<Integer> list2 = list1.stream().sorted((o1, o2) -> {
            if (o1 > o2) {
                return 1;
            } else if (o1 < o2) {
                return -1;
            } else {
                return 0;
            }
        }).collect(Collectors.toList());

        System.out.println(list2);*/

        List<Integer> list = Lists.newArrayList(1,3,5,9,7,5,6,2);
        System.out.println(list);
        list = list.stream().sorted((o1, o2) -> {
            if (o1 > o2) {
                return 1;
            } else if (o1 < o2) {
                return -1;
            } else {
                return 0;
            }
        }).collect(Collectors.toList());

        System.out.println(list);
    }

    @Test
    public void test_add_null_to_collection() {
        Integer a = 1000;
        Integer b = null;
        Integer c = null;
        Integer d = 2000;
        Set<Integer> set = Sets.newHashSet();
        set.add(a);
        set.add(b);
        set.add(c);
        set.add(d);
        set = set.stream().filter(e -> e!= null).collect(Collectors.toSet());
        System.out.println(set);
    }

    @Test
    public void test_remove() {
        Set<String> set1 = Sets.newHashSet("abc", "efg", "xxx");
        Set<String> set2 = Sets.newHashSet(set1);
        for (String e : set2) {
            set1.remove(e);
        }
        System.out.println(set1);
        System.out.println(set2);
    }

    @Data
    public static class SingleCustomAudienceDto implements Serializable {

        private Long uid;
        private String phone;
        private String oid;
        private String did;
        private String mail;

        public SingleCustomAudienceDto(Long uid, String did, String mail, String phone, String oid) {
            this.uid = uid;
            this.did = did;
            this.mail = mail;
            this.phone = phone;
            this.oid = oid;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            if (!super.equals(o)) {
                return false;
            }
            SingleCustomAudienceDto that = (SingleCustomAudienceDto) o;
            return Objects.equals(uid, that.uid) &&
                Objects.equals(did, that.did) &&
                Objects.equals(mail, that.mail) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(oid, that.oid);
        }

        @Override
        public int hashCode() {
            return Objects.hash(uid, did, mail, phone, oid);
        }

        @Override
        public String toString() {
            return JsonUtil.object2JSON(this);
        }
    }

    @Data
    public static class CustomAudience {
        private Set<SingleCustomAudienceDto> audiences;

        public SingleCustomAudienceDto getCustomAudienceByIndex(Serializable index) {
            return audiences.stream().filter(e ->
                Objects.equals(e.getUid(), index) ||
                    Objects.equals(e.getUid(), index) ||
                    Objects.equals(e.getUid(), index) ||
                    Objects.equals(e.getUid(), index))
                .findAny().orElse(null);
        }

        public Set<Long> getUid() {
            return audiences.stream().map(e -> e.getUid()).collect(Collectors.toSet());
        }
    }

    @Test
    public void test_remove_object() {
        SingleCustomAudienceDto audience1 = new SingleCustomAudienceDto(1L, null, null, null, null);
        SingleCustomAudienceDto audience2 = new SingleCustomAudienceDto(2L, null, null, null, null);
        SingleCustomAudienceDto audience3 = new SingleCustomAudienceDto(3L, null, null, null, null);
        Set<SingleCustomAudienceDto> audiences = Sets.newHashSet(audience1 , audience2, audience3);
        CustomAudience customAudience = new CustomAudience();
        customAudience.setAudiences(audiences);
        Set<SingleCustomAudienceDto> set2 = Sets.newHashSet(audiences);
        System.out.println(audiences);
/*        for (Long uid : customAudience.getUid()) {
            SingleCustomAudienceDto audience = customAudience.getCustomAudienceByIndex(uid);
            //SingleCustomAudienceDto dto = new SingleCustomAudienceDto(audience.getUid(), null, null, null, null);

            //System.out.println(dto.equals(audience));
            //System.out.println(dto.hashCode() == audience.hashCode());
            //boolean success = audiences.remove(audience);
            boolean success = customAudience.getAudiences().remove(audience);
            System.out.println(success);
            System.out.println("---------------------------");
        }*/

        customAudience.getUid().stream().forEach(uid -> {
            SingleCustomAudienceDto audience = customAudience.getCustomAudienceByIndex(uid);
            boolean success = customAudience.getAudiences().remove(audience);
            System.out.println(success);
            System.out.println("---------------------------");
        });

        System.out.println(audiences);
        System.out.println(set2);
    }

    @Test
    public void test_remove_object2() {
        SingleCustomAudienceDto audience1 = new SingleCustomAudienceDto(1L, null, null, null, null);
        SingleCustomAudienceDto audience2 = new SingleCustomAudienceDto(1L, null, null, null, null);
        SingleCustomAudienceDto audience3 = new SingleCustomAudienceDto(1L, null, null, null, null);
        Set<SingleCustomAudienceDto> audiences = Sets.newHashSet(audience1 , audience2, audience3);
        System.out.println(audiences);
    }

    @Test
    public void test_set_null() {
        Set<User> s = new HashSet<>();
        s.stream()
            .filter(e -> StringUtils.isNotEmpty(e.getUserDomain()))
            .map(e -> e.getUserDomain())
            .collect(Collectors.toSet());
    }

    @Test
    public void test_set_clear() {
        Set<User> s = new HashSet<>();
        s.add(new User(1, "hj"));
        s.add(new User(2, "hj"));
        s.add(new User(3, "hj"));
        System.out.println(s);
        s.clear();
        System.out.println(s);
    }

    @Test
    public void test_sub_list() {
        List<Integer> list = Lists.newArrayList(1, 2, 3);
        System.out.println(list.subList(1,3));
    }

    @Test
    public void test_Null_List() {
        List<Integer> list = Lists.newArrayList(null, null, null);
        System.out.println(CollectionUtils.isNotEmpty(list));
    }

    @Test
    public void test_multi_collection_union() {
        Set<Long> set1 = Sets.newHashSet(1L,2L,3L);
        Set<Long> set2 = Sets.newHashSet(4L,5L,6L);
        Set<Long> set3 = Sets.newHashSet(7L,8L,9L);
        Set<Long> set4 = Sets.newHashSet(7L,8L,9L);

        Set<Long> set = Sets.newHashSet(CollectionUtils.union(CollectionUtils.union(CollectionUtils.union(set1, set2), set3), set4));
        System.out.println(set);

        Iterable<Long> it = IterableUtils.chainedIterable(set1, set2, set3, set4);
        System.out.println(it);
        set = Sets.newHashSet(it);
        System.out.println(set);

    }

    @Test
    public void test_two_list_equal() {
        List<User> list1 = Lists.newArrayList(new User(1, "hj"),new User(1, "hj"),new User(1, "hj"));
        List<User> list2 = Lists.newArrayList(new User(1, "hj"),new User(1, "hj"),new User(1, "hj"));
        System.out.println(Objects.equals(list1, list2));
    }

}
