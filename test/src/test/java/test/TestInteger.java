/**
 * 
 */
package test;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

/**
 * @author yangkai
 *
 */
public class TestInteger {
    
    @Test
    public void test_1() {
        int a = 1;
        Integer b = new Integer(1);
        System.out.println(a==b);

    }
    
    @Test
    public void test_2() {
        Integer a =123456;
        Integer b = new Integer(123456);
        System.out.println(a==b);
    }
    
    @Test
    public void test_3() {
    	
        System.out.println( NumberUtils.toInt("1"));
        System.out.println(Integer.parseInt("2"));
    }
    
    @Test
    public void test() {
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        Integer c = new Integer(2);
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a < c);
        
    }
    
    @Test
    public void test2() {
        int a = 0;
        Integer x = new Integer(0);
        Calculator calculator = new Calculator();
        System.out.println(calculator.add(a));
        System.out.println(a);
        System.out.println(calculator.add(x));
        System.out.println(x);
       
        
    }
    
    @Test
    public void test3() {
    	//int a = 9999999999;
    }
    
    public static class Calculator {
        public int add(Integer i) {
            return ++i;
        }
    }
    
    @Test
    public void test4() {
        int  a = 40;
        System.out.println((float)a);
        System.out.println(a/100.0);

     }
    
	public static void main(String[] args) {
/*		Float a = 0.00f;
		Float b = 1.00f;
		Float c = 1.00f;
		for(int i = 0; i < 2; i++) {
			//a += b + c;
			a = a + b + c;
			//a += b;
		}
		System.out.println(a);
*/
	    
	    
/*	    Float f = new Float(100.00f);
	    System.out.println(f.intValue());*/
	    Integer a = 1020;
	    System.out.println(a.floatValue()/100);
	}
	
	   @Test
	    public  void test5() {
	        Double a = 100.90;
	        System.out.println(a.intValue());

	    }
	    
	    @Test
	    public  void test6() {
	        float a = 0.99956936F;
	        System.out.println(a >= 0.852F);
	        System.out.println(Float.MAX_VALUE);
	        System.out.println(Float.MIN_VALUE);
	        System.out.println(Double.MAX_VALUE);
	        System.out.println(Double.MIN_VALUE);
	    }
	    
	    @Test
	    public  void test7() {
	    	Integer a = new Integer(5200);
	    	int b = 5200;
	    	Integer c = 5200;
//	    	true
//	    	false
//	    	true
//	    	true
	    	System.out.println(a == b);
	    	System.out.println(a == c);
	    	System.out.println(b == c);
	    	System.out.println(c == 5200);
	    	
	    	
	    }

}
