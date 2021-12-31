package test;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

public class TestArray {

    @Test
    public void testArr() {
        long[] a = {1L, 2L, 3L, 4L};
        Integer[] b = {1, 2, 3, 4};
        System.out.println(a.getClass().isArray());
        System.out.println(b.getClass().isArray());
        Object obj = new Integer[]{1, 2, 3, 4};
        Object[] s = (Object[]) obj;
        System.out.println(s.getClass().isArray());
        System.out.println(s);

        Object object = new Object[]{"cc"};
        if (object.getClass().isArray() && ArrayUtils.isEmpty((Object[]) object)) {
            System.out.println("xxx");
        }

    }

    @Test
    public void testArr1() {
        Object[] items = new Object[5];
        System.out.println(items.length);
    }
}
