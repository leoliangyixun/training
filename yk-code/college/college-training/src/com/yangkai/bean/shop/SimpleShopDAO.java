package com.yangkai.bean.shop;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.sql.PreparedStatement;
public class SimpleShopDAO {  
	 Connection conn = null;
	 Statement stmt = null;
	 PreparedStatement price_pstmt=null;
	 PreparedStatement pstmt = null;
	 ResultSet rs = null;
	 ResultSet price_rs=null;
	 private double price=0.0;
	 String url = "jdbc:mysql://localhost:3306/onlineshop?user=root&password=root";
	public SimpleShopDAO() 
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
	public String setCharacterEncoding(String str)throws UnsupportedEncodingException 
	{	
		return new String(str.getBytes("ISO-8859-1"), "GBK");
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector getShopGoodsname() 
	{
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Vector v_name = new Vector();
		try {
			rs = stmt.executeQuery("SELECT name FROM goods");
			while (rs.next()) {
				SimpleShopBean ssb = new SimpleShopBean(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return v_name;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector showShopGoods()
	{
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Vector v_show = new Vector();
		String sql="SELECT * FROM shopbus";
		String str="SELECT price FROM goods WHERE name=?";
		try {
			rs = stmt.executeQuery(sql);
			price_pstmt=conn.prepareStatement(str,ResultSet.TYPE_SCROLL_SENSITIVE ,
					ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
			if(rs.isAfterLast()==rs.isBeforeFirst())
			{
				v_show=null;
			}
			else{
					while (rs.next()) 
					{
						price_pstmt.setString(1, rs.getString("name"));
						price_rs=price_pstmt.executeQuery();
						while(price_rs.next())
						{
							price=price_rs.getDouble("price");
						}
						SimpleShopBean ssb = new SimpleShopBean(rs.getString("name"),rs.getInt("num"),price,
								rs.getDouble("total"));
						v_show.add(ssb);
					}
			   }
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return v_show;
	}
	
	public void addShopGoods(String name,int num) 
	{
		String str="SELECT price FROM goods WHERE name=?";
		String sql="INSERT INTO shopbus(name,num,total) VALUES(?,?,?)";
		try {
			price_pstmt=conn.prepareStatement(str,ResultSet.TYPE_SCROLL_SENSITIVE ,
					ResultSet.CONCUR_UPDATABLE);
			price_pstmt.setString(1,name);
			price_rs=price_pstmt.executeQuery();
			while(price_rs.next())
			{
				price=price_rs.getFloat("price");
			}
			pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE ,
					ResultSet.CONCUR_UPDATABLE );
			pstmt.setString(1,name);
			pstmt.setInt(2,num);
			pstmt.setDouble(3,num*price);
			pstmt.executeUpdate();
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
		System.out.println(name+","+num);
	}
	public void updateShopGoodsnum(String name,int num) 
	{
		String str="SELECT price FROM goods WHERE name=?";
		String sql="UPDATE shopbus SET num=?,total=? WHERE name=?";
		try {
			price_pstmt=conn.prepareStatement(str,ResultSet.TYPE_SCROLL_SENSITIVE ,
					ResultSet.CONCUR_UPDATABLE);
			price_pstmt.setString(1,name);
			price_rs=price_pstmt.executeQuery();
			while(price_rs.next())
			{
				price=price_rs.getFloat("price");
			}
			pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, num);
			pstmt.setDouble(2, num*price);
			pstmt.setString(3, name);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				price_pstmt.close();
				price_rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//System.out.println(name+","+num);
	}
	public void deleteShopGoods(String name) 
	{
		String sql="DELETE FROM shopbus WHERE name=?";
		try {
			pstmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
