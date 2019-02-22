package com.yangkai.dao.impl2;

import java.util.List;

import com.yangkai.dao.vo.User;

public class UserDaoImpl {
	JdbcTemplate jdbcTemplate=new JdbcTemplate();
	public User findUser(int id){
		String sql="SELECT * FROM user WHERE id=?";
		Object[] args=new Object[]{id};
		return this.jdbcTemplate.query(sql,args,new BeanPropertyRowMapper(User.class));
	}
	public List<User> findUser(String name){
		String sql="SELECT * FROM user WHERE name=?";
		Object[] args=new Object[]{name};
		/*
		List<User> users=this.jdbc.query(sql,args,new JdbcBeanTemplate(User.class));
		return users;
		*/
		return this.jdbcTemplate.query(sql,args,new BeanPropertyRowMapper(User.class));
	}

}
