package com.yangkai.test;
public class BlogCommentListBean 
{
	private Integer blog_id=null;
	private Integer blogcomment_id=null;
	private String guest_username=null;
	private String blogcomment_content=null;
	private String blogcomment_date=null;
	public Integer getBlogId() {
		return blog_id;
	}
	public void setBlogId(Integer blog_id) {
		this.blog_id = blog_id;
	}
	public Integer getBlogcommentId() 
	{
		return blogcomment_id;
	}
	public void setBlogcommentId(int blogcomment_id) 
	{
		this.blogcomment_id = blogcomment_id;
	}
	public String getGuestusername() 
	{
		return guest_username;
	}
	public void setGuestusername(String guest_username) 
	{
		this.guest_username = guest_username;
	}
	public String getBlogcommentcontent() 
	{
		return blogcomment_content;
	}
	public void setBlogcommentcontent(String blogcomment_content) 
	{
		this.blogcomment_content = blogcomment_content;
	}
	public String getBlogcommentdate() 
	{
		return blogcomment_date;
	}
	public void setBlogcommentdate(String blogcomment_date) 
	{
		this.blogcomment_date = blogcomment_date;
	}
}
