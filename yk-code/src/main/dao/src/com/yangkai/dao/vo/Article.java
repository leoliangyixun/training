package com.yangkai.dao.vo;
import java.util.Date;

public class Article {
	private Long art_id;
	private String username;
	private String art_title;
	private String art_content;
	private Date art_date;
	public Article(){
		
	}
	public Long getArtid() {
		return art_id;
	}
	public void setArtid(Long art_id) {
		this.art_id = art_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getArttitle() {
		return art_title;
	}
	public void setArttitle(String art_title) {
		this.art_title = art_title;
	}
	public String getArtcontent() {
		return art_content;
	}
	public void setArtcontent(String art_content) {
		this.art_content = art_content;
	}
	public Date getArtdate() {
		return art_date;
	}
	public void setArtdate(Date art_date) {
		this.art_date = art_date;
	}
	
}
