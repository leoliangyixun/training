package org.yangkai.jdbc.dao.impl3;

import java.sql.ResultSet;

import org.yangkai.jdbc.dao.Mapper;
import org.yangkai.jdbc.domain.Account;

public class AccountAction extends CommonDaoTemplate{
	public Account findById(Class<Account> clazz,int id)
	{
		String sql="";
		super.find(clazz, sql, new Mapper<Account>() {
		
			@Override
			public Account objectMapper(ResultSet rs) {
				Account account=new Account();
				return account;
			}
		});
		return null;
		
	}
}
