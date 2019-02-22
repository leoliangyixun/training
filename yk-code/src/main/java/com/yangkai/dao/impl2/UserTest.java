package com.yangkai.dao.impl2;

import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.yangkai.dao.factory.ConnectionFactory;
import com.yangkai.dao.vo.User;

public class UserTest {
	NamedParameterJdbcTemplate named=new NamedParameterJdbcTemplate(ConnectionFactory.getDataSource());
	public List<User> findUser(User user){
		String sql="SELECT * FROM user WHERE id<:id";
		List<User> users=this.named.query(sql, new BeanPropertySqlParameterSource(user), new BeanPropertyRowMapper<User>(User.class));
		return users;
	}
	public static void main(String[] args){
		UserTest t=new UserTest();
		User user=new User();
		user.setId(3);
		List<User> u=t.findUser(user);
		System.out.println(u);
	}
}
