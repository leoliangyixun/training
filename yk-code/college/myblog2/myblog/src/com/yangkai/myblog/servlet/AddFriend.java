package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.FriendRequest;

public class AddFriend extends HttpServlet {

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		    HttpSession session=request.getSession(true);
		    String loginuser=(String) session.getAttribute("loginuser");
			String request_content=request.getParameter("request_content");
			String request_date=request.getParameter("request_date");
			FriendRequest fr=new FriendRequest();
			//fr.
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			this.doGet(request, response);
	}

	public void init() throws ServletException {
		
	}

}
