package com.yangkai.myblog.domain;

import java.util.Date;

public class MoodCommentReply {
	private Integer mood_comment_reply_id=null;
	private Integer mood_comment_id=null;
	private String username=null;
	private String mood_comment_reply_content=null;
	private Date mood_comment_reply_date=null;
	public MoodCommentReply() {
	
	}
	public Integer getMoodcommentreplyid() {
		return mood_comment_reply_id;
	}
	public void setMoodcommentreplyid(Integer mood_comment_reply_id) {
		this.mood_comment_reply_id = mood_comment_reply_id;
	}
	public Integer getMoodcommentid() {
		return mood_comment_id;
	}
	public void setMoodcommentid(Integer mood_comment_id) {
		this.mood_comment_id = mood_comment_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMoodcommentreplycontent() {
		return mood_comment_reply_content;
	}
	public void setMoodcommentreplycontent(String mood_comment_reply_content) {
		this.mood_comment_reply_content = mood_comment_reply_content;
	}
	public Date getMoodcommentreplydate() {
		return mood_comment_reply_date;
	}
	public void setMoodcommentreplydate(Date mood_comment_reply_date) {
		this.mood_comment_reply_date = mood_comment_reply_date;
	}

}
