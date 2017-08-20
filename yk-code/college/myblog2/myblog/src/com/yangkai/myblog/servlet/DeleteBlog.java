package com.yangkai.myblog.servlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.constants.Constants;
import com.yangkai.myblog.domain.Blog;
import com.yangkai.myblog.domain.User;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class DeleteBlog extends HttpServlet {

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		int blog_id=Integer.parseInt(request.getParameter("blog_id"));
		int mark=Integer.parseInt(request.getParameter("mark"));
		String blog_mark=request.getParameter("blog_mark");
		String loginuser=(String)session.getAttribute("loginuser");
		String currpage=request.getParameter("page");
		String currstep=request.getParameter("step");
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		System.out.println("DeleteBlog:"+service);
		
		/************************************删除数据库里的记录************************************/
		int count=service.deleteBlog(blog_id);
		if(count>0)
		{
			
			int num=service.alterBlogNum(loginuser, Constants.BLOG_CUT_NUM);
			if(num>0)
			{
				User bloger=(User)session.getAttribute("bloger");
				bloger.setBlognum(bloger.getBlognum()-1);
				session.setAttribute("bloger", bloger);
			}
			/************************************删除user_blog集合里的记录**************************************/
			
			if(mark==Constants.BLOG_FOR_USER || mark==Constants.BLOG_FOR_LATEST)
			{
				List<Blog> user_blog=(List<Blog>) session.getAttribute("user_blog");
				for(int i=0;i<user_blog.size();i++)
				{
					Blog blog=user_blog.get(i);
					if(blog.getBlogid()==blog_id)
					{
						user_blog.remove(blog);
						break;
					}
				}
				/************************************删除latest_blog集合里的记录**************************************/		
				List<Blog> latest_blog=(List<Blog>) session.getAttribute("latest_blog");
				for(int i=0;i<latest_blog.size();i++)
				{
					Blog blog=latest_blog.get(i);
					if(blog.getBlogid()==blog_id)
					{
						latest_blog.remove(blog);
						break;
					}
				}
				if(blog_mark!=null && Integer.parseInt(blog_mark)==Constants.DELETE_BLOG_FROM_BLOG_LIST)
				{
					response.sendRedirect("blog_list.jsp?page="+currpage+"&step="+currstep+"");
				}else{
					response.sendRedirect("user_blog.jsp");
				}
			}
			/************************************删除draft_blog集合里的记录**************************************/		
			if(mark==Constants.BLOG_FOR_DRAFT)
			{
				List<Blog> draft_blog=(List<Blog>) session.getAttribute("draft_blog");
				for(int i=0;i<draft_blog.size();i++)
				{
					Blog blog=draft_blog.get(i);
					if(blog.getBlogid()==blog_id)
					{
						draft_blog.remove(blog);
						break;
					}
				}
				response.sendRedirect("blog_draft.jsp");
			}
			/************************************删除private_blog集合里的记录**************************************/		
			if(mark==Constants.BLOG_FOR_PRIVATE)
			{
				List<Blog> private_blog=(List<Blog>) session.getAttribute("private_blog");
				for(int i=0;i<private_blog.size();i++)
				{
					Blog blog=private_blog.get(i);
					if(blog.getBlogid()==blog_id)
					{
						private_blog.remove(blog);
						break;
					}
				}
				response.sendRedirect("blog_private.jsp");
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
