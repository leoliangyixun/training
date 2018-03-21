package com.hujiang.dsp;

import com.hujiang.basic.framework.core.dao.pagination.Page;
import com.hujiang.dsp.a8admin.constants.SysConstants;
import com.hujiang.dsp.a8admin.vo.criteria.Sortable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangkai on 2017/2/26.
 */
public class PageBuilder<T> {

    protected Page<T> page;

    private PageBuilder() {
    	page = new Page<>(1, 20);
        page.setOrderby("id desc");
        page.addParam("offset", 0);
        page.addParam("limit", 20);
        page.addParam("orderBy", "id");
        page.addParam("direction", "desc");
    }

    private PageBuilder(int pageNo, int limit) {
        pageNo = pageNo < 1 ? 1 : pageNo;
        limit = limit < 1 ? Integer.MAX_VALUE : limit;
    	page = new Page<>(pageNo, limit);
        page.setOrderby("id desc");
        page.addParam("offset", pageNo < 1 ? 1 : (pageNo - 1) * limit);
        page.addParam("limit", limit);
        page.addParam("orderBy", "id");
        page.addParam("direction", "desc");
    }
    //可以建一个静态内部类Builder<T>
/*    public static PageBuilder<Object> create() {
        return new PageBuilder<>(1, 20);
    }
    public static PageBuilder<Object> create(int pageNo, int limit) {
        return new PageBuilder<>(pageNo, limit);
    }*/

    public static <T> PageBuilder<T> create() {
        return new PageBuilder<>(1,20);
    }

    public static <T> PageBuilder<T> create(int pageNo, int limit) {
        return new PageBuilder<>(pageNo, limit);
    }

    public PageBuilder<T> status(Integer status) {
        if (status != null) {
            page.addParam("status", status);
        }

        return this;
    }

    public PageBuilder<T> put(String key, Object value) {
        if (StringUtils.isNotBlank(key)) {
            if (value instanceof String) {
                String val = (String) value;
                if (StringUtils.isBlank(val)) {
                    return this;
                }
            }
            page.addParam(key, value);
        }

        return this;
    }

    public PageBuilder<T> simple(boolean simple) {
        page.addParam("simple", simple);
        return this;
    }

    public PageBuilder<T> likeOrIdEqual(String criteria) {
        if (StringUtils.isNotBlank(criteria)) {
            Matcher m = Pattern.compile("([%_])+").matcher(criteria);
            if(m.find()){
                criteria = m.replaceAll("\\\\$1");
            }

            page.addParam("criteria", "%" + criteria + "%");

            if (NumberUtils.isNumber(criteria)) {
                page.addParam("orId", Integer.valueOf(criteria));
            }
        }

        return this;
    }

    public PageBuilder<T> sort(Sortable sort) {
        if (sort != null) {
            page.setOrderby(sort.getOrderBy() + SysConstants.BLANK + sort.getDirection());
            page.addParam("orderBy", sort.getOrderBy());
            page.addParam("direction", sort.getDirection());
        }

        return this;
    }
    public Page<T> build() {
        return page;
    }

/*    @SuppressWarnings("unchecked")
    public <V extends T> Page<V> build() {
        return (Page<V>)page;
    }*/
}
