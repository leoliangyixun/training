package com.yangkai.myblog.servlet;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.Message;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncodingTool;

public class AddMessage extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession(true);
		String guest=null;
		String username=EncodingTool.setCharacterEncoding(request.getParameter("username"));
		String message_content=EncodingTool.setCharacterEncoding(request.getParameter("message_content"));
		String message_date=request.getParameter("message_date");	
		String loginuser=(String) session.getAttribute("loginuser");
		if(loginuser==null)
		{
			guest=EncodingTool.setCharacterEncoding(request.getParameter("guest"));	
		}else{
			guest=loginuser;
		}
		Message msg=new Message();
		msg.setUsername(username);
		msg.setGuest(guest);
		msg.setMessagecontent(message_content);
		try {
			msg.setMessagedate(DateFormat.getDateTimeInstance().parse(message_date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		System.out.println("AddMessage:"+service);
		int count=service.addMessage(msg);
		if(loginuser!=null && count>0)
		{
			
			/*
			 * 这种方式效率低。
			 */
			//List<Message> my_leave_message=service.getMyLeaveMessage(loginuser);
			//session.setAttribute("my_leave_message", my_leave_message);
			/*
			 * 应该使用这种方式。
			 */
			List<Message> my_leave_message=(List<Message>) session.getAttribute("my_leave_message");
			my_leave_message.add(msg);
		}
		if(count>0)
		{
			response.sendRedirect("include/message_add_success.jsp");
		}else{
			response.sendRedirect("error.jsp");
		}	
	}
}
