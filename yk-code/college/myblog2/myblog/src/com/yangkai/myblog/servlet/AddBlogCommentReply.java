package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yangkai.myblog.domain.BlogCommentReply;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncodingTool;

public class AddBlogCommentReply extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		int blog_id=Integer.parseInt(request.getParameter("blog_id"));
		int blog_comment_id=Integer.parseInt(request.getParameter("blog_comment_id"));
		int mark=Integer.parseInt(request.getParameter("mark"));
		String blog_comment_reply_content=EncodingTool.setCharacterEncoding(request.getParameter("blog_comment_reply_content"));
		String blog_comment_reply_date=request.getParameter("blog_comment_reply_date");
		BlogCommentReply blogcommentreply=new BlogCommentReply();
		blogcommentreply.setBlogcommentid(blog_comment_id);
		blogcommentreply.setBlogcommentreplycontent(blog_comment_reply_content);
		try {
			blogcommentreply.setBlogcommentreplydate(DateFormat.getDateTimeInstance().parse(blog_comment_reply_date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		System.out.println("AddBlogCommentReply:"+service);
		/*************************将回复更新到数据库*************************/
		service.addBlogCommentReply(blogcommentreply);
		response.sendRedirect("ViewBlog?blog_id="+blog_id+"&mark="+mark+"");
		//测试代码。
		/* 
		String blog_id=request.getParameter("blog_id");
		String blog_comment_id=request.getParameter("blog_comment_id");
		String mark=request.getParameter("mark");
		String blog_comment_reply_content=EncodingTool.setCharacterEncoding(request.getParameter("blog_comment_reply_content"));
		String blog_comment_reply_date=request.getParameter("blogcommentreply_date");
		System.out.println("blog_id:"+blog_id);
		System.out.println("blog_comment_id:"+blog_comment_id);
		System.out.println("mark:"+mark);
		System.out.println("blog_comment_reply_content:"+blog_comment_reply_content);
		System.out.println("blog_comment_reply_date:"+blog_comment_reply_date);
        */
	}
}
