package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.constants.Constants;
import com.yangkai.myblog.domain.Blog;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class AlterBlogState extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session=request.getSession();
		String loginuser=(String)session.getAttribute("loginuser");
		/**
		 * 要检验是否为空。
		 */
		List<Blog> user_blog=(List<Blog>) session.getAttribute("user_blog");
		List<Blog> draft_blog=(List<Blog>) session.getAttribute("draft_blog");
		List<Blog> private_blog=(List<Blog>) session.getAttribute("private_blog");
		int blog_id=Integer.parseInt(request.getParameter("blog_id"));
		int type=Integer.parseInt(request.getParameter("type"));
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		int count=0;
		if(type==Constants.BLOG_FOR_USER)
		{
			count=service.alterBlogState(Constants.BLOG_STATE_FOR_PRIVATE,blog_id);
			if(count>0)
			{
				/*
				 * 这种方式效率很低。
				 * List<Blog> draft_blog=service.getDraftBlog(loginuser);
				 * session.setAttribute("draft_blog", draft_blog);
				 */
				for(int i=0;i<user_blog.size();i++)
				{
					if(user_blog.get(i).getBlogid()==blog_id)
					{
						Blog blog=user_blog.get(i);
						//user_blog.remove(blog);
						user_blog.remove(i);
						if(private_blog==null){
							private_blog=new ArrayList<Blog>();
							private_blog.add(0, blog);
							session.setAttribute("private_blog", private_blog);
						}else{
							private_blog.add(0, blog);
						}
					}
				}
				response.sendRedirect("blog_private.jsp");
			}else{
				response.sendRedirect("include/error.jsp");
			}
			return;
		}
		if(type==Constants.BLOG_FOR_PRIVATE)
		{
			count=service.alterBlogState(Constants.BLOG_STATE_FOR_RELEASE,blog_id);
			if(count>0)
			{
				for(int i=0;i<private_blog.size();i++)
				{
					if(private_blog.get(i).getBlogid()==blog_id)
					{
						Blog blog=private_blog.get(i);
						private_blog.remove(i);
						if(user_blog==null){
							user_blog=new ArrayList<Blog>();
							user_blog.add(0, blog);
							session.setAttribute("user_blog",user_blog);
						}else{
							user_blog.add(0, blog);
						}
					}
				}
				response.sendRedirect("user_blog.jsp");
			}else{
				response.sendRedirect("include/error.jsp");
			}
			return;
		}
		if(type==Constants.BLOG_FOR_DRAFT)
		{
			count=service.alterBlogState(Constants.BLOG_STATE_FOR_RELEASE,blog_id);
			if(count>0)
			{
				for(int i=0;i<draft_blog.size();i++)
				{
					if(draft_blog.get(i).getBlogid()==blog_id)
					{
						Blog blog=draft_blog.get(i);
						draft_blog.remove(i);
						if(user_blog==null){
							user_blog=new ArrayList<Blog>();
							user_blog.add(0, blog);
							session.setAttribute("user_blog",user_blog);
						}else{
							user_blog.add(0, blog);
						}
					}
				}
				response.sendRedirect("user_blog.jsp");
			}else{
				response.sendRedirect("include/error.jsp");
			}
			return;
		}
	}
}
