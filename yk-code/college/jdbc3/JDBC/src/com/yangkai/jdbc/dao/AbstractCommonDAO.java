package com.yangkai.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.yangkai.jdbc.tools.ConnectionTool;

public abstract class AbstractCommonDAO {
	public Connection conn=null;
	public PreparedStatement pstmt=null;
	public ResultSet rs=null;
	public ResultSetMetaData rsmd=null;
	public Object find (String sql,Object[] fields)
	{
		conn=ConnectionTool.getConnection();
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		Object obj=null;
		return obj;
	}
	public int insert(String sql,Object[] fields)
	{
		int count=0;
		conn=ConnectionTool.getConnection();
		try {
			pstmt=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			for(int i=1;i<=fields.length;i++)
			{
				pstmt.setObject(i, fields[i-1]);
			}
			count=pstmt.executeUpdate();
			/*
			rs=pstmt.getGeneratedKeys();
			rsmd=rs.getMetaData();
			
			if(rs.next())
			{
				id=rs.getInt(1);//不能使用字段名，因为rs的字段名与数据表的字段名不同 。
				//System.out.println(rsmd.getColumnCount());
			}
			System.out.println(rsmd.getColumnName(1));
			*/
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionTool.freeConnection(rs, pstmt, conn);
		return count;
	}
}
