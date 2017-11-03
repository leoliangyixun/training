package org.yangkai.jdbc.dao2.impl2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.yangkai.jdbc.dao2.ConnectionCallback;
import org.yangkai.jdbc.domain.User;

public class UserCallbackImpl {
	
	//public JdbcCallbackTemplate template=new JdbcCallbackTemplate();
	//protected JdbcCallbackTemplate template=new JdbcCallbackTemplate();
	private JdbcCallbackTemplate template=new JdbcCallbackTemplate();
	
	public User findUser(int id){
		String sql="SELECT * FROM user WHERE id=?";
		Object[] args=new Object[]{id};
		long start=System.currentTimeMillis();
		User user=this.template.find(User.class,sql,args);
		long end=System.currentTimeMillis();
		System.out.println("instance method cost:"+(end-start));
		return user;
	}
	
	public List<User> findAll(int id){	
		String sql="SELECT * FROM user WHERE id<?";
		Object[] args=new Object[]{id};
		long start=System.currentTimeMillis();
		List<User> users=this.template.findAll(User.class,sql,args);
		long end=System.currentTimeMillis();
		System.out.println("instance method cost:"+(end-start));
		return users;
	}
	//返回整形值。
	public  int addUser(final User user){
		return this.template.save(new ConnectionCallback<Integer>() {

			@Override
			public Integer doInConnection(Connection conn,PreparedStatement ps,ResultSet rs) {
				int count=0;
				String sql="INSERT INTO user(name,sex,birthday,addr) VALUES(?,?,?,?)";
				try {
					ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, user.getName());
					ps.setString(2, user.getSex());
					ps.setObject(3, user.getBirthday());
					ps.setString(4, user.getAddr());
					count=ps.executeUpdate();
					//获取插入记录后生成的键。
					//get key after inserting a new record.
					rs=ps.getGeneratedKeys();
					ResultSetMetaData rsmd=rs.getMetaData();
					while(rs.next()){
						for(int i=1;i<=rsmd.getColumnCount();i++){
							System.out.println(rsmd.getColumnName(i));
							//System.out.println(rsmd.getColumnLabel(i));
							System.out.println("key:"+rs.getObject(rsmd.getColumnName(i)));
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return count;
			}
		});
	}
	//返回布尔值。
	public  boolean addUser2(final User user){
		
		return this.template.save2(new ConnectionCallback<Boolean>() {

			@Override
			public Boolean doInConnection(Connection conn,PreparedStatement ps,ResultSet rs) {
				boolean addSuccess=false;
				int count=0;
				String sql="INSERT INTO user(name,sex,birthday,addr) VALUES(?,?,?,?)";
				try {
					ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, user.getName());
					ps.setString(2, user.getSex());
					ps.setObject(3, user.getBirthday());
					ps.setString(4, user.getAddr());
					count=ps.executeUpdate();
					rs=ps.getGeneratedKeys();
					ResultSetMetaData rsmd=rs.getMetaData();
					while(rs.next()){
						for(int i=1;i<=rsmd.getColumnCount();i++){
							System.out.println("key:"+rs.getObject(rsmd.getColumnName(i)));
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(count>0){
					addSuccess=true;
				}
				return addSuccess;
			}
		});
	}
}
