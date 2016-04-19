package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class AjaxCheckBlogClass extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session= request.getSession();
		boolean exsist=false;
		String blog_class=request.getParameter("blog_class");
		String loginuser=(String)session.getAttribute("loginuser");
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		if(blog_class!=null)
		{
			blog_class=URLDecoder.decode(blog_class, "UTF-8");
			exsist=service.checkBlogClass(loginuser,blog_class);
		}
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		if(exsist==true)
		{
			out.println("<a style='color:red' id='ajax_exsist_blog_class'>*该文章类别已经存在!</a>");
		}else{
			out.println("<a style='color:red' id='ajax_exsist_blog_class'>&nbsp;</a>");
		}
		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
