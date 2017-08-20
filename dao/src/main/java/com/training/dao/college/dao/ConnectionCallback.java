package com.training.dao.college.dao;

import java.sql.Connection;

public interface ConnectionCallback<T> {
	public T doInConnection(Connection conn);
}
