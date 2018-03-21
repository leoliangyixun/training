/**
 * 
 */
package com.training.jdk.lambda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TooManyListenersException;
import java.util.stream.Stream;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * @author yangkai
 *
 */
public class TestLambda {
    

    @Test 
    public void test() {
        Collection<User> list = new ArrayList<>();
        list.add(new User(1, "alisa"));
        list.add(new User(2, "kerwin"));
        list.add(new User(3, "annie"));
        User u1 = list.stream().findFirst().get();
        System.out.println(u1);
        
        Collection<User> set = new HashSet<>();
        
        //set.add(new User(4, "alisa gao"));
        set.add(new User(5, "kerwin yang"));
        set.add(new User(6, "annie gao"));
        User u2 = set.stream().findFirst().get();
        System.out.println(u2);
       
        
        
    }
    
    @Test 
    public void test2() {

        Map<Integer, String> map = new HashMap<>();
        List<Integer> ids = new ArrayList<>();
        ids.add(1);        
        ids.add(2);        
        ids.add(3);
        
        map.put(1, "alias");
        map.put(2, "kerwin");
        map.put(3, "annie");
        ids.stream().forEach(id -> { if (id ==2) {map.remove(id);}});
        System.out.println(map);

    }
    
    @Test
    public void test3() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "alisa"));
        list.add(new User(2, "kerwin"));
        list.add(new User(3, "annie"));
        System.out.println(list);
       
        list = Arrays.asList(list.stream().filter(user -> user.getId() != 3).toArray(User[]::new));
        System.out.println(list);
        List<User> users = new ArrayList<>();
        
        users.addAll(list);
        users.add(new User(4, "shit"));
        System.out.println(users);

    }
    
    @Test
    public void testLamdba() {
    	Stream<User> stream = Stream.of(new User(1, "alisa"), new User(2, "kerwin"), new User(3, "annie"));
    	Stream<String> ns = stream.map(user -> {
    		return user.getName();
    	});
    	
    }
    
    
    
    public static class User implements Serializable {


        private static final long serialVersionUID = 3987964147135663049L;
        
        private Integer id;
        private String name;
        
        
        public User(Integer id, String name) {
            super();
            this.name = name;
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        

        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
        
    }
}
