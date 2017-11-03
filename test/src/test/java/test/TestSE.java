package test;

import com.alibaba.fastjson.annotation.JSONField;
import com.hujiang.basic.framework.core.util.JsonUtil;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangkai on 2017/6/9.
 */
public class TestSE {

    @Test
    public void test() {
        Main.Employee e = new Main.Employee(200, "ok", "{yk}");
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
}
