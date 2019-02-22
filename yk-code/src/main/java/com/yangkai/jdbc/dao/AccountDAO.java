package org.yangkai.jdbc.dao;

import java.util.List;

import org.yangkai.jdbc.domain.Account;

public interface AccountDAO {
	public Account getAccount(int id);
	public Account getAccount(String name);
	public List<Account> getAll(int id);
	public boolean addAccount(Account account);
	public boolean removeAccount(int id);
	public boolean updateAccount(Account account);
}
