/**
 * 
 */
package test;

import java.util.Comparator;

import org.junit.Test;

/**
 * @author yangkai
 *
 */
public class EffectiveJavaTest {
    @Test
    public void test48_1() {
        double a = 1.03;
        double b = 0.42;
        System.out.println(a - b);
        
        double c = 1.20;
        double d = 0.10;
        System.out.println(c - d);
    }
    
    @Test
    public void test49_1() {
        Comparator<Integer> naturalOrder = new Comparator<Integer>() {
            public int compare(Integer first, Integer second) {
                return first < second ? -1 : (first == second ? 0 : 1);
            }
        };
     
        int result = naturalOrder.compare(new Integer(42), new Integer(42));
        System.out.println(result);
        
        Integer a = new Integer(9);
        Integer b = new Integer(9);
        System.out.println(a == b);
    }
    
    @Test
    public void test49_2() {
        long start = System.currentTimeMillis();
        Long sum1 = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum1 += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum1 = " + sum1 + ", cost: " + (end - start) + " ms");
        
        start = System.currentTimeMillis();
        long sum2 = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum2 += i;
        }
        end = System.currentTimeMillis();
        System.out.println("sum2 = " + sum2 + ", cost: " + (end - start) + " ms");
    }
    
    
    @Test
    public void test51_1() {

    
    }
}
