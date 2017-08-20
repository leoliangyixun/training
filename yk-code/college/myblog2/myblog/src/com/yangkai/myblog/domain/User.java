package com.yangkai.myblog.domain;

import java.util.Date;

public class User {
	
	private Integer id;
	private String username;
	private String password;
	private String name;
	private String sex;
	private Date birthday;
	private String address;
	private String qq;
	private String telephone;
	private String mail;
	private String interest;
	private String blog_name;
	private String blog_logo;
	private Date regist_time;
	private Integer blog_num;
	
	public User() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getBlogname() {
		return blog_name;
	}
	public void setBlogname(String blog_name) {
		this.blog_name = blog_name;
	}
	public String getBloglogo() {
		return blog_logo;
	}
	public void setBloglogo(String blog_logo) {
		this.blog_logo = blog_logo;
	}
	public Date getRegisttime() {
		return regist_time;
	}
	public void setRegisttime(Date regist_time) {
		this.regist_time = regist_time;
	}
	public Integer getBlognum() {
		return blog_num;
	}

	public void setBlognum(Integer blog_num) {
		this.blog_num = blog_num;
	}
}
