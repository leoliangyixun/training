package com.training.dao.query;

import java.util.List;

/**
 * Created by yangkai on 2016/10/9.
 */
public interface Query<T extends Query<?, ?>, U> {

    T asc();

    T desc();

    long count();

    U singleResult();

    List<U> list();

    List<U> listPage(int offset, int limit);

}
