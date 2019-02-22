package com.yangkai.jdbc3.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yangkai.jdbc.tools.ConnectionTool;

public class TransactionDemo {

	public static void main(String[] args) 
	{
		transactionSubmit() ;
	}
	@SuppressWarnings("null")
	public static void transactionSubmit() 
	{
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		conn=ConnectionTool.getConnection();
		try {
			conn.setAutoCommit(false);	
			String sql1="update user set money=money-100 where id=?";
			ps=conn.prepareStatement(sql1);
			ps.setInt(1, 1);
			int count=ps.executeUpdate();
			System.out.println(count);
			
			float m=0.0f;
			String sql2="SELECT money FROM user WHERE id=?";
			ps=conn.prepareStatement(sql2);
			ps.setInt(1, 2);
			rs=ps.executeQuery();
			if(rs.next()){
				m=rs.getFloat("money");
			}
			
			System.out.println("------before throw------");
			conn.commit();
			if(m>500){
				throw new RuntimeException("���������");
			}
			System.out.println("------after throw------");
			String sql3="UPDATE user SET money=money+100 WHERE id=?";
			ps=conn.prepareStatement(sql3);
			ps.setInt(0, 2);
			ps.executeUpdate();
			System.out.println("------after ps.executeUpdate()------");
			System.out.println("------before con.commit()------");
			conn.commit();
			System.out.println("------after con.commit()------");
		} catch (SQLException e) {
			try {
				if(conn!=null){
					conn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println("�����쳣");
			e.printStackTrace();
		}catch(RuntimeException e){
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			ConnectionTool.freeConnection(rs, ps, conn);
		}
	}
}

