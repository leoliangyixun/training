package org.yangkai.jdbc.dao2;

import java.util.List;

import org.yangkai.jdbc.domain.Account;
public interface AccountDao {
	public List<Account> findAccountList(String name);
	public Account findAccountByName(String name);
	public Account findAccountById(int id);
	public int addAccount(Account account);
	public int deleteAccount(int id);
	public int updateAccount(Account account);
}
