package com.yangkai.sql.domain;

import java.util.Date;

public class User {
	private Integer userId;
	private String username;
	private String userSex;
	private Date userBirthday;
	private String userAddress;
	public User() {	
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	@Override
	public String toString() {
		//return "id="+this.id+";name="+this.name+";sex="+this.sex+";birthday="+this.birthday+";addr"+this.addr;
		return super.toString();
	}
	public void show(String name){
		System.out.println(name);
	}
	/*
	public User show(String name,Integer id){
		User user=new User();
		user.setId(id);
		user.setName(name);
		System.out.println(user);
		return user;
	}
	*/
	
}
