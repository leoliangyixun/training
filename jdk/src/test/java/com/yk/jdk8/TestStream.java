/**
 * 
 */
package com.yk.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

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
    
    public class User {
        public String name;
        public String openid;
        public String dept;
        
        public User(String name, String openid, String dept) {
            this.name = name;
            this.openid = openid;
            this.dept = dept;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public String getOpenid() {
            return openid;
        }
        public void setOpenid(String openid) {
            this.openid = openid;
        }
        public String getDept() {
            return dept;
        }
        public void setDept(String dept) {
            this.dept = dept;
        }
        
        @Override
        public String toString() {
            return "User[name=" + name + ", openid=" + openid + ", dept=" + dept + "]";
        }
        
        
        
        
    }

}
