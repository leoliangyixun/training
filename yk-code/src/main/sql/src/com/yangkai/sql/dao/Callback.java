package com.yangkai.sql.dao;

import java.sql.Connection;

public interface Callback <T>{
	public  T doInCallback(Connection conn);
}
