package com.yangkai.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.yangkai.jdbc.tools.ConnectionTool;

public class BatchDemo {

	public BatchDemo() {
		
	}


	public static void main(String[] args) 
	{
		batchInsert();

	}
	public static void batchInsert() 
	{
		Connection conn=null;
		PreparedStatement ps=null;
		conn=ConnectionTool.getConnection();
		//String sql="INSER INTO user(name,money) VALUES(?,?)";
		String sql="INSERT INTO account(name,sex,age) VALUES (?,?,?)";
		//String sql="select * from user";
		try {
			ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//for(int i=0;i<5;i++)
			//{
				ps.setString(1, "name");
				ps.setString(2,"femal");
				ps.setInt(3, 20);
				//ps.addBatch();
			//}
			//int[] count=ps.executeBatch();
				ps.executeUpdate();
				//ps.executeQuery();
			//System.out.println(count.length);
				//System.out.println(count);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionTool.freeConnection(ps, conn);
		}
	}

}
