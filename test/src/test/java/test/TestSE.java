package test;

import com.alibaba.fastjson.annotation.JSONField;
import com.b5m.test.se.Main;
import com.hujiang.basic.framework.core.util.JsonUtil;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by yangkai on 2017/6/9.
 */
public class TestSE {

    @Test
    public void test() {
        Main.Employee e = new Main.Employee(200, "ok", "{yk}");
        e.setName("xx");
        //System.out.println(e.toString());
        System.out.println(e.clearSensitive());
    }

    @Data
    @NoArgsConstructor
    public static abstract class Entity<T extends Serializable> implements Serializable {

        private static final long serialVersionUID = 3454837606164676885L;

        protected Integer status;
        protected String message;
        protected T data;

        public Entity(Integer status, String message, T data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        public String clearSensitive() {
           // Entity<T> obj = JsonUtil.deserialize(JsonUtil.serialize(this));
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }

    @NoArgsConstructor
    @EqualsAndHashCode(callSuper=true)
    public static class Employee extends Entity<String> {

        private static final long serialVersionUID = -4742391741835085542L;
        @Getter
        @Setter
        private String name;

        public Employee(Integer status, String message, String data) {
            super(status, message, data);
            this.name = name;
        }

/*        public String clearSensitive() {
            Employee obj = JsonUtil.deserialize(JsonUtil.serialize(this));
            return obj.toString();
        }*/

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }

    }

    @Test
    public void test2() {
        Object obj = new Object() {@Getter String username = "yk"; @Getter int age = 20;};
        System.out.println(JsonUtil.object2JSON(obj));
    }


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
    public void test2_1() {
        Integer a = new Integer(1);
        Integer b = new Integer(1);
        Integer c = new Integer(2);
        System.out.println(a == b);
        System.out.println(a.equals(b));
        System.out.println(a < c);

    }

    @Test
    public void test2_2() {
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


    @Test
    public void int2long() {
        int a = 500;
        int b = 200;
        long c = a - b;
        long d = (long)(a - b);
        System.out.println(c);
        System.out.println(d);
    }
    


}
