package test;

import java.util.HashMap;
import java.util.Map;

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

}
