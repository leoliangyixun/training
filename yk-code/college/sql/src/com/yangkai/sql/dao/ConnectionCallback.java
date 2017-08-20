package com.yangkai.sql.dao;

import java.sql.Connection;

public interface ConnectionCallback <T>{
	public T doInConnectionCallback(Connection conn);
}
