/**
 * 
 */
package com.yk.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;
import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import lombok.Data;
import lombok.val;

/**
 * @author yangkai
 *
 */
public class TestStream {
    
    @Test
    public void test() {
        List<Integer> list = new ArrayList<>(Arrays.asList(new Integer[]{1,2,3,4,5,6}));
      list.stream().flatMap(l -> list.stream()).collect(null);

    }
    
    @Test
    public void test2() {
        List<User> users = new ArrayList<>();
       new ArrayList<User>(Arrays.asList(new User[] {new User("xxxxxx", "1","qa"), new User("yangkai", "1","dev"), new User("lowrrence", "1","dev"), new User("kerwin", "2", "dev"), new User("alisa", "3", "qa")}))
       .stream().filter(user -> "dev".equals(user.dept)).forEach(user -> {
           if ("1".equals(user.openid)) {
               users.add(user);
           }
           });
       
       System.out.println(users);
    }
    
    @Test
    public void test3() {
        List<User> users = new ArrayList<User>(Arrays.asList(new User[] {new User("xxxxxx", "1","qa"), new User("yangkai", "1","dev"), new User("lowrrence", "1","dev"), new User("kerwin", "2", "dev"), new User("alisa", "3", "qa")}))
       .stream()
       .filter(user -> "dev".equals(user.dept) && "1".equals(user.openid))
       .collect(Collectors.toList());
       System.out.println(users);

    }
    
    @Test
    public void test4() {
        List<User> users = new ArrayList<User>(Arrays.asList(new User[] {new User("xxxxxx", "1","qa"), new User("yangkai", "1","dev"), new User("lowrrence", "1","dev"), new User("kerwin", "2", "dev"), new User("alisa", "3", "qa")}))
       .stream().filter(user -> "dev".equals(user.dept)).filter(user -> "1".equals(user.openid)).collect(Collectors.toList());
       System.out.println(users);
    }
    
    
    @Test
    public void test5() {
        List<User> users = new ArrayList<>(Arrays.asList(new User[] {new User("xxxxxx", "1","qa"), new User("yangkai", "1","dev"), new User("lowrrence", "1","dev"), new User("kerwin", "2", "dev"), new User("alisa", "3", "qa")}));
        List<User> validUsers = new   ArrayList<>();
        List<User> invalidUsers = new   ArrayList<>();
        validUsers = users.stream().filter(user -> "dev".equals(user.dept)).filter(user -> "1".equals(user.openid)).collect(Collectors.toList());
        System.out.println(users);
        System.out.println(validUsers);
       
        
/*        users.removeAll(validUsers);
        System.out.println(users);
        System.out.println(invalidUsers);*/
        
    }
    
    @Test
    public void test6() {
    	List<Map<String, Object>> list = new ArrayList<>();
    	Map<String, Object> map1 = new HashMap<>();
    	map1.put("name", "yangkai");
    	map1.put("openid", "123456");
    	map1.put("dept", "dev");
       	Map<String, Object> map2 = new HashMap<>();
    	map2.put("name", "gaowenjuan");
    	map2.put("openid", "123456789");
    	map2.put("dept", "qa");
       	list.add(map1);
       	list.add(map2);
       	
       	list.stream().map(m -> JSONObject.toJavaObject(new JSONObject(m), User.class));
    }
    
    @Test
    public void test7() {
    	
    	User u1 = new User("yangkai", "123456", "dev", 29);
    	User u2 = new User("gaowenjuan", "123456789", "qa", 28);
    	User u3 = new User("xx", "1", "qa", 28);
    	User u4 = new User("leoliangyixun", "123456789", "design", 26);
    	List<User> users = Lists.newArrayList(u1, u2, u3, u4);
    	Map<String, User> map1 = users.stream().collect(Collectors.toMap(User::getName, v -> v));
    	//Map<String, User> map2 = users.stream().collect(Collectors.toMap());
    	Map<Integer, List<User>> map = users.stream().collect(Collectors.groupingBy(u -> u.getAge()));
    	//Map<Integer, List<User>> map3 = users.stream().collect(Collectors.groupingBy());
    	System.out.println(map);
    	Map<String, User> map4 = users.stream().collect(Collectors.toMap(u -> u.getName(), v -> v));
    	Map<Integer, User> map5 = users.stream().collect(Collectors.toMap(u -> u.getAge(), v -> v));
    	
    }
    
    @Test
    public void test8() {
    	Stream.of("one", "two", "three", "four")
    	 .filter(e -> e.length() > 3)
    	 .peek(e -> System.out.println("Filtered value: " + e)).collect(null);
    }
    
    @Test
	public void test9() {
    	List<Integer> nums = Arrays.asList(1, 2, 3, 4);
    	List<Integer> squareNums = nums.stream().map(n -> n * n).collect(Collectors.toList());
    	
       	List<Integer> squareNums2 = nums.stream().map(e -> e).collect(Collectors.toList());
	}
    
    @Test
	public void test10() {
    	
	}
    
//  @Test
//  public void test6() {
//      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//      numbers.parallelStream()
//             .forEach(out::println);  
//  }
    
    @Data
    public class User {
        private String name;
        private String openid;
        private String dept;
        private Integer age;
        private Address address;
        public User() {

        }
        
        public User(String name, String openid, String dept) {
            this.name = name;
            this.openid = openid;
            this.dept = dept;
        }
        
        public User(String name, String openid, String dept, Integer age) {
            this.name = name;
            this.openid = openid;
            this.dept = dept;
            this.age = age;
        }
        
        @Override
        public String toString() {
            return "User[name=" + name + ", openid=" + openid + ", dept=" + dept + "]";
        }
    }

    @Data
    public class Address {
    	private String name;
    }
}
