package com.yangkai.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.yangkai.jdbc.tools.ConnectionTool;

public abstract class UserAbstractDAO2 {
	public Connection conn=null;
	public PreparedStatement pstmt=null;
	public ResultSet rs=null;
	public ResultSetMetaData rsmd=null;
	public Object find (String sql,Object[] fields,UserDAOMapping mapper)
	{
		conn=ConnectionTool.getConnection();
		Object obj=null;
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0;i<fields.length;i++)
			{
				pstmt.setObject((i+1), fields[i]);
			}
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				obj=mapper.userResultSetMapping(rs); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return obj;
	}
}
