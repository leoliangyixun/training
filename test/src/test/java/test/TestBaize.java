package test;

import com.hujiang.notifycenter.qingniao.model.dto.result.SendResultSet;

import com.google.common.collect.Lists;

import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author yangkai
 * @date 2019-06-18
 * @email yangkai@hujiang.com
 * @description
 */
public class TestBaize {

    @Test
    public void test() {
        List<Integer> list = Lists.newArrayList(1,2,3);
        System.out.println(list);
        list.clear();
        System.out.println(list);
    }

    @Test
    public void test2() {
        SendResultSet set = SendResultSet.builder().batchId(new Date().getTime()).build();
        List<SendResultSet> list = Lists.newArrayList();
        list.add(set);
        list.add(set);
        list.add(set);
        System.out.println(list);
    }

}
