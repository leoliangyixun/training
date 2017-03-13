package test;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class StringTest {

    public StringTest() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        String a = new String("xxx");
        String b = new String("xxx");
        String c = new String("xxx");
        String d = new String("ggmm");
        String e = "xxx";
        String f ="xxx";
        Set<String> set = new HashSet<String>();
        set.add(a);
        set.add(b);
        set.add(c);
        set.add(d);
        set.add(e);
        System.out.println(set);
        System.out.println("a==b: " + a == b);
        System.out.println("a.equals(b): " + a.equals(b));
        System.out.println(set.contains(new String("xxx")));
        System.out.println(set.contains("xxx"));
        System.out.println("e==f:" + (e==f));
//-----------------------------------------------------------------
        String x ;
        String y ="yyy";
        x = y;
        System.out.println(x);
        
    }
    
    @Test
    public void test() {
    	System.out.println(StringUtils.split("1", ","));
    }

}
