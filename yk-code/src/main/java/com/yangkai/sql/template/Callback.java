package com.yangkai.sql.template;

import java.sql.Connection;
import java.util.List;

public interface Callback<T> {
	public  List<T> doInCallback(Connection conn);
}
