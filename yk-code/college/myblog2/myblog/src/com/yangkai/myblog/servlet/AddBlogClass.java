package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.constants.Constants;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncodingTool;

public class AddBlogClass extends HttpServlet {

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session=request.getSession();
		String loginuser=(String)session.getAttribute("loginuser");
		int blog_class_mark=Integer.parseInt(request.getParameter("blog_class_mark"));
		String blog_class=null;
		if(blog_class_mark==Constants.BLOG_CLASS_FOR_AJAX)
		{
			blog_class=URLDecoder.decode(request.getParameter("blog_class"), "UTF-8");
		}
		else if(blog_class_mark==Constants.BLOG_CLASS_FOR_FORM)
		{
			blog_class=EncodingTool.setCharacterEncoding(request.getParameter("blog_class"));
		}
		MyBlogService service=ServiceFactory.getMyBlogService(request); 
		
		int count=service.addBlogClass(loginuser,blog_class);
		if(count>0)
		{
			//List<String> user_blog_class=service.getBlogClass(loginuser);
			List<String> user_blog_class=(List<String>) session.getAttribute("user_blog_class");
			user_blog_class.add(blog_class);
			//session.setAttribute("user_blog_class", user_blog_class);
			response.sendRedirect("blogclass_add_success.jsp");
		}else{
			response.sendRedirect("error.jsp");
		}
		
		//测试代码。
		/*
		//String blog_class=request.getParameter("blog_class");
		//System.out.println(blog_class);//打印出来的是UTF-8编码。
		 */
		/*
		System.out.println(loginuser);
		System.out.println(blog_class);
		*/

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
