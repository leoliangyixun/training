package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class AjaxCheckUsername extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		//不能使用编码工具类，因为此时的username是采用Ajax发送过来的，而Ajax使用UTF-8编码，因此要用UTF-8解码。
		//String username=CharactorEncoder.setCharacterEncoding(request.getParameter("username"));
		String username=URLDecoder.decode(request.getParameter("username"), "UTF-8");
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		
		boolean Isuse=service.checkUsername(username);
		if(Isuse==true)
		{
			out.println("<a style='color:red;font-size:14px;margin-left:10px'>*该用户名已经被注册</a>");
		}else{
			out.println("<a style='color:red;font-size:14px;margin-left:10px'>*该用户名可以使用</a>");
		}
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
