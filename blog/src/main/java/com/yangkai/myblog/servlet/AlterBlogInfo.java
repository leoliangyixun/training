package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.User;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncoderUtil;

public class AlterBlogInfo extends HttpServlet {

	public void destroy() {
		super.destroy(); 
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			HttpSession session=request.getSession(true);
			String loginuser=(String)session.getAttribute("loginuser");
			String blog_name=request.getParameter("blog_name");
			String blog_logo=request.getParameter("blog_logo");
			if(blog_name!=null && !blog_name.equals("")){
				blog_name=EncoderUtil.encode(blog_name);
			}
			if(blog_logo!=null && !blog_logo.equals("")){
				blog_logo=EncoderUtil.encode(blog_logo);
			}
			MyBlogService service=ServiceFactory.getMyBlogService(request);
			User user=new User();
			user.setUsername(loginuser);
			user.setBlogname(blog_name);
			user.setBloglogo(blog_logo);
			int count=service.alterBlogInfo(user);
			if(count>0){
				User bloger=service.getBloger(loginuser);
				session.setAttribute("bloger",bloger);
				response.sendRedirect("user_home.jsp");
				System.out.println("修改博客信息成功！！！");
			}else{
				response.sendRedirect("include/error.jsp");
				System.out.println("修改博客信息失败！！！");
			}
	}

	
	public void init() throws ServletException {
		
	}

}
