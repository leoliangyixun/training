package test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by yangkai on 2017/8/14.
 */
public class TestCollection {
    private final static int MAX_TOUSER = 10000;
    private final static int MAX_TOUSER_PER_ROUND = 100;

    public List<List<String>> separate(Collection<String> source, int quantity) {
        if (CollectionUtils.isNotEmpty(source)) {
            List<List<String>> separatedSources = Lists.newArrayList();
            int count = quantity <= 0 ? MAX_TOUSER_PER_ROUND : quantity;
            List<String> target = Lists.newArrayList(source);
            int size = target.size();
            int loop = size % count == 0 ? size / count : (size / count) + 1;
            for (int i = 0; i < loop; i++) {
                int start = i * count;
                int end = (i * count) + count;
                List<String> subSeparatedSource = target.subList(start, Math.min(end, size));
                separatedSources.add(Lists.newArrayList(subSeparatedSource));
            }

            return separatedSources;
        }

        return null;
    }

    public List<Set<String>> separate(Set<String> source, int quantity) {
        if (CollectionUtils.isNotEmpty(source)) {
            List<Set<String>> separatedSources = Lists.newArrayList();
            int count = quantity <= 0 ? MAX_TOUSER_PER_ROUND : quantity;
            List<String> target = Lists.newArrayList(source);
            int size = target.size();
            int loop = size % count == 0 ? size / count : (size / count) + 1;
            for (int i = 0; i < loop; i++) {
                int start = i * count;
                int end = (i * count) + count;
                List<String> subSeparatedSource = target.subList(start, Math.min(end, size));
                separatedSources.add(Sets.newHashSet(subSeparatedSource));
            }

            return separatedSources;
        }

        return null;
    }

    @Test
    public void test() {
        Set<String> ids = Sets.newHashSet("aaa", "bbb", "ccc", "aaa","ddd","eee","fff","ggg","hhh", "iii", "jjj");
        List<Set<String>> separatedIds = separate(ids, 3);
        System.out.println(separatedIds);
    }

    @Test
    public void test2() {
        //List<Integer> ids = Lists.newArrayList(1);
        List<Integer> ids = Lists.newArrayList(1,2,3);
        //List<Integer> ids = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10,11);
        //List<Integer> ids = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
        //List<Integer> ids = Lists.newArrayList(1,2,3,4,5,6,7,8,9);
        System.out.println(ids);

        int length = ids.size();
        int offset = 7;
        int loop = length % offset == 0 ? length / offset : (length / offset) + 1;
        System.out.println(loop);

        for (int i = 0; i < loop; i++) {
            int start = i * offset;
            int end = (i * offset) + offset;
            List<Integer> _ids = ids.subList(start, Math.min(end, length));
            System.out.println(_ids);
        }

    }
}
