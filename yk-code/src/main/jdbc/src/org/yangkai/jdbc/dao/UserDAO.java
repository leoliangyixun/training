package org.yangkai.jdbc.dao;

import java.util.List;

import org.yangkai.jdbc.domain.User;

public interface UserDAO {
	public User getUser(int id);
	public User getUser(String name);
	public List<User> getAll(int id);
	public boolean addUser(User user);
	public boolean removeUser(int id);
	public boolean updateUser(User user);
}
