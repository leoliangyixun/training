package org.yangkai.jdbc.service;

import org.yangkai.jdbc.domain.Account;
import org.yangkai.jdbc.domain.User;

public interface Service {
	
	public <T> T findUserById(Class<T> clazz,int id);
	public <T> T findUserByName(Class<T> clazz,String name);
	public <T> T findAccountById(Class<T> clazz,int id);
	public <T> T findAccountByName(Class<T> clazz,String name);
	public boolean saveUser(User user);
	public boolean saveAccount(Account account);
}
