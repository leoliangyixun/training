package com.training.dao.college.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.yangkai.dao.MyAccountDao;
import com.yangkai.dao.MyStrategyTemplate;
import com.yangkai.dao.RowMapper;
import com.yangkai.dao.vo.Account;

public class MyAccountDaoJdbcImpl extends ResultMapper<Account> implements MyAccountDao{
	public MyStrategyTemplate srategyTemplate=new MyStrategyTemplateJdbcImpl();
//	@Override
//	public  List<Account> getResult(ResultSet rs, List<Account> accounts) {
//		try {
//			if(rs.isAfterLast()!=rs.isBeforeFirst()){
//				accounts=new ArrayList<Account>();
//				while(rs.next()){
//					Account account=getAccount(rs);	
//					accounts.add(account);
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return accounts;
//	}
//	@Override
//	public Account getResult(ResultSet rs) {
//		Account account=null;
//		try {
//			if(rs.next()){
//				account=getAccount(rs);	
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return account;
//	}
//	private Account getAccount(ResultSet rs)  {
//		Account account=new Account();
//		try {
//			account.setId(rs.getLong("id"));
//			account.setName(rs.getString("name"));
//			account.setBalance(rs.getFloat("balance"));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return account;
//	}
	protected Account getObject(ResultSet rs) {
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

	@Override
	public Account findAccount(int id) {
		String sql="SELECT id,name,balance FROM account WHERE id=?";
		Object[] args=new Object[]{id};
		return this.srategyTemplate.query(sql,args,new RowMapper<Account>() {

			@Override
			public Account rowMapper(ResultSet rs) {
				Account account=getResult(rs);
			  return account;
			}
		});
	}

	@Override
	public List<Account> findAccount(String name) {
		String sql="SELECT id,name,balance FROM account WHERE name LIKE ? ";
		Object[] args=new Object[]{"%"+name+"%"};
		return this.srategyTemplate.query(sql, args, new RowMapper<List<Account>>() {

			@Override
			public List<Account> rowMapper(ResultSet rs) {
				List<Account> accounts=null;
				accounts=getResult(rs,accounts);
				return accounts;
			}
		});
	}
}
