/**
 * 
 */
package test;

import com.hujiang.basic.framework.core.util.IpUtil;
import com.hujiang.basic.framework.core.util.JsonUtil;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author yangkai
 *
 */
public class Main {
    
    @Test
    public void test() {
        Employee e = new Employee(200, "ok", "{}");
        e.setName("xx");
        //System.out.println(e.toString());
        System.out.println(e.clearSensitive());
    }

    @Data
    @NoArgsConstructor
    public static abstract class Entity<T extends Serializable> implements Serializable {
        
        private static final long serialVersionUID = 3454837606164676885L;

        protected Integer status;
        protected String message;
        protected T data;
        
        public Entity(Integer status, String message, T data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }
        
        public String clearSensitive() {
            Entity<T> obj = JsonUtil.deserialize(JsonUtil.serialize(this));
            return obj.toString();
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }

    @NoArgsConstructor
    @EqualsAndHashCode(callSuper=true)
    public static class Employee extends Entity<String> {
 
        private static final long serialVersionUID = -4742391741835085542L;
        @Getter
        @Setter
        private String name;

        public Employee(Integer status, String message, String data) {
            super(status, message, data);
            this.name = name;
        }
        
/*        public String clearSensitive() {
            Employee obj = JsonUtil.deserialize(JsonUtil.serialize(this));
            return obj.toString();
        }*/
        
        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }

        
    }

    @Test
    public void test2() {
        for (int i = 0; i < 64; i++) {
            String str = "DROP TABLE IF EXISTS `user_union_" + i + "`;\n" + "CREATE TABLE `user_union_" + i + "` (\n" + "  `id` int unsigned NOT NULL AUTO_INCREMENT,\n" + "  `union_id` bigint NOT NULL,\n" + "  `user_id` int(10) NOT NULL,\n" + "  `user_domain` varchar(10) NOT NULL,\n" + "  `create_at` datetime NOT NULL ,\n" + "  `update_at` datetime NOT NULL,\n" + "  PRIMARY KEY (`id`),\n" + "  UNIQUE KEY `uidx_user_id_user_domain` (`user_id`,`user_domain`) USING BTREE\n" + ") ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;";

            System.out.println(str);
            System.out.println();
        }

        for (int i = 0; i < 64; i++) {
            String str = "DROP TABLE IF EXISTS `union_user_" + i + "`;\n" + "CREATE TABLE `union_user_" + i + "` (\n" + "  `id` int unsigned NOT NULL AUTO_INCREMENT,\n" + "  `union_id` bigint NOT NULL,\n" + "  `user_id` int(10) NOT NULL,\n" + "  `user_domain` varchar(10) NOT NULL,\n" + "  `create_at` datetime NOT NULL ,\n" + "  `update_at` datetime NOT NULL,\n" + "  PRIMARY KEY (`id`),\n" + "  UNIQUE KEY `uidx_user_id_user_domain` (`user_id`,`user_domain`) USING BTREE,\n" + "  INDEX `idx_union_id_user_domain` (`union_id`, `user_domain`) USING BTREE\n" + ") ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;";

            System.out.println(str);
            System.out.println();
        }
    }


    private static String functionWithSideEffect(String name) {
        System.out.println(name + " Yes this is a sideffect!");
        return name + " And this is the returned value!";
    }

    @Test
    public void test_orElse_orElseGet() {

        Optional<String> empty = Optional.empty();
        Optional<String> something = Optional.of("something");
       // String a = empty.orElse(functionWithSideEffect());
        String b = something.orElse(functionWithSideEffect("orElse"));
       // String c = empty.orElseGet(()->functionWithSideEffect());
        String d = something.orElseGet(()->functionWithSideEffect("orElseGet"));
    }

    @Test
    public void testJSON() {

        System.out.println(JsonUtil.object2JSON(JSON.parseObject("all")));
    }

    public enum Type {
        A("A"), B("B"), C("C");

        private String type;

        Type(String type) {
            this.type = type;
        }
    }

    @Test
    public void testEnum() {

        System.out.println(JsonUtil.object2JSON(JSON.parseObject("all")));
    }


    @Test
    public void testArray2List() {

        String[] names = new String[]{"yk", "gwj", "gyy"};
        List<String> list = Arrays.asList(names);
        System.out.println(list);
        List<String> list2 = Lists.newArrayList(names);
        System.out.println(list2);
    }

    @Test
    public void test_getAvailableProcessors() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }


    @Test
    public void test_long() {
        Long a = new Long(123456789);
        Long b = new Long(123456789);
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(Objects.equals(a, b));

        long c = 1234567L;
        long d = 1234567L;
        System.out.println(c ==d);

        long e = new Long(123456789);
        long f = new Long(123456789);
        System.out.println(e ==f);
    }

    private Map<String, ? extends Fruit> fruitMap = new HashMap<>();

    @Test
    public void testListGeneric() {
/*        Map<String, ? extends Fruit> map = new HashMap<>();
        Apple apple = new Apple();
        apple.setName("apple");
        apple.setCountry("china");
        Orange orange = new Orange();
        orange.setName("orange");
        orange.setColor("yellow");

        map.put("apple", apple);
        map.put("orange", orange);
        System.out.println(map);
        List<? extends Fruit> list = new ArrayList<>();
        list.add(apple);
        list.add(orange);
        System.out.println(list);
        fruitMap.put("a", apple);*/

    }

    @Test
    public void testToString() {
        Apple apple = new Apple();
        apple.setCategory("xxx");
        apple.setName("apple");
        apple.setCountry("china");
        apple.setColor("red");
        System.out.println(apple);

    }


    public interface Fruit {

    }

    @Data
    public static abstract class GenericFruit implements Fruit {
        private String category;

        @Override
        public String toString() {
            return JsonUtil.object2JSON(this);
        }
    }

    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Apple extends GenericFruit {
        private String name;
        private String country;
        private String color;


    }

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    public static class Orange extends GenericFruit {
        private String name;
        private String country;
        private String color;

        public Orange(String name, String country, String color) {
            this.name = name;
            this.country = country;
            this.color = color;
        }
    }


    @Test
    public void testEqual() {
        Object o1 = "yk";
        Object o2 = 1000;
        String s1 ="yk";
        Integer a = 1000;
        System.out.println(Objects.equals(o1,s1));
        System.out.println(Objects.equals(o2,a));
    }

    @Test
    public void testClass() {
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Orange orange1 = new Orange();
        System.out.println(apple1.getClass() == apple2.getClass());

    }

    @Data
    public static class Wrapper {
        private Fruit fruit;

        public Wrapper(Fruit fruit) {
            this.fruit = fruit;
        }

        @Override
        public String toString() {
            return JsonUtil.object2JSON(this);
        }
    }

    @Test
    public void testReference() {
        Fruit orange = new Orange("xxx", "xxx", "xxx");
        Wrapper wrapper = new Wrapper(orange);
        orange = new Orange("yyy", "yyy", "yyy");
       // wrapper.setFruit(orange);
        System.out.println(wrapper.getFruit());
    }

    @Test
    public void testReferenceAndValue() {
/*        int a = 1;
        this.change1(a);
        System.out.println(a);*/

/*        Integer b = new Integer(2);
        this.change2(b);
        System.out.println(b);*/

        Integer c = new Integer(2000);
        Integer d = c;
        Integer e = 2001;
        int f = 2001;
        d++;
        System.out.println(d);
        c++;
        System.out.println(c);
        System.out.println(c == d);
        System.out.println(c == e);
        System.out.println(e == d);
        System.out.println(e == f);
        System.out.println("");
    }

    @Test
    public void testInt2Long() {
        int a = 1;
        long b= 1;
        long c = a;
        System.out.println(b == c);
    }

    private int change1(int a) {
        return ++a;
    }

    private Integer change2(Integer a) {
        ++a;
        System.out.println(a);
        return a;
    }


    @Test
    public void testCollectionContain() {
        Set<Integer> set = Sets.newSet(1,2,3);
        Long a = 1L;
        Integer b = 1;
        System.out.println(Objects.equals(a, b));
        System.out.println(set.contains(a));
        System.out.println(set.contains(b));
    }

    @Test
    public void testIpMode() {
/*        System.out.println(102 % 32);
        System.out.println(111 % 32);
        System.out.println(107 % 32);*/
        System.out.println(254 % 256);
        //172.168.36.255
    }

    @Test
    public void testLongAndInteger() {
        Long a = 10000L;
        //Integer b = (Integer) a;
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);

    }

    @Data
    public static class Fruit1 {
        private String type;

        public Fruit1(String type) {
            this.type = type;
        }

        public void print() {
            System.out.println("fruit");
        }
        @Override
        public String toString() {
            return JsonUtil.object2JSON(this);
        }

    }

    @Data
    public static class Apple1 extends Fruit1 {
        private String name;

        public Apple1(String type, String name) {
            super(type);
            this.name = name;
        }

        @Override
        public void print() {
            System.out.println("apple");
        }

        @Override
        public String toString() {
            return JsonUtil.object2JSON(this);
        }
    }

    @Test
    public void test_override() {
        Apple1 apple = new Apple1("水果", "苹果");
        Fruit1 fruit1 = (Fruit1) apple;
        Fruit1 fruit2 = new Apple1("水果", "苹果");
        Fruit1 fruit3 = new Fruit1("水果");
        apple.print();
        fruit1.print();
        fruit2.print();
        fruit3.print();

    }

    @Test
    public void test_list_generic() {
        Apple1 apple = new Apple1("水果", "苹果");
        Fruit1 fruit = new Fruit1("水果");
        List<Fruit1> list = new ArrayList<>();
        list.add(apple);
        list.add(fruit);
        System.out.println(list);
    }

    @Test
    public void test_instanceof() {
        Apple1 apple = new Apple1("水果", "苹果");
        Fruit1 fruit = new Fruit1("水果");

        System.out.println(apple instanceof Apple1);
        System.out.println(apple instanceof Fruit1);
        System.out.println(fruit instanceof Apple1);
    }

    @Test
    public void testMessageFormat() {
        Object[] objects={new Date(),"美国","晴朗"};

        MessageFormat mf= new MessageFormat("当前时间：{0,date}，地点：{2}，天气：{1}");
        String result=mf.format(objects);
        System.out.println(result);

        System.out.println(new MessageFormat("数字{0}").format(new Object[] { 12345+"" }));

    }

    @Test
    public void test1000() {
        System.out.println((-1 << (Integer.SIZE - 3)) | 0);
    }

    @Test
    public void test1001() {
        System.out.println((float) 10 / 3);
        System.out.println(10 / 3.0);
        System.out.println(10 / 2.0);

    }

}
