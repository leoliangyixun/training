package com.hujiang.hjauth;

import com.hujiang.basic.framework.core.threads.ThreadPool;
import com.hujiang.basic.framework.dao.annotation.TargetDataSource;
import com.hujiang.oauth.core.aop.Metrics;
import com.hujiang.oauth.dao.UnionUserDao;
import com.hujiang.oauth.dao.UserUnionDao;
import com.hujiang.oauth.support.constants.MetricsType;
import com.hujiang.oauth.support.model.dto.UnionUserDetailDTO;
import com.hujiang.oauth.support.model.dto.UnionUserDetailQueryDTO;
import com.hujiang.oauth.support.model.po.UnionUserPO;
import com.hujiang.oauth.support.model.po.UserUnionPO;
import com.hujiang.oauth.support.utils.AssertUtils;
import com.hujiang.oauth.support.utils.Utils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class UnionUserService {

    private ExecutorService executor = ThreadPool.newFixedThreadPool("union-info-batch-query", 5, 2000);

    @Autowired
    private UnionUserDao unionUserDao;

    @Autowired
    private UserUnionDao userUnionDao;

    /**
     * 递归查询
     *
     * @return
     * @deprecated
     */
    @TargetDataSource("slave")
    @Metrics(type = MetricsType.SERVICE, timeout = 1000)
    public List<UnionUserDetailDTO> query(UnionUserDetailQueryDTO query) {
        AssertUtils.notNull(query, "[illegal arguments] query should not be null");
        AssertUtils.notNull(CollectionUtils.isNotEmpty(query.getUnionId()) && CollectionUtils.isNotEmpty(query.getUserId()), "[illegal arguments] user id and union id collection should be alternative");

        return Optional.of(query).filter(q -> CollectionUtils.isNotEmpty(q.getUnionId())).map(q -> {
            Map<Long, List<Long>> group = Utils.groupByTable(query.getUnionId());
            List<UnionUserPO> entities = group.entrySet().stream()
                    .map(e -> CompletableFuture.supplyAsync(() -> unionUserDao.query(e.getValue(), query.getUserDomain(), e.getKey()), executor)).collect(Collectors.toList())
                    .stream().map(CompletableFuture::join).collect(Collectors.toList())
                    .stream().flatMap(Collection::stream).collect(Collectors.toList());

            return Optional.ofNullable(entities).filter(CollectionUtils::isNotEmpty).map(e -> e.stream().map(UnionUserDetailDTO::new).collect(Collectors.toList())).orElseGet(Collections::emptyList);
        }).orElseGet(() -> Optional.of(query).filter(q -> CollectionUtils.isNotEmpty(q.getUserId())).map(q -> {
            Map<Long, List<Long>> group1 = Utils.groupByTable(query.getUserId());
            List<UserUnionPO> entities = group1.entrySet().stream()
                    .map(e -> CompletableFuture.supplyAsync(() -> userUnionDao.list(e.getValue(), query.getUserDomain(), e.getKey()), executor)).collect(Collectors.toList())
                    .stream().map(CompletableFuture::join).collect(Collectors.toList())
                    .stream().flatMap(Collection::stream).collect(Collectors.toList());

            return Optional.ofNullable(entities).filter(CollectionUtils::isNotEmpty).map(pos -> {
                Set<Long> unionIds = pos.stream().map(UserUnionPO::getUnionId).collect(Collectors.toSet());
                query.setUnionId(unionIds);
                query.setUserId(null);
                return this.query(query);
            }).orElseGet(Collections::emptyList);
        }).orElseGet(Collections::emptyList));
    }

    /**
     * 非递归查询
     *
     * @param query
     * @return
     * @deprecated
     */
    @Deprecated
    @TargetDataSource("slave")
    @Metrics(type = MetricsType.SERVICE, timeout = 1000)
    public List<UnionUserDetailDTO> query2(UnionUserDetailQueryDTO query) {
        AssertUtils.notNull(query, "[illegal arguments] query should not be null");
        AssertUtils.notNull(CollectionUtils.isNotEmpty(query.getUnionId()) && CollectionUtils.isNotEmpty(query.getUserId()), "[illegal arguments] user id and union id collection should be alternative");

        return Optional.of(query).filter(q -> CollectionUtils.isNotEmpty(q.getUnionId())).map(q -> {
            Map<Long, List<Long>> group = Utils.groupByTable(query.getUnionId());
            List<UnionUserPO> entities = group.entrySet().stream()
                    .map(e -> CompletableFuture.supplyAsync(() -> unionUserDao.query(e.getValue(), query.getUserDomain(), e.getKey()), executor)).collect(Collectors.toList())
                    .stream().map(CompletableFuture::join).collect(Collectors.toList())
                    .stream().flatMap(Collection::stream).collect(Collectors.toList());

            return Optional.ofNullable(entities).filter(CollectionUtils::isNotEmpty).map(e -> e.stream().map(UnionUserDetailDTO::new).collect(Collectors.toList())).orElseGet(Collections::emptyList);
        }).orElseGet(() -> Optional.of(query).filter(q -> CollectionUtils.isNotEmpty(q.getUserId())).map(q -> {
            Map<Long, List<Long>> group1 = Utils.groupByTable(query.getUserId());
            List<UserUnionPO> entities = group1.entrySet().stream()
                    .map(e -> CompletableFuture.supplyAsync(() -> userUnionDao.list(e.getValue(), query.getUserDomain(), e.getKey()), executor)).collect(Collectors.toList())
                    .stream().map(CompletableFuture::join).collect(Collectors.toList())
                    .stream().flatMap(Collection::stream).collect(Collectors.toList());

            return Optional.ofNullable(entities).filter(CollectionUtils::isNotEmpty).map(pos -> {
                List<Long> unionIds = pos.stream().map(UserUnionPO::getUnionId).collect(Collectors.toList());
                /*
                 * 将获取的unionId按表分组
                 * key: union_user表索引号
                 * value: union_id列表
                 */
                Map<Long, List<Long>> group2 = Utils.groupByTable(unionIds);
                // 查询出来的关联用户
                return group2.entrySet().stream()
                        .map(e -> CompletableFuture.supplyAsync(() -> unionUserDao.query(e.getValue(), query.getUserDomain(), e.getKey()), executor))
                        .collect(Collectors.toList())
                        .stream().map(CompletableFuture::join).collect(Collectors.toList())
                        .stream().flatMap(Collection::stream).collect(Collectors.toList())
                        .stream().map(UnionUserDetailDTO::new).collect(Collectors.toList());
            }).orElseGet(Collections::emptyList);
        }).orElseGet(Collections::emptyList));
    }

    /**
     *
     * @param query
     * @return
     */
    @TargetDataSource("slave")
    @Metrics(type = MetricsType.SERVICE, timeout = 1000)
    public Map<String, List<UnionUserDetailDTO>> queryBy(UnionUserDetailQueryDTO query) {
        AssertUtils.notNull(query, "[illegal arguments] query should not be null");
        AssertUtils.notNull(CollectionUtils.isNotEmpty(query.getUnionId()) && CollectionUtils.isNotEmpty(query.getUserId()), "[illegal arguments] user id and union id collection should be alternative");

        return Optional.of(query).filter(q -> CollectionUtils.isNotEmpty(q.getUnionId())).map(q -> {
            Map<Long, List<Long>> group = Utils.groupByTable(query.getUnionId());
            List<UnionUserPO> entities = group.entrySet().stream()
                    .map(e -> CompletableFuture.supplyAsync(() -> unionUserDao.query(e.getValue(), query.getUserDomain(), e.getKey()), executor)).collect(Collectors.toList())
                    .stream().map(CompletableFuture::join).collect(Collectors.toList())
                    .stream().flatMap(Collection::stream).collect(Collectors.toList());

            return Optional.ofNullable(entities).filter(CollectionUtils::isNotEmpty).map(e -> e.stream().map(UnionUserDetailDTO::new).collect(Collectors.groupingBy(v-> String.valueOf(v.getUnionId())))).orElseGet(Collections::emptyMap);
        }).orElseGet(() -> Optional.of(query).filter(q -> CollectionUtils.isNotEmpty(q.getUserId())).map(q -> {
            Map<Long, List<Long>> group1 = Utils.groupByTable(query.getUserId());
            List<UserUnionPO> entities = group1.entrySet().stream()
                    .map(e -> CompletableFuture.supplyAsync(() -> userUnionDao.list(e.getValue(), query.getUserDomain(), e.getKey()), executor)).collect(Collectors.toList())
                    .stream().map(CompletableFuture::join).collect(Collectors.toList())
                    .stream().flatMap(Collection::stream).collect(Collectors.toList());

            return Optional.ofNullable(entities).filter(CollectionUtils::isNotEmpty).map(pos -> {
                Set<Long> unionIds = pos.stream().map(UserUnionPO::getUnionId).collect(Collectors.toSet());
                query.setUnionId(unionIds);
                query.setUserId(null);
                return this.queryBy(query);
            }).orElseGet(Collections::emptyMap);
        }).orElseGet(Collections::emptyMap));
    }

    /**
     *
     * @param query
     * @return
     */
    @TargetDataSource("slave")
    @Metrics(type = MetricsType.SERVICE, timeout = 1000)
    public Map<String, List<UnionUserDetailDTO>> queryBy2(UnionUserDetailQueryDTO query) {
        return Optional.ofNullable(this.query(query)).filter(CollectionUtils::isNotEmpty).map(e -> e.stream().collect(Collectors.groupingBy(v-> String.valueOf(v.getUnionId())))).orElseGet(Collections::emptyMap);
    }
}
