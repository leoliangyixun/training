package test;

import com.hujiang.basic.framework.core.threads.ThreadPool;
import com.hujiang.basic.framework.core.util.JsonUtil;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

/**
 * @author yangkai
 * @date 2018/10/29
 * @email yangkai@hujiang.com
 * @description
 */
public class TestFunctionalProgramming {
    private static final ExecutorService DEVICE_INFO_THREAD_POOL = ThreadPool
        .newFixedThreadPool("deviceInfoQueryExecutor", 64, 100000);


    @Data
    @AllArgsConstructor
    public static class DeviceInfoPo {
        private String deviceId;
        private Long userId;
        private String appName;
        private String pushToken;

        @Override
        public String toString() {
            return JsonUtil.object2JSON(this);
        }

    }

    @Test
    public void testJdk8Reduce() {
        List<List<DeviceInfoPo>> list = Lists.newArrayList();
        List<DeviceInfoPo> list1 = Lists.newArrayList(new DeviceInfoPo("xxx", 1L, "xxx", "xxx-xxx-xxx-xxx"));
        List<DeviceInfoPo> list2 = Lists.newArrayList(new DeviceInfoPo("yyy", 1L, "yyy", "yyy-yyy-yyy-yyy"),new DeviceInfoPo("zzz", 1L, "zzz", "zzz-zzz-zzz-zzz"));
        List<DeviceInfoPo> list3 = null;
        List<DeviceInfoPo> list4 = Lists.newArrayList();
        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);

        List<DeviceInfoPo> all = list.stream().reduce(Lists.newArrayList(), (x, y) -> {
            Optional.ofNullable(y).filter(CollectionUtils::isNotEmpty).ifPresent(x::addAll);
            return x;
        });
        System.out.println(all);

        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
    }

}
