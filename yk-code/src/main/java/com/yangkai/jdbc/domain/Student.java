package org.yangkai.jdbc.domain;

public class Student {
	private String id;
	private String name;
	private char sex;
	private String major;
	private String dep;
	public String getId() {
		return id;
	}
	public void setId(String num) {
		this.id = num;
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
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	
}
