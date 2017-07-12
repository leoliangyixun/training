/**
 * 
 */
package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import org.apache.commons.collections4.MapUtils;
import org.junit.Test;

/**
 * @author yangkai
 *
 */
public class TestCollection {
    @Test
    public void testArray() {
        String[] ss = new String[]{"aa", "bb","cc"};
        String[] ss2 = new String[]{"aa", "bb","cc", "dd"};
        List<String> list = Arrays.asList(ss);
        String[] ss3 =list.toArray(ss2);
        System.out.println(list);
        System.out.println(ss3);
    }

    @Test
    public void testMap() {
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("name", "yangkai");
            put("age", 30);
            put("area", "shanghai");
            put("email", null);
            put("address", null);
        }};

        System.out.println(map);
    }

    @Test
    public void testMap2() {

    }

    @Test
    public void testObject() {

    }
    
}
