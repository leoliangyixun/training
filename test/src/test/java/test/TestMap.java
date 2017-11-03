package test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hujiang.basic.framework.core.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

public class TestMap {

    public TestMap() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }
    
    @Test
    public void test() {
    	Map<String, Object> map = new HashMap<>();
    	map.put("name", "yangkai");
    	map.put("age", 20);
    	map.put("name", null);
    	System.out.println(map);
    	
    }

    @Test
    public void filterMap() {
        Map<String, String> paramData = null;
        Map<String, Object> map = new HashMap<>();
        map.put("host", "xxx");
        map.put("id", 100);
        map.put("dog", new Dog("lasi", 2));
        map.put("char", 'd');
        map.put("float", 10.0f);
        paramData = map.entrySet().stream().filter(entry -> entry.getValue() instanceof String)
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> String.valueOf(entry.getValue())));
//        paramData = map.entrySet().stream().filter(entry -> entry.getValue() instanceof String)
//                .collect(Collectors.toMap( entry -> entry.getKey(), entry -> String.valueOf(entry.getValue())));

        System.out.println(paramData);
    }

    @Test
    public void cast() {
        Context context = new Context();
        context.setAttribute("dog", new Dog("lasi", 2));
        context.setAttribute("id", 100);
        context.setAttribute("host", "yangkai");
        context.setAttribute("dogs", Lists.newArrayList(new Dog("mm", 2), new Dog("gg", 3)));

        Dog dog = context.getAttribute("dog", Dog.class);
        System.out.println(dog);
    }

    @Test
    public void cast2() {
        Context context = new Context();
        context.setAttribute("dog", new Dog("lasi", 2));
        context.setAttribute("id", 100);
        context.setAttribute("host", "yangkai");
        context.setAttribute("dogs", Lists.newArrayList(new Dog("mm", 2), new Dog("gg", 3)));
      //  List dogs= context.getAttribute("dogs", List.class);
        Object obj = context.getAttribute("dogs");

        List<Dog> dogs = JsonUtil.json2Reference( JsonUtil.object2JSON(obj), new TypeReference<List<Dog>>(){});
        Dog dog =  JsonUtil.json2Reference( JsonUtil.object2JSON(obj), new TypeReference<Dog>(){});
        List<Dog> ss = (List<Dog>)obj;
      //  Collections.checkedList(dogs, Dog.class);

        System.out.println(dogs);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Dog {
        private String name;
        private Integer age;
    }


    public static class Context {
        private Map<String, Object> attributes = Maps.newHashMap();

        public Object getAttribute(String key) {
            return attributes.get(key);
        }

        public <T> T getAttribute(String key, Class<T> clazz) {
            return clazz.cast(getAttribute(key));
        }

        public void setAttribute(String key, Object value) {
            attributes.put(key, value);
        }
    }

}
