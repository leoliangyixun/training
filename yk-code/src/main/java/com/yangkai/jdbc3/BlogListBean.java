package com.yangkai.jdbc;
public class BlogListBean
{
	private Integer blog_id=null;
	private String username=null;
	private String blog_subject=null;
	private String blog_content=null;
	private String blog_class=null;
	private String blog_date=null;
	public void setBlogId(int blog_id)
	{
		this.blog_id=blog_id;
	}
	public int getBlogId()
	{
		return blog_id;
	}
	public void setUsername(String username)
	{
		this.username=username;
	}
	public String getUsername()
	{
		return username;
	}
	public void setBlogsubject(String blog_subject)
	{
		this.blog_subject=blog_subject;
	}
	public String getBlogsubject()
	{
		return blog_subject;
	}
	public void setBlogcontent(String blog_content)
	{
		this.blog_content=blog_content;
	}
	public String getBlogcontent()
	{
		return blog_content;
	}
	public void setBlogclass(String blog_class)
	{
		this.blog_class=blog_class;
	}
	public String getBlogclass()
	{
		return blog_class;
	}
	public void setBlogdate(String blog_date)
	{
		this.blog_date=blog_date;
	}
	public String getBlogdate()
	{
		return blog_date;
	}
}
