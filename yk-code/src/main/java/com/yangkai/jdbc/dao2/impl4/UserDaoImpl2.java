package org.yangkai.jdbc.dao2.impl4;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.yangkai.jdbc.dao2.ObjectMapper;
import org.yangkai.jdbc.dao2.UserDao;
import org.yangkai.jdbc.domain.User;

public class UserDaoImpl2 extends CommonAbstractDaoImpl2 implements UserDao{
	
	@Override
	public User findUserById(int id) 
	{
		//String sql="select * from user where id=?";
		String sql="select name from user where id=?";
		//String sql="select name,sex from user where id=?";
		//String sql="select name,sex,birthday from user where id=?";
		Object[] args=new Object[]{id};
		Object obj=super.commonFind(sql, args,new ObjectMapper() //匿名内部类
		{
			@Override 
			public User commonObjectMapper(ResultSet rs)
			{
				User user=new User();
				try{
				user.setName(rs.getString("name"));
				user.setSex(rs.getString("sex"));
				user.setBirthday((Date)rs.getObject("birthday"));
				user.setAddr(rs.getString("addr"));
				}catch (Exception e) {
					e.printStackTrace();
				}
				return user;
			}
		});

		return (User) obj;
	}
	@Override
	public User findUserByName(String name) 
	{
		//String sql="select * from user where name=?";
		String sql="select name from user where id=?";
		//String sql="select name,sex from user where name=?";
		//String sql="select name,sex,birthday from user where name=?";
		Object[] args=new Object[]{name};
		Object obj=super.commonFind(sql, args,new ObjectMapper() {
	
			@Override
			public User commonObjectMapper(ResultSet rs)
			{
				User user=new User();
				try{
				user.setName(rs.getString("name"));
				//user.setSex(rs.getString("sex"));
				//user.setBirthday((Date)rs.getObject("birthday"));
				//user.setAddr(rs.getString("addr"));
				}catch (Exception e) {
					e.printStackTrace();
				}
				return user;
			}
		});

		return (User) obj;
	}
	@Override
	public List<User> findUserList(String name)
	{
		String sql="select * from user where name=?";
		Object[] object=new Object[]{name};
		List<User> users=null;
		List<Object> objs=super.commonListFind(sql, object,new ObjectMapper() {
			
			@Override
			public User commonObjectMapper(ResultSet rs)
			{
				return null;
			}
		});
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
	public String findUserName(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
