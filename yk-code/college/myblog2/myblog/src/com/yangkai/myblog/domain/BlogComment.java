package com.yangkai.myblog.domain;

import java.util.Date;

public class BlogComment 
{
	private Integer blog_id=null;
	private Integer blog_comment_id=null;
	private String guest=null;
	private String blog_comment_content=null;
	private Date blog_comment_date=null;
	public Integer getBlogid() {
		return blog_id;
	}
	public void setBlogid(Integer blog_id) {
		this.blog_id = blog_id;
	}
	public Integer getBlogcommentid() 
	{
		return blog_comment_id;
	}
	public void setBlogcommentid(int blog_comment_id) 
	{
		this.blog_comment_id = blog_comment_id;
	}
	public String getGuest() 
	{
		return guest;
	}
	public void setGuest(String guest) 
	{
		this.guest = guest;
	}
	public String getBlogcommentcontent() 
	{
		return blog_comment_content;
	}
	public void setBlogcommentcontent(String blog_comment_content) 
	{
		this.blog_comment_content = blog_comment_content;
	}
	public Date getBlogcommentdate() 
	{
		return blog_comment_date;
	}
	public void setBlogcommentdate(Date blog_comment_date) 
	{
		this.blog_comment_date = blog_comment_date;
	}
}
