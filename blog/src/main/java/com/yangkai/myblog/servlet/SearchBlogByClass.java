package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.Blog;

public class SearchBlogByClass extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}


	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		   HttpSession session=request.getSession(true);
		   String link_blog_class=request.getParameter("link_blog_class");
		   link_blog_class=URLDecoder.decode(link_blog_class, "UTF-8");
		   List<Blog> user_blog=(List<Blog>) session.getAttribute("user_blog");
		   int size=0;
		   if(user_blog!=null && user_blog.size()>0)
		   {
			   for(int i=0;i<user_blog.size();i++)
			   {
				   if(user_blog.get(i).getBlogclass().equals(link_blog_class))
				   {
					   size++;
				   }else{
					   continue;
				   }
			   }
			   response.sendRedirect("user_blog.jsp?link_blog_class="
					   				+URLEncoder.encode(link_blog_class,"UTF-8")
					   				+"&size="+size+"");  
		   }else{
			   response.sendRedirect("user_blog.jsp");  
			   
		   }
	}
	
}
