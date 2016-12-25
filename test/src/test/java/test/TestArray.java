/**
 * 
 */
package test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @author yangkai
 *
 */
public class TestArray {
    @Test
    public void testArray() {
        String[] ss = new String[]{"aa", "bb","cc"};
        String[] ss2 = new String[]{"aa", "bb","cc", "dd"};
        List<String> list = Arrays.asList(ss);
        String[] ss3 =list.toArray(ss2);
        System.out.println(list);
        System.out.println(ss3);
    }
    
}
