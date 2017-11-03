package com.yangkai.sql.dao.impl;

import java.sql.Connection;
import java.util.List;

public interface Callback<T> {
	public  List<T> doInCallback(Connection conn);
}
