package org.yangkai.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.yangkai.jdbc.domain.User;
import org.yangkai.jdbc.factory.ConnectionFactory;

public class SqlInject {

	public SqlInject() {
		
	}
	public static void statementFind(String name){
		Connection conn=ConnectionFactory.getConnection();
		Statement stmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM user WHERE name='"+name+"'";
		System.out.println("SQL:"+sql);
		try {
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery(sql);
			while (rs.next()){
				User user=new User();
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setSex(rs.getString("sex"));
				user.setBirthday((Date)rs.getObject("birthday"));
				user.setAddr(rs.getString("addr"));
				System.out.println("id:"+user.getId()+";name:"+user.getName()+";sex:"+user.getSex()+";birthday:"+user.getBirthday()+";addr:"+user.getAddr());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void preparedStatementFind(String name){
		Connection conn=ConnectionFactory.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="SELECT * FROM user WHERE name=?";
		
		try {
			ps=conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ps.setString(1, name);
		
			rs=ps.executeQuery();
			while (rs.next()){
				User user=new User();
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setSex(rs.getString("sex"));
				user.setBirthday((Date)rs.getObject("birthday"));
				user.setAddr(rs.getString("addr"));
				System.out.println("id:"+user.getId()+";name:"+user.getName()+";sex:"+user.getSex()+";birthday:"+user.getBirthday()+";addr:"+user.getAddr());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Statement:");
		//statementFind("李四");
		//statementFind("or 1 or");
		statementFind("'or 1 or'");
		//statementFind("'or 1");
		System.out.println("PreparedStatement:");
		preparedStatementFind("李四");
		//preparedStatementFind("or 1 or");
		preparedStatementFind("'or 1 or'");
	}

}
