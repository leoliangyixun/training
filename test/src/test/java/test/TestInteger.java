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

    @Test
    public void test7() {

        System.out.println(Integer.MAX_VALUE);
    }

    @Test
    public void test8() {
        int a = 33462689;
        Long b = 33462689L;
        System.out.println(Objects.equals((long)a, b));
    }

    @Test
    public void test9() {
        Integer a = new Integer(9999);
        int b = 9999;
        Integer c = 9999;
        System.out.println(a == b);
        System.out.println(b == c);
        System.out.println(a == c);
    }

    @Test
    public void test10 () {
        String a = "a";
        String b = " 10";
        String c = "+10";
        String d = "-10";

       // System.out.println(Integer.valueOf(a));
        System.out.println(Integer.valueOf(b));
       // System.out.println(Integer.valueOf(c));
       // System.out.println(Integer.valueOf(d));
    }

    @Test
    public void test11 () {
       // Integer a = 0;
        Integer a = null;
        Long x = (long) a;
        System.out.println(x);
    }


    @Test
    public void testString2Integer() {
        String s1 = "10";
        String s2 = "+10";
        String s3 = "-10";
        String s4 = " + 10";
        Integer a = 10;
        System.out.println(s1 + ": " +Integer.valueOf(s1));
        System.out.println(s2 + ": " + Integer.valueOf(s2));
        System.out.println(s3 + ": " + Integer.valueOf(s3));

        System.out.println(s1 + ": " + (a + Integer.valueOf(s1)));
        System.out.println(s2 + ": " + (a +Integer.valueOf(s2)));
        System.out.println(s3 + ": " + (a +Integer.valueOf(s3)));

        // System.out.println(s4 + ": " +Integer.valueOf(s4));

    }

    @Test
    public void testIntegerEqual() {
        System.out.println(1000 == 1000);
        System.out.println(100 == 100);
    }
}
