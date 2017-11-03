package org.yangkai.jdbc.dao2.impl4;

import java.sql.ResultSet;
import java.util.List;

import org.yangkai.jdbc.dao2.AccountDao;
import org.yangkai.jdbc.domain.Account;

public class AccountDaoImpl extends CommonAbstractDaoImpl implements AccountDao{
	public List<Account> findAccountList(String name)
	{
		String sql="select * from account where name=?";
		Object[] object=new Object[]{name};
		List<Object> accounts=super.commonFind(sql, object);
		return null;
	}
	public int addAccount(Account account)
	{
		int count=0;
		String sql="insert into user(name,sex,birthday,addr) values(?,?,?,?)";
		return count;
	}
	public int deleteAccount(int id)
	{
		int count=0;
		String sql="delete from user where id=?";
		return count;
	}
	public int updateAccount(Account account)
	{
		int count=0;
		String sql="update user set name=?,sex=?,birthday=?addr=? where id=?";
		return count;
	}
	@Override
	public Object objectMapper(ResultSet rs) 
	{
		Account account=new Account();
		try{
		account.setId(rs.getLong("id"));
		account.setName(rs.getString("name"));
		account.setBalance(rs.getFloat("balance"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}
	@Override
	public Account findAccountByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Account findAccountById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
