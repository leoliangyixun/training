package test;

import com.google.common.collect.Sets;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.Collection;
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
        Set<Integer> set1 = Sets.newHashSet(1,2,3);
        Set<Integer> set2 = Sets.newHashSet(4,5,6);
        set1.addAll(set2);
        Collection<Integer> collection = CollectionUtils.union(set1, set2);
        System.out.println(set1);
        System.out.println(collection);
    }

    @Test
    public void test2() {
        Collection<Object> col = org.apache.commons.collections.CollectionUtils.intersection(Sets.newHashSet(), Sets.newHashSet());
        System.out.println(col);
        System.out.println(org.apache.commons.collections.CollectionUtils.isEmpty(col));
    }

}
