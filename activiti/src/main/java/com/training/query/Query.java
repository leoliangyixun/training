/**
 * 
 */
package com.training.query;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangkai
 *
 */
public interface Query<T, U extends Serializable> {

    T asc();

    T desc();

    long count();

    U singleResult();

    List<U> list();

    List<U> listPage(int offset, int limit);
}
