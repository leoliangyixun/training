package com.training.dao.college.dao.impl2;

public class JdbcTemplate{
	
	public <T> T query(String sql,Object[] args,BeanPropertyRowMapper rowMapper){
		return rowMapper.query(sql,args);
	}
}
