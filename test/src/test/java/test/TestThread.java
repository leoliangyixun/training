package test;

import com.google.common.collect.Sets;
import com.hujiang.basic.framework.core.threads.ThreadPool;
import com.hujiang.basic.framework.core.util.AppUtil;
import com.hujiang.basic.framework.core.util.JsonUtil;
import com.training.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class TestThread {

    private ExecutorService executor = ThreadPool.newFixedThreadPool("multi-ds-execute", AppUtil.getSystemProcessors(), 2000);

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserUnion {
        protected Long unionId;
        protected Long userId;
        protected String userDomain;
        protected String unionDomain;

        public UserUnion(Long userId, String userDomain) {
            this.userId = userId;
            this.userDomain = userDomain;
        }

        public UserUnion(Long unionId, Long userId, String userDomain) {
            this.unionId = unionId;
            this.userId = userId;
            this.userDomain = userDomain;
        }

        @Override
        public String toString() {
            return JsonUtil.object2JSON(this);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UnionUser {
        protected Long unionId;
        protected Long userId;
        protected String userDomain;
        protected String unionDomain;

        @Override
        public String toString() {
            return JsonUtil.object2JSON(this);
        }
    }

    public static class UnionService {

        public List<UserUnion> getUnionUser(Set<Long> ids, String userDomain) {
/*            if(ids.size() == 1){
                throw new IllegalArgumentException();
            }*/
/*            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ids.stream().map(id -> new UserUnion((long)new Random().nextInt(10000), id,  userDomain)).collect(Collectors.toList());*/
            //return null;
            return Lists.newArrayList();
        }

    }

    @Test
    public  void testAsyn() {
        UnionService service = new UnionService();
        Set<Set<Long>> ids = Utils.splitByTableIndex(Sets.newHashSet(1L, 2L,3L, 64L, 65L, 66L, 67L, 68L, 69L,70L));
        long start = System.currentTimeMillis();

        List<CompletableFuture<List<UserUnion>>> features = ids.stream()
                .map(e -> CompletableFuture.supplyAsync(() -> service.getUnionUser(e, "hj"), executor))
                //.map(f -> f.thenApply())
                .collect(Collectors.toList());
        List<List<UserUnion>> users = features.stream().map(CompletableFuture::join).collect(Collectors.toList());

        long end = System.currentTimeMillis();

        System.out.println(users);
        System.out.println("异步costs: " + (end -start)+" ms");
    }

    @Test
    public  void testSync() {
        UnionService service = new UnionService();
        Set<Set<Long>> ids = Utils.splitByTableIndex(Sets.newHashSet(1L, 2L,3L, 64L, 65L, 66L, 67L, 68L, 69L,70L));
        long start = System.currentTimeMillis();

        List<List<UserUnion>> users = ids.stream().map(e -> service.getUnionUser(e, "hj")).collect(Collectors.toList());

        long end = System.currentTimeMillis();
        System.out.println(users);
        System.out.println("同步costs: " + (end -start) +" ms");
    }

    @Test
    public  void testAsyn2() {
        UnionService service = new UnionService();
        Set<Set<Long>> ids = Utils.splitByTableIndex(Sets.newHashSet(1L, 2L,3L, 64L, 65L, 66L, 67L, 68L, 69L,70L));
        long start = System.currentTimeMillis();
        List<CompletableFuture<List<UserUnion>>> features = ids.stream()
                .map(e -> CompletableFuture.supplyAsync(() -> service.getUnionUser(e, "hj"), executor))
                .collect(Collectors.toList());
        List<List<UserUnion>> users = features.stream().map(CompletableFuture::join).collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println(users);
        System.out.println("异步: " + (end - start) +" ms");
        start = System.currentTimeMillis();
        List<UserUnion> list = users.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(list);
        end = System.currentTimeMillis();
        System.out.println("同步流: " + (end - start) +" ms");
        start = System.currentTimeMillis();
        list = users.parallelStream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(list);
        end = System.currentTimeMillis();
        System.out.println("异步流: " + (end - start) +" ms");
        System.out.println(AppUtil.getSystemProcessors());
    }

    @Test
    public  void testAsyn3() {
        UnionService service = new UnionService();
        Set<Set<Long>> ids = Utils.splitByTableIndex(Sets.newHashSet(1L, 2L,3L, 64L, 65L, 66L, 67L, 68L, 69L,70L));

        List<UserUnion> users = ids.stream().map(e -> service.getUnionUser(e, "hj")).flatMap(e -> e.stream()).collect(Collectors.toList());

        System.out.println(users);

    }

    @Test
    public void testExecutorService1() {

    }

}
