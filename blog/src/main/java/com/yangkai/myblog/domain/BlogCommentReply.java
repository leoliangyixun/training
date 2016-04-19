 package com.yangkai.myblog.domain;

import java.util.Date;

public class BlogCommentReply {
	private Integer blog_comment_reply_id=null;
	private Integer blog_comment_id=null;
	private String blog_comment_reply_content=null;
	private Date blog_comment_reply_date=null;
	public Integer getBlogcommentid() {
		return blog_comment_id;
	}
	public void setBlogcommentid(Integer blog_comment_id) {
		this.blog_comment_id = blog_comment_id;
	}

	public Integer getBlogcommentreplyid() {
		return blog_comment_reply_id;
	}
	public void setBlogcommentreplyid(Integer blog_comment_reply_id) {
		this.blog_comment_reply_id = blog_comment_reply_id;
	}
	public String getBlogcommentreplycontent() {
		return blog_comment_reply_content;
	}
	public void setBlogcommentreplycontent(String blog_comment_reply_content) {
		this.blog_comment_reply_content = blog_comment_reply_content;
	}
	public Date getBlogcommentreplydate() {
		return blog_comment_reply_date;
	}
	public void setBlogcommentreplydate(Date blog_comment_reply_date) {
		this.blog_comment_reply_date = blog_comment_reply_date;
	}
	

}
