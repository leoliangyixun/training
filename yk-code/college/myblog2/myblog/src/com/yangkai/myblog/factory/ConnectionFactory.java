package com.yangkai.myblog.factory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.yangkai.myblog.tools.ConnectionTool;
public class ConnectionFactory {
	
	private ConnectionFactory() {
		
	}
	public static Connection getConnection() 
	{
		return ConnectionTool.getPoolConnection();
		//return ConnectionTool.getConnection();
	}
	public static void freeConnection(ResultSet rs)
	{
		ConnectionTool.freeConnection(rs);
	}
	public static void freeConnection(PreparedStatement pstmt,Connection conn)
	{
		ConnectionTool.freeConnection(pstmt, conn);
	}
	public static void freeConnection(ResultSet rs,PreparedStatement pstmt,Connection conn)
	{
		ConnectionTool.freeConnection(rs, pstmt, conn);
	}
}
