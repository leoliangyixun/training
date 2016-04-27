package com.yangkai.myblog.domain;

import java.util.Date;

public class MessageReply {
	private Integer message_reply_id=null;
	private Integer message_id=null;
	private String username=null;
	private String guest=null;
	private String message_reply_content=null;
	private Date message_reply_date=null;
	public MessageReply() {
		
	}
	public Integer getMessagereplyid() {
		return message_reply_id;
	}
	public void setMessagereplyid(Integer message_reply_id) {
		this.message_reply_id = message_reply_id;
	}
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
	public String getMessagereplycontent() {
		return message_reply_content;
	}
	public void setMessagereplycontent(String message_reply_content) {
		this.message_reply_content = message_reply_content;
	}
	public Date getMessagereplydate() {
		return message_reply_date;
	}
	public void setMessagereplydate(Date message_reply_date) {
		this.message_reply_date = message_reply_date;
	}

}
