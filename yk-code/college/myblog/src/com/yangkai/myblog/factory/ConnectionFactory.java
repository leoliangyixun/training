package com.yangkai.myblog.factory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.yangkai.myblog.tools.DBConnectionUtil;
public class ConnectionFactory {
	
	private ConnectionFactory() {
		
	}
	public static Connection getConnection() 
	{
		return DBConnectionUtil.getPoolConnection();
		//return DBConnectionUtil.getConnection();
	}
	public static void freeConnection(ResultSet rs)
	{
		DBConnectionUtil.freeConnection(rs);
	}
	public static void freeConnection(PreparedStatement pstmt,Connection conn)
	{
		DBConnectionUtil.freeConnection(pstmt, conn);
	}
	public static void freeConnection(ResultSet rs,PreparedStatement pstmt,Connection conn)
	{
		DBConnectionUtil.freeConnection(rs, pstmt, conn);
	}
}
