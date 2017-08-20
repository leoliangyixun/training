package org.yangkai.jdbc.dao2.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.yangkai.jdbc.dao2.Callback;
import org.yangkai.jdbc.domain.User;

public class UserInnerAction extends JdbcConnectionTemplate {

	public UserInnerAction() {
		
	}
	
	public User getUser(final int id){
		final String sql="SELECT * FROM user WHERE id=?";
		//Object[] args=new Object[]{id};
		return super.executeCallback(new Callback<User>() {

			@Override
			public User doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) {
				System.out.println("doInCallback(Connection conn, PreparedStatement ps,ResultSet rs)");
				User user=null;
				try {
					ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ps.setInt(1, id);
					rs=ps.executeQuery();
					if(rs.next()){
						user=new User();
						user.setId(rs.getLong("id"));
						user.setName(rs.getString("name"));
						user.setSex(rs.getString("sex"));
						user.setBirthday((Date)rs.getObject("birthday"));
						user.setAddr(rs.getString("addr"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return user;
			}
		});
	}
	
	public User getUser(final String name){
		final String sql="SELECT * FROM user WHERE name=?";
		return super.executeCallback(new Callback<User>() {

			@Override
			public User doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) {
				System.out.println("doInCallback(Connection conn, PreparedStatement ps,ResultSet rs)");
				User user=null;
				try {
					ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ps.setString(1, name);
					rs=ps.executeQuery();
					if(rs.next()){
						user=new User();
						user.setId(rs.getLong("id"));
						user.setName(rs.getString("name"));
						user.setSex(rs.getString("sex"));
						user.setBirthday((Date)rs.getObject("birthday"));
						user.setAddr(rs.getString("addr"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return user;
			}
		});
	}
	
	public List<User> getAll(int id){
		String sql="SELECT * FROM user WHERE id=?";
		return super.executeCallback(new Callback<List<User>>() {

			@Override
			public List<User> doInCallback(Connection conn, PreparedStatement ps,
					ResultSet rs) {
				System.out.println("doInCallback(Connection conn, PreparedStatement ps,ResultSet rs)");
				List<User> users=null;
				try {
					if(!(rs.isAfterLast()==rs.isBeforeFirst())){
						users=new ArrayList<User>();
						while(rs.next()){
							User user=new User();
							user.setId(rs.getLong("id"));
							user.setName(rs.getString("name"));
							user.setSex(rs.getString("sex"));
							user.setBirthday((Date)rs.getObject("birthday"));
							user.setAddr(rs.getString("addr"));
							users.add(user);
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return users;
			}
		});
	}
}
