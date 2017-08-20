package org.yangkai.jdbc.domain;

import java.util.Date;

public class User {
	private Long id;
	private String name;
	private String sex;
	private Date birthday;
	private String addr;
	public User() {
		
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
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
		return "id:"+this.id+";name:"+this.name+";sex:"+this.sex+";birthday:"+this.birthday+";addr:"+this.addr;
		//return super.toString();
	}
}
