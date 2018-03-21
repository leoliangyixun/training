package com.training.dao.query;

import com.training.dao.model.Entity;

public interface MybatisQuery<E extends Entity> extends Query<MybatisQuery<E>, E> {

    MybatisQuery<E> or(Expression conditions);

    MybatisQuery<E> and(Expression conditions);

    MybatisQuery<E> where(Expression conditions);

    MybatisQuery<E> from(String statement);
}
