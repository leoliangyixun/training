package org.yangkai.jdbc.dao2.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.yangkai.jdbc.dao2.Callback;
import org.yangkai.jdbc.domain.Account;

public class AccountInAction extends JdbcConnectionTemplate {

	public AccountInAction() {
		
	}
	
	public Account getAccount(final int id){
		final String sql="SELECT * FROM account WHERE id=?";
		return super.executeCallback(new Callback<Account>() {

			@Override
			public Account doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) {
				System.out.println("doInCallback(Connection conn, PreparedStatement ps,ResultSet rs)");
				Account account=null;
				try {
					ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ps.setInt(1, id);
					rs=ps.executeQuery();
					if(rs.next()){
						account=new Account();
						account.setId(rs.getLong("id"));
						account.setName(rs.getString("name"));
						account.setBalance(rs.getFloat("balance"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return account;
			}
		});
	}
	
	public Account getAccount(final String name){
		final String sql="SELECT * FROM account WHERE name=?";
		return super.executeCallback(new Callback<Account>() {

			@Override
			public Account doInCallback(Connection conn, PreparedStatement ps,ResultSet rs) {
				System.out.println("doInCallback(Connection conn, PreparedStatement ps,ResultSet rs)");
				Account account=null;
				try {
					ps=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ps.setString(1, name);
					rs=ps.executeQuery();
					if(rs.next()){
						account=new Account();
						account.setId(rs.getLong("id"));
						account.setName(rs.getString("name"));
						account.setBalance(rs.getFloat("balance"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return account;
			}
		});
	}
	
	public List<Account> getAll(int id){
		String sql="SELECT * FROM account WHERE id=?";
		return super.executeCallback(new Callback<List<Account>>() {

			@Override
			public List<Account> doInCallback(Connection conn, PreparedStatement ps,
					ResultSet rs) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}

}
