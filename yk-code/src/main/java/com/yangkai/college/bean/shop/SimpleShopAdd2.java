package com.yangkai.bean.shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleShopAdd2 {
	 int n=0;
	 Connection conn = null;
	 Statement stmt = null;
	 PreparedStatement price_pstmt=null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 ResultSet price_rs=null;
	 String url = "jdbc:mysql://localhost:3306/onlineshop?user=root&password=root";
	public SimpleShopAdd2() 
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
	public void addShopGoods(String name,int num) 
	{
		double price=0.0;
		String str="SELECT price FROM goods WHERE name=?";
		String sql="INSERT INTO shopbus(name,num,total) VALUES(?,?,?)";
		try {
			price_pstmt=conn.prepareStatement(str,ResultSet.TYPE_SCROLL_SENSITIVE ,
					ResultSet.CONCUR_UPDATABLE);
			price_pstmt.setString(1,name);
			price_rs=price_pstmt.executeQuery();
			
			while(price_rs.next())
			{
				price=price_rs.getDouble("price");
			}
			pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE ,
					ResultSet.CONCUR_UPDATABLE );
			pstmt.setString(1,name);
			pstmt.setInt(2,num);
			pstmt.setDouble(3,num*price);
			n=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				price_pstmt.close();
				pstmt.close();
				price_rs.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	public static void main(String[] args)
	{
		SimpleShopAdd2 d=new SimpleShopAdd2();
		d.addShopGoods("����",3);
		
	}
}
