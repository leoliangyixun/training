package com.hujiang.dsp.a8admin.model;

import com.hujiang.basic.framework.core.dao.pagination.Page;
import com.hujiang.basic.framework.core.util.NumberUtils;

/**
 * Created by yangkai on 2017/2/26.
 */
public class PageBuilder<T> {

    protected Page<T> page;

    private PageBuilder() {
    	page = new Page<>(1, 20);
    }

    private PageBuilder(int pageNo, int limit){
    	page = new Page<>(pageNo < 1 ? 1 : pageNo, limit < 1 ? Integer.MAX_VALUE : limit);
    }

    public static PageBuilder<Object> create() {
        return new PageBuilder<>();
    }

    public static PageBuilder<Object> create(int pageNo, int limit) {
        return new PageBuilder<>(pageNo, limit);
    }

    public PageBuilder<T> pageNo(int pageNo) {

        return this;
    }

    public PageBuilder<T> limit(int limit) {
        return this;
    }

    public PageBuilder<T> status(int status) {
        page.addParam("status", status);
        return this;
    }

    public PageBuilder<T> put(String key, Object value) {
        page.addParam(key, value);
        return this;
    }

    public PageBuilder<T> simple(boolean simple) {
        page.addParam("simple", simple);
        return this;
    }

    public PageBuilder<T> likeOrEqual(String keyword) {
        if (NumberUtils.isNumber(keyword)) {
            page.addParam("id", Integer.valueOf(keyword));
        } else {
            page.addParam("keyword", "%" + keyword + "%");
        }
        return this;
    }

    public <V extends T> Page<V> build() {
        return (Page<V>)page;
    }
}
