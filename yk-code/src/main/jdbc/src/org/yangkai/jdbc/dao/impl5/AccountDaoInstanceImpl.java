package org.yangkai.jdbc.dao.impl5;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.yangkai.jdbc.dao2.AccountDao;
import org.yangkai.jdbc.domain.Account;

public class AccountDaoInstanceImpl implements AccountDao{
	
	CommonInstanceTemplate template=new CommonInstanceTemplate();
	@Override
	public List<Account> findAccountList(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccountByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findAccountById(int id) {
		String sql="SELECT * FROM account WHERE id=?";
		Object[] args={id};
		Account account=this.template.find(sql, args,new ObjectMapper() {
			@SuppressWarnings("unchecked")
			@Override
			public Account commonObjectMapper(ResultSet rs) { 
				Account account=new Account();
				try {
					account.setId(rs.getLong("id"));
					account.setName(rs.getString("name"));
					account.setBalance(rs.getFloat("balance"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return account;
			}
		});
		return account;
	}

	@Override
	public int addAccount(Account account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAccount(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAccount(Account account) {
		// TODO Auto-generated method stub
		return 0;
	}

}
