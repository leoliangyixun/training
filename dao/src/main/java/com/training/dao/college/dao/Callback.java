package com.training.dao.college.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface Callback<T> {
	public T doInCallback(Connection conn,PreparedStatement ps,ResultSet rs);
}
