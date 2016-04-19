package com.yangkai.myblog.domain;

public class Contacts { 
	private Integer contacts_id;
	private String username;
	private String name;
	private String contacts_class;
	private String telephone;
	private String qq;
	private String mail;
	public Contacts() {
		
	}
	public Integer getContactsid() {
		return contacts_id;
	}
	public void setContactsid(Integer contacts_id) {
		this.contacts_id = contacts_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactsclass() {
		return contacts_class;
	}
	public void setContactsclass(String contacts_class) {
		this.contacts_class = contacts_class;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

}
