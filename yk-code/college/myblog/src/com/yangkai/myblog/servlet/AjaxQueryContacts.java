package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.Contacts;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class AjaxQueryContacts extends HttpServlet {

	public void destroy() {
		super.destroy(); 
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session= request.getSession();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE></TITLE></HEAD>");
		out.println("  <BODY>");
		String name=request.getParameter("name");
		String loginuser=(String)session.getAttribute("loginuser");
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		List<Contacts> contacts_query_result=null;
		if(name!=null && !name.equals("")){
			name=URLDecoder.decode(name, "UTF-8");
			contacts_query_result=service.queryContacts("%"+name+"%",loginuser);
		}	
		request.setAttribute("contacts_query_result",contacts_query_result );
		/*
		 * ÇëÇó×ª·¢¡£
		 * */
		RequestDispatcher rd=request.getRequestDispatcher("/tools/contacts_query_result.jsp");
		rd.forward(request, response);
		
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			this.doGet(request, response);
	}

	public void init() throws ServletException {
		
	}

}
