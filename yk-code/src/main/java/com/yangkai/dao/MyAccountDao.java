package com.yangkai.dao;

import java.util.List;

import com.yangkai.dao.vo.Account;

public interface MyAccountDao {
	public Account findAccount(int id);
	public List<Account> findAccount(String name);
}
