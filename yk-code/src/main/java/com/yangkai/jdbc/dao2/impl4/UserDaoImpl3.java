package org.yangkai.jdbc.dao2.impl4;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.yangkai.jdbc.dao2.Mapper;
import org.yangkai.jdbc.dao2.UserDao;
import org.yangkai.jdbc.domain.User;

public class UserDaoImpl3 implements UserDao{
	
	CommonDaoImpl3 comm=new CommonDaoImpl3();
	@Override
	public User findUserById(int id) 
	{
		//String sql="select * from user where id=?";
		//String sql="select name from user where id=?";
		//String sql="select name,sex from user where id=?";
		String sql="select name,sex,birthday,addr from user where id=?";
		Object[] args=new Object[]{id};
		Object obj=comm.find(sql, args,new Mapper() 
		{
			@Override 
			public User commonResultSetMapper(ResultSet rs) 
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
				return null;
			}
		});
		/*
		Object obj=comm.commonFind(sql, args,new UserMapper() 
		{
			@Override 
			public User userResultSetMapping(ResultSet rs) 
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
        */
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
		Object obj=comm.find(sql, args,new Mapper() {
	
			@SuppressWarnings("unchecked")
			@Override
			public User commonResultSetMapper(ResultSet rs) 
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
		List<Object> objs=comm.findAll(sql, object,new Mapper() {
			
			@Override
			public User commonResultSetMapper(ResultSet rs) 
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
		count=comm.action(sql,object);
		return count;
	}
	@Override
	public int deleteUser(int id)
	{
		int count=0;
		String sql="delete from user where name=?";
		Object[] object=new Object[]{id};
		count=comm.action(sql, object);
		return count;
	}
	@Override
	public int updateUser(User user)
	{
		int count=0;
		String sql="update user set name=?,sex=?,birthday=?addr=? where id=?";
		Object[] object=new Object[]{user.getId(),user.getName(),user.getSex(),user.getBirthday(),user.getAddr()};
		count=comm.action(sql,object);
		return count;
	}
	@Override
	public String findUserName(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
