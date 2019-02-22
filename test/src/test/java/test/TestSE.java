package test;

import com.hujiang.basic.framework.core.util.DateUtil;
import com.hujiang.basic.framework.core.util.JsonUtil;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Created by yangkai on 2017/6/9.
 */
public class TestSE {

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
           // Entity<T> obj = JsonUtil.deserialize(JsonUtil.serialize(this));
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
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
        Object obj = new Object() {@Getter String username = "yk"; @Getter int age = 20;};
        System.out.println(JsonUtil.object2JSON(obj));
    }


    @Test
    public void test_1() {
        int a = 1;
        Integer b = new Integer(1);
        System.out.println(a==b);

    }

    @Test
    public void test_2() {
        Integer a =123456;
        Integer b = new Integer(123456);
        System.out.println(a==b);
    }

    @Test
    public void test_3() {

        System.out.println( NumberUtils.toInt("1"));
        System.out.println(Integer.parseInt("2"));
    }

    @Test
    public void test2_1() {
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        Integer c = new Integer(2);
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a < c);

    }

    @Test
    public void test2_2() {
        int a = 0;
        Integer x = new Integer(0);
        Calculator calculator = new Calculator();
        System.out.println(calculator.add(a));
        System.out.println(a);
        System.out.println(calculator.add(x));
        System.out.println(x);


    }

    @Test
    public void test3() {
        //int a = 9999999999;
    }

    public static class Calculator {
        public int add(Integer i) {
            return ++i;
        }
    }

    @Test
    public void test4() {
        int  a = 40;
        System.out.println((float)a);
        System.out.println(a/100.0);

    }

    public static void main(String[] args) {
/*		Float a = 0.00f;
		Float b = 1.00f;
		Float c = 1.00f;
		for(int i = 0; i < 2; i++) {
			//a += b + c;
			a = a + b + c;
			//a += b;
		}
		System.out.println(a);
*/


/*	    Float f = new Float(100.00f);
	    System.out.println(f.intValue());*/
        Integer a = 1020;
        System.out.println(a.floatValue()/100);
    }

    @Test
    public  void test5() {
        Double a = 100.90;
        System.out.println(a.intValue());

    }

    @Test
    public  void test6() {
        float a = 0.99956936F;
        System.out.println(a >= 0.852F);
        System.out.println(Float.MAX_VALUE);
        System.out.println(Float.MIN_VALUE);
        System.out.println(Double.MAX_VALUE);
        System.out.println(Double.MIN_VALUE);
    }

    @Test
    public  void test7() {
        Integer a = new Integer(5200);
        int b = 5200;
        Integer c = 5200;
//	    	true
//	    	false
//	    	true
//	    	true
        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(b == c);
        System.out.println(c == 5200);


    }


    @Test
    public void int2long() {
        int a = 500;
        int b = 200;
        long c = a - b;
        long d = (long)(a - b);
        System.out.println(c);
        System.out.println(d);
    }

    public interface Res {
        Map<String, Object> map();
        Map<String, Object> map2();
        String json();
    }

    public abstract static class BaseRes implements Res {
        @JSONField(name = "access_token")
        private String accessToken;
        @JSONField(name = "refresh_token")
        private String refreshToken;
        @JSONField(name = "expires_in")
        private long expiresIn;

        @Override
        public String json() {
            return JsonUtil.object2JSON(this);
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public class LoginRes extends BaseRes implements Serializable {

        private static final long serialVersionUID = 2666272039102217927L;
        private String account;
        private String password;
        @JSONField(name = "sms_code")
        private String smsCode;
        @JSONField(name = "login_type")
        private int loginType;

        @Override
        public Map<String, Object> map() {
            String json = JsonUtil.object2JSON(this);
            return  JsonUtil.json2Map(json);
        }

        @Override
        public Map<String, Object> map2() {
            Map<String, Object> mp = new HashMap<>();

            for (Field field : this.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    mp.put(field.getName(), field.get(this));
                } catch (Exception e) {
                  e.printStackTrace();
                }
            }

            return mp;
        }

    }


    @Test
    public void testRes() {
        Res res1 = new LoginRes("yangkai@hujaing.com", "!@#$%^&*", "123654", 1001);
        System.out.println(res1.json());
        System.out.println(res1.map());
        LoginRes res2 = new LoginRes("yangkai@hujaing.com", "!@#$%^&*", "123654", 1001);
        System.out.println(res2.json());
        System.out.println(res2.map());
        System.out.println("");
    }

    @Test
    public void testRes2() {
        Res res = new LoginRes("yangkai@hujaing.com", "!@#$%^&*", "123654", 1001);
        System.out.println(res.map2());

        System.out.println("");
    }

    @Test
    public void testRes3() {
        LoginRes res = new LoginRes("yangkai@hujaing.com", "!@#$%^&*", "123654", 1001);
        System.out.println(res.map2());

        System.out.println("");
    }

//-----------------------------------------------------------------------------
    private User user = new User("annie", 27);

    @Test
    public void test8() {
        User u = this.user;
        System.out.println(this.user);
        u.setAge(28);
        System.out.println(u);
        System.out.println(this.user);

    }

    public static class User {
        private String name;
        private int age;


        public User(String name, int age) {
            super();
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }
    //-----------------------------------------------------------------------------

    @Test
    public void testUrl() throws Exception {
        //URL url = new URL("http://www.baidu.com");
        URL url = new URL("www.baidu.com");
       // URL url = new URL("baidu.com/login");
//        String file = url.getFile();
//        System.out.println("file"+ file);
        Object content = url.getContent();
        System.out.println("content" + content);
//        String host = url.getHost();
//        System.out.println("host" + host);
//        String protocol = url.getProtocol();
//        System.out.println("protocol" + protocol);
//        int port = url.getPort();
//        System.out.println("port" + port);
    }

    @Test
    public void testUrl2() throws Exception {

        String url = "hujiang.com/qc";

        String url2 = "http://www.hujiang.com/qc?source=weixin";

        StringUtils.split(url, "http://");
    }

    @Test
    public void testStr2Map() throws Exception {
        String token = "{\"token\": \"xxx\"}";
        Map<String, Object> map = JsonUtil.json2Map(token);
        System.out.println(map);
    }

    @Data
    @NoArgsConstructor
    public static class Student {
        private int age;
        private String name;
        private String school;
        public Student(int age){
            this.age = age;
            this.name = build(age);
            this.school = "xx";
        }

        public String buildName(int age) {
            return "xxx" + age;
        }

        public static String build(int age) {
            return "xxx" + age;
        }

        public String toString() {
            return JsonUtil.object2JSON(this);
        }

    }

    @Test
    public void testStudent() throws Exception {
        Student s = new Student(20);
        System.out.println(s);
    }

    @Test
    public void testURL() throws Exception {
        String url = "http://localhost:8080/qc?source=xx";
        System.out.println(new URL(url).getHost());

    }

    @Test
    public void testNull() throws Exception {
        System.out.println(Objects.equals(null, null));
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class People {
        private Integer userId;
        private String name;
        private People teacher;
        private People leader;
        private List<People> students;

        public People(Integer userId, String name) {
            this.userId = userId;
            this.name = name;
        }

        @Override
        public String toString() {
            return JsonUtil.object2JSON(this);
        }
    }

    @Test
    public void testJsonCascade() {
        People p1 = new People(1, "xx");
        People p2 = new People(2, "yy");
        People p3 = new People(3, "xx");
        People p4 = new People(4, "mm");
        People p5 = new People(5, "nn");
        p1.setTeacher(p2);
        p1.setStudents(Lists.newArrayList(p4, p5));

        People[] peoples = new People[]{p1/*, p2, p3,p4, p5*/};
        //List<People> ss = Lists.newArrayList(p1, p2, p3);

        System.out.println(JsonUtil.object2JSON(peoples, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect));
        //System.out.println(JsonUtil.object2JSON(ss));
    }

    public interface Teachable {
        public void work();
    }

    public interface Programmable {
        public void program();
    }

    public class Programmer {
        public void work() {
            System.out.println("I am programming");
        }
    }

    public class TeachableProgrammer extends Programmer implements Teachable {

        @Override
        public void work() {
            super.work();
            System.out.println("I am teaching");
        }
    }

    @Test
    public void testWork() {
        TeachableProgrammer tp = new TeachableProgrammer();
        tp.work();
    }
    
    
    @Data 
    public static class Student2 {
        private String name;

        @Override
        public boolean equals(Object obj) {
            return false;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }

        public Student2(String name) {
            this.name = name;
        } 
    }
    
    @Test
    public void testHashCodeAndEqual() {
        
        Student2 s1 = new Student2("yk");
        Student2 s2 = new Student2("yk");
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());

        System.out.println(s1 == s2);

        System.out.println( s1.equals(s2));

        Map<Student2, String> map = new HashMap<>();
        map.put(s1, "yk");
        map.put(s2, "yk2");
        System.out.println(map);

        Set<Student2> set = new HashSet<>();
        set.add(s1);
        set.add(s2);
        System.out.println(set);

    }

    @Test
    public void testInteger() {
        Integer a = 200000;
        Integer b = new Integer(200000);
        System.out.println(a == 200000);
        System.out.println(b == 200000);
    }

    @Test
    public void test_字节数() {
        String a = "杨开";
        System.out.println(a.getBytes(Charset.forName("UTF-8")).length);//6
        String b = "a";
        System.out.println(b.getBytes(Charset.forName("UTF-8")).length);//6

    }

    @Test
    public void test_date() {
        System.out.println(new Date().getTime());
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date().getTime() / 1000);
        System.out.println(System.currentTimeMillis() / 1000);
        System.out.println(DateUtil.toDateString(new Date(), DateUtil.DEFAULT_DATETIME_PATTERN));

    }

    @Test
    public void test_ArrayIndexOutOfBoundsException() {
        List<Integer> list = Lists.newArrayList();
        for (int i = 1; i < 100000000; i++) {
            list.add(i);
        }
        System.out.println(list);

    }


    @Test
    public void test_ArrayIndexOutOfBoundsException2() {
        List<List<Integer>> list = Lists.newArrayList();

        for (int i = 0; i< 100; i++) {
            List<Integer> l = Lists.newArrayList();
            for (int j = 0; j< 1000000; j++) {
                l.add(j);
            }
            list.add(l);
        }

        List<Integer> all = list.stream().reduce(Lists.newArrayList(), (x, y) -> {
            x.addAll(y);
            return x;
        });

        System.out.println(all);
    }

    @Test
    public void testNP() {
        Integer a = null;
        System.out.println(1 == a);
    }

    @Data
    public static class Person {
        private Integer age;
        private String name;
        private Date birthday;
    }

    interface Notification {
        String getPayload();
        Person getPerson();
    }


    public static class SoaNotification implements Notification {
        @Setter
        private String payload;
        @Setter
        private Person person;

        @Override
        public String getPayload() {
            return null;
        }

        @Override
        public Person getPerson() {
            return null;
        }
    }

    @Test
    public void testJsonUtil4Interface() {
        Notification notification = new SoaNotification();
        String data = JsonUtil.object2JSON(notification);

        //Notification copy = JsonUtil.json2Object(data, Notification.class);
        Notification copy = JsonUtil.json2Object(data, SoaNotification.class);
        System.out.println(copy);

    }

    @Test
    public void test11() {
        int a = 1;
        int b = 3;
        //System.out.println(a += b);
        System.out.println(a = +b);

        Map<String, Object> map = Maps.newHashMap();
        map.put("key1", "key1");
        map.put("key2", "key2");
        map.put("key3", "key3");
        map.put("key4", map);
        map.put("key5", map);
        String json = JsonUtil.object2JSON(map);
        System.out.println(json);
        Map dict = JsonUtil.json2Object(json, Map.class);
        System.out.println(dict);
    }

    @Test
    public void test12() {
        System.out.println(true ^ true^ true^ true);
        System.out.println(false ^ false^ true^ false);
    }

    @Data
    public static class A {
        private Set<String> names;

        @Override
        public String toString() {
            return JsonUtil.object2JSON(this);
        }
    }

    @Data
    public static class B {
        private Set<String> names;

        @Override
        public String toString() {
            return JsonUtil.object2JSON(this);
        }
    }

    @Test
    public void test13() {
        Set<String> names = Sets.newHashSet("yk", "leo", "lavender");
        A a = new A();
        B b = new B();
        a.setNames(names);
        b.setNames(names);

        b.getNames().remove("leo");
        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void test14() {
        Serializable a = 10000L;
        Long b = 10000L;
        System.out.println(Objects.equals(a, b));

        Serializable c = "yk";
        String d = "yk";
        System.out.println(Objects.equals(c, d));
    }

    @Test
    public void test15() {
        String a = "1";
        String b= "xx";
        Long c = 2L;
        Serializable d = 10000L;
        Long e = (Long) d;
        System.out.println(e);
    }

    @Test
    public void test16() {
        String a = "1";
        String b= "xx";
        Long c = 2L;
        Serializable d = 10000L;
        Long e = (Long) d;
        System.out.println(e);
    }

}
