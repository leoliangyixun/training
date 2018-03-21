package com.yangkai.dao.spring;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yangkai.dao.factory.ConnectionFactory;

public class UserDaoImpl {
	JdbcTemplate jdbc=new JdbcTemplate(ConnectionFactory.getDataSource());
	
}
