package com.training.dao.college.dao.spring;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.yangkai.dao.factory.ConnectionFactory;
import com.yangkai.dao.vo.User;

public class JdbcSpringTemplate {
	JdbcTemplate jdbc=new JdbcTemplate(ConnectionFactory.getDataSource()); 
	public void test(String sql,Object[] args){
		jdbc.queryForObject(sql,args,new BeanPropertyRowMapper<User>());
	}

}
