package test;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yangkai
 * @date 2018/10/29
 * @email yangkai@hujiang.com
 * @description
 */
public class TestCommonCollection4 {

    @Test
    public void testEx() {
        Set<Object> set1 = Sets.newSet(1L,2L,3L, "xxx", "yyy");
        Set<Object> set2 = Sets.newSet(1L,2L,"xxx");
        set1 = set1.stream().filter(e -> !set2.contains(e)).collect(Collectors.toSet());
        System.out.println(set1);
       // CollectionUtils.exists(set, (e) -> {});
    }
}
