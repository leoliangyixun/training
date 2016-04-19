package com.yangkai.myblog.domain;

import java.util.Date;

public class MoodComment {
	private Integer mood_comment_id=null;
	private Integer mood_id=null;
	private String username=null;
	private String guest=null;
	private String mood_comment_content=null;
	private Date mood_comment_date=null;
	public MoodComment(){
		
	}
	public Integer getMoodcommentid() {
		return mood_comment_id;
	}
	public void setMoodcommentid(Integer mood_comment_id) {
		this.mood_comment_id = mood_comment_id;
	}
	public Integer getMoodid() {
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
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}
	public String getMoodcommentcontent() {
		return mood_comment_content;
	}
	public void setMoodcommentcontent(String mood_comment_content) {
		this.mood_comment_content = mood_comment_content;
	}
	public Date getMoodcommentdate() {
		return mood_comment_date;
	}
	public void setMoodcommentdate(Date mood_comment_date) {
		this.mood_comment_date = mood_comment_date;
	}

}
