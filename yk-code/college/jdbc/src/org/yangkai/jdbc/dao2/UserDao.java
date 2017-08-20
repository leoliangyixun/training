package org.yangkai.jdbc.dao2;

import java.util.List;

import org.yangkai.jdbc.domain.User;
public interface UserDao {
	public List<User> findUserList(String name);
	public User findUserByName(String name);
	public User findUserById(int id);
	public String findUserName(int id);
	public int addUser(User user);
	public int deleteUser(int id);
	public int updateUser(User user);

}
