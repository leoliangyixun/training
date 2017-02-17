package test;

import org.junit.Test;
import org.springframework.cglib.transform.impl.InterceptFieldTransformer;

public class IntegerTest {

    public IntegerTest() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        int a = 1;
        Integer b = new Integer(1);
        System.out.println(a==b);

    }
    
    @Test
    public void test() {
        Integer a =123456;
        Integer b = new Integer(123456);
        System.out.println(a==b);
    }

}
