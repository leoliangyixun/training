package com.yangkai.sql.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yangkai.sql.domain.User;

public class UserDaoImpl extends AbstractDaoImpl {
	public User findUserById(int id) {
		String sql = "SELECT *  FROM user WHERE u_id=?";
		Object[] args = new Object[] { id };
		List<User>users=super.query(sql, args);
		if(users!=null && users.size()>0){
			return users.get(0);
		}else{
			return null;
		}
	}

	public List<User> findUserByName(String username) {
		String sql = "SELECT * FROM user WHERE u_name like ?";
		Object[] args=new Object[]{username};
		return super.query(sql, args);
	}

	public int updateUser(int id) {
		return 0;
	}

	public int addUser(User user) {
		return 0;
	}

	public int removeUser(int id) {
		return 0;
	}

	/*
	 * 结果映射的方法写死了就会使程序很不灵活。
	 */

	public List<User> mappingObjects(ResultSet rs) {
		List<User> users = null;
		try {
			while (rs.next()) {
				if (users == null) {
					users = new ArrayList<User>();
				}
				User user = new User();
				user.setUserId(rs.getInt("u_id"));
				user.setUsername(rs.getString("u_name"));
				user.setUserSex(rs.getString("u_sex"));
				user.setUserBirthday((Date) rs.getObject("u_birth"));
				user.setUserAddress(rs.getString("u_addr"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
}
