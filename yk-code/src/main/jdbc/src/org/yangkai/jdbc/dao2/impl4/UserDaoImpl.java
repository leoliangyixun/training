package org.yangkai.jdbc.dao2.impl4;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.yangkai.jdbc.dao2.UserDao;
import org.yangkai.jdbc.domain.User;

public class UserDaoImpl extends CommonAbstractDaoImpl implements UserDao{
	
	@Override
	public User findUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<User> findUserList(String name)
	{
		String sql="select * from user where name=?";//这样做不能避免SQL注入。
		Object[] args=new Object[]{name};
		List<User> users=null;
		List<Object> objs=super.commonFind(sql, args);
		if(objs!=null && objs.size()>0)
		{
			users=new ArrayList<User>();
			for(int i=0;i<objs.size();i++)
			{
				users.add((User) objs.get(i));
			}
		}
		return users;
	}
	@Override
	public int addUser(User user)
	{
		int count=0;
		String sql="insert into user(name,sex,birthday,addr) values(?,?,?,?)";
		Object[] object=new Object[]{user.getName(),user.getSex(),user.getBirthday(),user.getAddr()};
		count=super.commonAction(sql,object);
		return count;
	}
	@Override
	public int deleteUser(int id)
	{
		int count=0;
		String sql="delete from user where name=?";
		Object[] object=new Object[]{id};
		super.commonAction(sql, object);
		return count;
	}
	@Override
	public int updateUser(User user)
	{
		int count=0;
		String sql="update user set name=?,sex=?,birthday=?addr=? where id=?";
		Object[] object=new Object[]{user.getId(),user.getName(),user.getSex(),user.getBirthday(),user.getAddr()};
		count=super.commonAction(sql,object);
		return count;
	}
	@Override
	public Object objectMapper(ResultSet rs) 
	{
		User user=new User();
		try{
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setSex(rs.getString("sex"));
		user.setBirthday((Date)rs.getObject("birthday"));
		user.setAddr(rs.getString("addr"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String findUserName(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
