package test;

import com.google.common.collect.Sets;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author yangkai
 * @date 2019-01-17
 * @email yangkai@hujiang.com
 * @description
 */
public class TestCollectionUtils {

    @Test
    public void test() {
        Set<Integer> set1 = Sets.newHashSet(1, 2, 3);
        Set<Integer> set2 = Sets.newHashSet(4, 5, 6);
        set1.addAll(set2);
        Collection<Integer> collection = CollectionUtils.union(set1, set2);
        System.out.println(set1);
        System.out.println(collection);
    }

    @Test
    public void test2() {
        Collection<Object> col = org.apache.commons.collections.CollectionUtils
            .intersection(Sets.newHashSet(), Sets.newHashSet());
        System.out.println(col);
        System.out.println(org.apache.commons.collections.CollectionUtils.isEmpty(col));
    }

    @Test
    public void testSubtract() {
        String[] arrayA = new String[]{"A", "B", "C", "D", "E", "F"};
        String[] arrayB = new String[]{"B", "D", "F", "G", "H", "K"};
        String[] arrayC = new String[]{"B", "D", "F", "G", "H", "K"};
        List<String> listA = Arrays.asList(arrayA);
        List<String> listB = Arrays.asList(arrayB);
        List<String> listC = Arrays.asList(arrayC);
        //arrayA扣除arrayB
        System.out.println(ArrayUtils.toString(CollectionUtils.subtract(listA, listB)));
        //[A, C, E]

        System.out.println(ArrayUtils.toString(CollectionUtils.subtract(listB, listA)));
        //[G, H, K]

        System.out.println(ArrayUtils.toString(CollectionUtils.subtract(listB, listC)));
        System.out.println(CollectionUtils.isEmpty(CollectionUtils.subtract(listB, listC)));
        System.out.println(ArrayUtils.toString(CollectionUtils.subtract(listC, listB)));
        System.out.println(CollectionUtils.isEmpty(CollectionUtils.subtract(listC, listB)));
    }

    @Test
    public void testSubtract2() {
        Integer[] arrayA = new Integer[]{1, 2, 3, 4, 5, 6};
        Integer[] arrayB = new Integer[]{2, 4, 6, 7, 8, 9};
        Integer[] arrayC = new Integer[]{2, 4, 6, 7, 8, 9};
        List<Integer> listA = Arrays.asList(arrayA);
        List<Integer> listB = Arrays.asList(arrayB);
        List<Integer> listC = Arrays.asList(arrayC);
        //arrayA扣除arrayB
        System.out.println(ArrayUtils.toString(CollectionUtils.subtract(listA, listB)));
        //[A, C, E]

        System.out.println(ArrayUtils.toString(CollectionUtils.subtract(listB, listA)));
        //[G, H, K]

        System.out.println(ArrayUtils.toString(CollectionUtils.subtract(listB, listC)));
        System.out.println(CollectionUtils.isEmpty(CollectionUtils.subtract(listB, listC)));
        System.out.println(ArrayUtils.toString(CollectionUtils.subtract(listC, listB)));
        System.out.println(CollectionUtils.isEmpty(CollectionUtils.subtract(listC, listB)));
    }

    @Test
    public void testSubtract3() {

        List<Integer> listA = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6});
        List<Integer> listB = null;
        System.out.println(ArrayUtils.toString(CollectionUtils.subtract(listA, listB)));
    }

    @Test
    public void testSubtract4() {

        List<Integer> listA = Arrays.asList(new Integer[]{1, 2});
        List<Integer> listB = Arrays.asList(new Integer[]{1, 2, 3});
        System.out.println(ArrayUtils.toString(CollectionUtils.subtract(listA, listB)));
        System.out.println(ArrayUtils.toString(CollectionUtils.subtract(listB, listA)));
    }

    @Test
    public void testSubtract5() {

        List<Integer> listA = Arrays.asList(new Integer[]{1, 2, 3});
        List<Integer> listB = Arrays.asList(new Integer[]{1, 2, 3});
        System.out.println(ArrayUtils.toString(CollectionUtils.subtract(listA, listB)));
        System.out.println(ArrayUtils.toString(CollectionUtils.subtract(listB, listA)));
    }

    @Test
    public void testSubtract6() {

        List<Integer> listA = Arrays.asList(new Integer[]{1, 2, 3});
        List<Integer> listB = Arrays.asList(new Integer[]{4, 5, 6});
        System.out.println(ArrayUtils.toString(CollectionUtils.subtract(listA, listB)));
        System.out.println(ArrayUtils.toString(CollectionUtils.subtract(listB, listA)));
    }

}
