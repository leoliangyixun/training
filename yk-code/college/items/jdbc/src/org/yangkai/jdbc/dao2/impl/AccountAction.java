package org.yangkai.jdbc.dao2.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.yangkai.jdbc.dao2.RowMapper;
import org.yangkai.jdbc.domain.Account;

public class AccountAction extends JdbcAbstractTemplate {

	public AccountAction() {
		
	}
	public Account find(int id){
		String sql="SELECT * FROM account WHERE id=?";
		Object[] args=new Object[]{id};
		return super.find(Account.class,sql,args,new RowMapper<Account>() {
			
			@Override
			public Account doInCallback(ResultSet rs) {
				Account account=null;
				try {
					if(rs.next()){
						account=new Account();
						account.setId(rs.getLong("id"));
						account.setName(rs.getString("name"));
						account.setBalance(rs.getFloat("balance"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return account;
			}
		});
		
	}
	
	public Account find(String name){
		return null;
		
	}
	
	public List<Account> findAll(int id){
		return null;
		
	}

}
