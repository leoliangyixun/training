package com.yangkai.vo;

import java.util.Date;

public class User {
	private Integer id;
	private String name;
	private char sex;
	private Date birthday;
	private String addr;
	public User() {
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Override
	public String toString() {
		//return "id="+this.id+";name="+this.name+";sex="+this.sex+";birthday="+this.birthday+";addr"+this.addr;
		return super.toString();
	}
	/*
	public void show(String name){
		System.out.println(name);
	}
	*/
	public void show(String name,int id){
		User user=new User();
		user.setId(id);
		user.setName(name);
		System.out.println(user.getId()+";"+user.getName());
	}
	
	
}
