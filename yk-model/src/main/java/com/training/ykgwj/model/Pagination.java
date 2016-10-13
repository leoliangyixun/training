/**
 * 
 */
package com.training.ykgwj.model;

import java.util.List;

/**
 * @author yangkai
 *
 */
public class Pagination<T> implements Page {
    private int offset;
    private int limit = 10;
    private int total;
    private List<T> data;
    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }
    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }
}
