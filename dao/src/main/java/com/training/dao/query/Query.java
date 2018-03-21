package com.training.dao.query;

import com.training.dao.model.Entity;

import java.util.List;

/**
 * Created by yangkai on 2016/10/9.
 */
public interface Query<T extends Query<?, ?>, U extends Entity> {

    T asc();

    T desc();

    long count();

    U single();

    List<U> list();

    List<U> listPage(int offset, int limit);

}
