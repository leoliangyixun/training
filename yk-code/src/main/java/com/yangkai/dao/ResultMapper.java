package com.yangkai.dao;

import java.sql.ResultSet;
import java.util.List;
public interface ResultMapper<T> {
	public List<T> getResult(ResultSet rs, List<T> objs);
	public T getResult(ResultSet rs);
}
