package com.yangkai.myblog.domain;

import java.util.Date;

public class UserResponse {
	private Integer response_id;
	private Integer request_id;
	private String username;
	private String guest;
	private String response_content;
	private Date response_date;
	public UserResponse(){
		
	}
	public Integer getResponseid() {
		return response_id;
	}
	public void setResponseid(Integer response_id) {
		this.response_id = response_id;
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
	public String getResponsecontent() {
		return response_content;
	}
	public void setResponsecontent(String response_content) {
		this.response_content = response_content;
	}
	public Date getResponsedate() {
		return response_date;
	}
	public void setResponsedate(Date response_date) {
		this.response_date = response_date;
	}
}
