package com.yangkai.myblog.domain;

import java.util.Date;

public class Message {
	private Integer message_id=null;
	private String username=null;
	private String guest=null;
	private String message_content=null;
	private Date message_date=null;
	public Integer getMessageid() {
		return message_id;
	}
	public void setMessageid(Integer message_id) {
		this.message_id = message_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}
	public String getMessagecontent() {
		return message_content;
	}
	public void setMessagecontent(String message_content) {
		this.message_content = message_content;
	}
	public Date getMessagedate() {
		return message_date;
	}
	public void setMessagedate(Date message_date) {
		this.message_date = message_date;
	}
	
}
