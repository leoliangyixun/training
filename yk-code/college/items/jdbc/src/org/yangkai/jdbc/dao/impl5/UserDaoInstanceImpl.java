package org.yangkai.jdbc.dao.impl5;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.yangkai.jdbc.dao2.UserDao;
import org.yangkai.jdbc.domain.User;

public class UserDaoInstanceImpl  implements UserDao{
	
	CommonInstanceTemplate template=new CommonInstanceTemplate();
	@Override
	public List<User> findUserList(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserById(int id) {
		String sql="SELECT * FROM user WHERE id=?";
		Object[] args={id};
		User user=this.template.find(sql, args,new ObjectMapper() {
			@SuppressWarnings("unchecked")
			@Override
			public User commonObjectMapper(ResultSet rs) { 
				User user=new User();
				try {
					user.setId(rs.getLong("id"));
					user.setName(rs.getString("name"));
					user.setSex(rs.getString("sex"));
					user.setBirthday((Date)rs.getObject("birthday"));
					user.setAddr(rs.getString("addr"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return user;
			}
		});
		return user;
	}

	@Override
	public String findUserName(int id) {
		String sql="SELECT name FROM user WHERE id=?";
		Object[] args={id};
		String name=this.template.find(sql, args,new ObjectMapper() {
			@SuppressWarnings("unchecked")
			@Override
			public String commonObjectMapper(ResultSet rs) { 
				String name=null;
				try {
					name=rs.getString("name");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return name;
			}
		});
		return name;
	}
	
	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUser(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}


}
