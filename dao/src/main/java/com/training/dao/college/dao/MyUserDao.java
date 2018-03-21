package com.training.dao.college.dao;

import java.util.List;

import com.yangkai.dao.vo.User;

public interface MyUserDao {
	public User findUser(int id);
	public List<User> findUser(String name);
	public int addUser(User user);
	public int addUsers(List<User> users);
	public int removeUser(int id);
}
