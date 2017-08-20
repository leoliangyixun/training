package org.yangkai.jdbc.dao2.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.yangkai.jdbc.dao2.RowMapper;
import org.yangkai.jdbc.domain.User;

public class UserAction extends JdbcAbstractTemplate {

	public UserAction() {
		
	}
	
	public User find(int id){
		String sql="SELECT * FROM user WHERE id=?";
		Object[] args=new Object[]{id};
		return super.find(User.class,sql,args,new RowMapper<User>() {
			
			@Override
			public User doInCallback(ResultSet rs) {
				User user=null;
				try {
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
	
	public User find(String name){
		return null;
		
	}
	
	public List<User> findAll(int id){
		String sql="SELECT * FROM user WHERE id>?";
		Object[] args=new Object[]{id};
		return super.findAll(User.class,sql,args,new RowMapper<List<User>>() {
			
			@Override
			public List<User> doInCallback(ResultSet rs) {
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
