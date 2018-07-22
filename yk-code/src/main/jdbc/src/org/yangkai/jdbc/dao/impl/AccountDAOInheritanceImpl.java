package org.yangkai.jdbc.dao.impl;

import java.util.List;

import org.yangkai.jdbc.dao.AccountDAO;
import org.yangkai.jdbc.domain.Account;

public class AccountDAOInheritanceImpl extends AbstractBasicDAOTemplate implements AccountDAO{

	@Override
	public Account getAccount(int id) {
		/*
		 *Class<Account> clazz=Account.class;
		 *String sql="SELECT * FROM account WHERE id="+id;
		 *Account account=super.find(clazz, sql);
		 * µÈ¼ÛÓÚ
		 * String sql="SELECT * FROM account WHERE id="+id;
		 * Account account=super.find(Account.class, sql);
		 * */
		//Class<Account> clazz=Account.class;
		String sql="SELECT * FROM account WHERE id="+id;
		Account account=super.find(Account.class, sql);
		return account;
	}

	@Override
	public Account getAccount(String name) {
		String sql="SELECT * FROM account WHERE name='"+name+"'";
		Account account=super.find(Account.class, sql);
		return account;
	}

	@Override
	public List<Account> getAll(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addAccount(Account account) {
		// TODO Auto-generated method stub
		return super.save(account);
	}

	@Override
	public boolean removeAccount(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAccount(Account account) {
		// TODO Auto-generated method stub
		return false;
	}

}
