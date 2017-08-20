package com.yangkai.myblog.domain;

public class FriendResponse {
	private Integer response_id;
	private String username;
	private String guest;
	private String response_content;
	private String response_date;
	public FriendResponse(){
		
	}
	public Integer getResponse_id() {
		return response_id;
	}
	public void setResponse_id(Integer response_id) {
		this.response_id = response_id;
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
	public String getResponse_content() {
		return response_content;
	}
	public void setResponse_content(String response_content) {
		this.response_content = response_content;
	}
	public String getResponse_date() {
		return response_date;
	}
	public void setResponse_date(String response_date) {
		this.response_date = response_date;
	}
}
