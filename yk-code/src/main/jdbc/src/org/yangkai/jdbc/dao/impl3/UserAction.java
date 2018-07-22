package org.yangkai.jdbc.dao.impl3;

import java.sql.ResultSet;

import org.yangkai.jdbc.dao.Mapper;
import org.yangkai.jdbc.domain.User;

public class UserAction extends CommonDaoTemplate {

	public User findById(Class<User> clazz,int id)
	{
		String sql="";
		User user=super.find(clazz, sql, new Mapper<User>() {
		
			@Override
			public User objectMapper(ResultSet rs) {
				User user=new User();
				return user;
			}
		});
		return user;
	}
}
