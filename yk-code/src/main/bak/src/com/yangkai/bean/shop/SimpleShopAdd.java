package com.yangkai.bean.shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleShopAdd {
	 Connection conn = null;
	 Statement stmt = null;
	 PreparedStatement price_pstmt=null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 ResultSet price_rs=null;
	 int n=0;
	 String url = "jdbc:mysql://localhost:3306/onlineshop?user=root&password=root";
	public SimpleShopAdd() 
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public void addShopGoods(String name,int num,double total) 
	{
		String sql="INSERT INTO shopbus(name,num,total) VALUES(?,?,?)";
		
		try {
			long start=System.currentTimeMillis();
			for(int i=0;i<1000;i++)
			{
			pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE ,
					ResultSet.CONCUR_UPDATABLE );
			pstmt.setString(1,name);
			pstmt.setInt(2,num);
			pstmt.setDouble(3,total);
			n+=pstmt.executeUpdate();
			}
			long end=System.currentTimeMillis();
			System.out.println(end-start);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try{
				pstmt.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	public static void main(String[] args)
	{
		SimpleShopAdd d=new SimpleShopAdd();

		
	}

}
