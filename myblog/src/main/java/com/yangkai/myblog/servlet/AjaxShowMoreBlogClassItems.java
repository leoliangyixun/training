package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AjaxShowMoreBlogClassItems extends HttpServlet {

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);

	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			HttpSession session=request.getSession(true);
			List<String> user_blog_class=(ArrayList<String>)session.getAttribute("user_blog_class");
			//out.println("<form action='SearchBlogByClass' method='get' name='moreBlogClassItems'>");
			out.println("<select name='link_blog_class' onchange='document.moreBlogClassItems.submit()'>");
			for(int i=0;i<user_blog_class.size();i++)
			{
				out.println("<option value="+user_blog_class.get(i)+">"+user_blog_class.get(i)+"</option>");
			}
			out.println("</select>");
			//out.println("<form>");
			out.flush();
			out.close();
	}

	public void init() throws ServletException {
		
	}

}
