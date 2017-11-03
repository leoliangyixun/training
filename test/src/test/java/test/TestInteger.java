/**
 * 
 */
package test;

import com.hujiang.basic.framework.rest.validation.annotation.In;
import org.junit.Test;

import java.util.Objects;

/**
 * @author yangkai
 *
 */
public class TestInteger {
    
    @Test
    public void test() {
        int a = -0x2072;
        int b = -8306;
        System.out.println(a);
        System.out.println(a == b);
    }

    @Test
    public void test2() {
        Integer a = null;
        System.out.println(a == 2);
    }

    @Test
    public void test3() {
        Integer a = 2000;
        a = a == 1 ? 0 : 2000;
        System.out.println(a);
    }

    @Test
    public void test4() {
        Integer a = 2000;
        Integer b = 2000;
        System.out.println(a == b);

        int c = 3000;
        int d = 3000;
        System.out.println(c == d);

        Integer e = 2000;
        Integer f = 3000;
        System.out.println(f > e);

        Integer g = null;
        Integer h = null;
        System.out.println(Objects.equals(g, h));
        System.out.println(g == h);

    }

    @Test
    public void test5() {
        long a = 123455 % 64;
        System.out.println(new Long(a).intValue());
    }

    @Test
    public void test6() {
        Integer a = 9999;
        Integer b = new Integer(9999);
        Integer c = new Integer(9999);
        int d = 9999;
        System.out.println(a == 9999);
        System.out.println(b == c);
        System.out.println(c == d);
        System.out.println(c == a);
    }
}
