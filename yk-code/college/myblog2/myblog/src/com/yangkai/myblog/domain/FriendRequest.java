package com.yangkai.myblog.domain;

public class FriendRequest {
	private Integer request_id;
	private String username;
	private String guest;
	private String request_content;
	private String request_date;
	private Integer request_state;
	public FriendRequest(){
		
	}
	public Integer getRequestid() {
		return request_id;
	}
	public void setRequestid(Integer request_id) {
		this.request_id = request_id;
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
	public String getRequestcontent() {
		return request_content;
	}
	public void setRequestcontent(String request_content) {
		this.request_content = request_content;
	}
	public String getRequestdate() {
		return request_date;
	}
	public void setRequestdate(String request_date) {
		this.request_date = request_date;
	}
	public Integer getRequeststate() {
		return request_state;
	}
	public void setRequeststate(Integer request_state) {
		this.request_state = request_state;
	}
	
}
