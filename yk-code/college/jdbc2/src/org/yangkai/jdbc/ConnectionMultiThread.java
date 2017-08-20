package org.yangkai.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.yangkai.jdbc.tools.ConnectionTool;

public class ConnectionMultiThread {

	public ConnectionMultiThread() {
		
	}

	
	public static void main(String[] args) 
	{
		Conn conn=new Conn();
		//conn.getUsername(1);
		/*
		for(int i=1;i<200;i++)
		{
			new Thread(new ConnUser1(conn,1)).start();
		}
		*/
		/*
		for(int i=1;i<100;i++)
		{
			new ConnUser2("name"+i,conn,new Random().nextInt(4)).start();
		}
		*/
		/*
		Thread u1=new Thread(new ConnUser1(conn,1));
		Thread u2=new Thread(new ConnUser2(conn,2));
		Thread u3=new Thread(new ConnUser1(conn,1));
		Thread u4=new Thread(new ConnUser2(conn,2));
		u1.start();
		u2.start();
		u3.start();
		u4.start();
		*/
		ConnUser1 cu=new ConnUser1(conn,2);
		for(int i=1;i<100;i++)
		{
			new Thread(cu).start();
		}
		//new Thread(cu).start();
		//new Thread(cu).start();
		//new Thread(cu).start();
	}

}
class Conn{
	//private Connection conn=null;
	//private PreparedStatement pstmt=null;
	//private ResultSet rs=null;
	public void getUsername(int id) 
	{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String str="";
		conn = ConnectionTool.getConnection();
		String sql = "SELECT * FROM user WHERE id=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) 
			{
				str=Thread.currentThread().getName()+"<--->id:"+rs.getInt("id")
					+"<--->name:"+rs.getString("name")
					+"<--->birthday:"+rs.getDate("birthday")
					+"<--->money:"+rs.getFloat("money");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionTool.freeConnection(rs, pstmt, conn);
		System.out.println(str);
	}
	
}
class ConnUser1 implements Runnable {
	private Conn conn;
	private int id;
	public ConnUser1(Conn conn,int id)
	{
		this.conn=conn;
		this.id=id;
	}
	public void run() 
	{
		conn.getUsername(id);
	}
}
class ConnUser2 extends Thread {
	private Conn conn;
	private int id;
	public ConnUser2(String name,Conn conn,int id)
	{
		super(name);
		this.conn=conn;
		this.id=id;
	}
	public void run() 
	{
		conn.getUsername(id);
	}
}
	
	
