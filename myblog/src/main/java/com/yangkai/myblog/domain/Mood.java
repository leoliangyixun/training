package com.yangkai.myblog.domain;

import java.util.Date;

public class Mood {
	private Integer mood_id=null;
	private String username=null;
	private String mood_content=null;
	private Date mood_date=null;
	public Mood(){
		
	}
	public int getMoodid() {
		return mood_id;
	}
	public void setMoodid(Integer mood_id) {
		this.mood_id = mood_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMoodcontent() {
		return mood_content;
	}
	public void setMoodcontent(String mood_content) {
		this.mood_content = mood_content;
	}
	public Date getMooddate() {
		return mood_date;
	}
	public void setMooddate(Date mood_date) {
		this.mood_date = mood_date;
	}
	
}
