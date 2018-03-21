package test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hujiang.basic.framework.core.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangkai on 2017/7/14.
 */
public class TestGuava {

    @Test
    public void test() {
        Set<Integer> ids = Sets.newHashSet(1,2,3,4,5,6,7,8,9,10,11);
        System.out.println(ids);
    }


    @Test
    public void test3() {
        Maps.newHashMap(null);
    }
    private AppDao appDao = new AppDao();

    private LoadingCache<String, App> appLocalCache = CacheBuilder.newBuilder()
            .expireAfterAccess(2, TimeUnit.HOURS).build(new CacheLoader<String, App>() {
                @Override
                public App load(String key) throws Exception {
                    //Optional.ofNullable(appDao.load(key)).orElse(null);
                    return appDao.load(key);
                }
            });


    public static class AppDao {
        public App load(String appKey) {
           return Objects.equals(appKey, "10") ? null : new App(appKey, null,  null, null);
        }
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class App {
        private String appName;
        private String appKey;
        private String appSecret;
        private Boolean status;

        @Override
        public String toString() {
            return JsonUtil.object2JSON(this);
        }
    }

    @Test
    public void testLoadingCache1() throws Exception{
        App app = appLocalCache.get(null);
        System.out.println(app);
        App app1 = appLocalCache.get("1");
        System.out.println(app1);
        App app2 = appLocalCache.get("2");
        System.out.println(app2);
        // App app10 = appLocalCache.get("10");
       // System.out.println(app10);
    }


    @Test
    public void testList() throws Exception{
        Lists.partition(null, 2);
    }



































}
