package com.training.dao.college.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.yangkai.dao.ConnectionCallback;
import com.yangkai.dao.MyStrategyTemplate;
import com.yangkai.dao.MyUserDao;
import com.yangkai.dao.RowMapper;
import com.yangkai.dao.vo.User;

public class MyUserDaoJdbcImpl extends ResultMapper<User> implements MyUserDao{
	/*
	 * �Ƿ��̰߳�ȫ��
	 * */
	public MyStrategyTemplate srategyTemplate=new MyStrategyTemplateJdbcImpl();
	/*
	 * ��getResult()�����Ĺ�����ȡ��ResultMapperTemplate���д���
	 **/
//	public  List<User> getResult(ResultSet rs, List<User> users) {
//		try {
//			if(rs.isAfterLast()!=rs.isBeforeFirst()){
//				users=new ArrayList<User>();
//				while(rs.next()){
//					User user=getUser(rs);	
//					users.add(user);
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return users;
//	}
//	
//	public User getResult(ResultSet rs) {
//		User user=null;
//		try {
//			if(rs.next()){
//				user=getUser(rs);	
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return user;
//	}
//	
//	private User getUser(ResultSet rs) {
//		User user=new User();
//		try {
//			user.setId(rs.getLong("id"));
//			user.setName(rs.getString("name"));
//			user.setSex(rs.getString("sex"));
//			user.setBirthday((Date)rs.getObject("birthday"));
//			user.setAddr(rs.getString("addr"));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return user;
//	}
//	
	/*
	 * ResultMapper������ĳ��󷽷�getObject()�ľ���ʵ�֡�
	 * */
	@Override
	protected User getObject(ResultSet rs) {
		User user=new User();
		try {
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setSex(rs.getString("sex"));
			user.setBirthday((Date)rs.getObject("birthday"));
			user.setAddr(rs.getString("addr"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User findUser(int id) {
		String sql="SELECT id,name,sex,birthday,addr FROM user WHERE id=?";
		Object[] args=new Object[]{id};
		return this.srategyTemplate.query(sql,args,new RowMapper<User>() {

			@Override
			public User rowMapper(ResultSet rs) {
              User user=getResult(rs);
			  return user;
			}
		});
	}

	@Override
	public List<User> findUser(String name) {
		String sql="SELECT id,name,sex,birthday,addr FROM user WHERE name LIKE ? ";
		//����д����
		//Object[] args=new Object[]{"'%"+name+"%'"};
		Object[] args=new Object[]{"%"+name+"%"};
		return this.srategyTemplate.query(sql, args, new RowMapper<List<User>>() {

			@Override
			public List<User> rowMapper(ResultSet rs) {
				List<User> users=null;
				users=getResult(rs,users);
				return users;
			}
		});
	}

	@Override
	public int addUser(final User user) {
		final Integer generatedIdentifier=null;
		return this.srategyTemplate.save(new ConnectionCallback<Integer>() {

			@Override
			public Integer doInConnection(Connection conn) {
				
				int generatedKey=0;
				String sql="INSERT INTO user(name,sex,birthday,addr) VALUES (?,?,?,?)";
				try {
					PreparedStatement ps=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, user.getName());
					ps.setString(2, user.getSex());
					ps.setObject(3, user.getBirthday());
					ps.setString(4, user.getAddr());
					int count=ps.executeUpdate();
					if(count>0){
						ResultSet rs=ps.getGeneratedKeys();
						if(rs.next()){
							generatedKey=rs.getInt(1);
							System.out.println(generatedIdentifier);
							return generatedKey;
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return generatedKey;
			}
		});
	}

	@Override
	public int addUsers(List<User> users) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeUser(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
