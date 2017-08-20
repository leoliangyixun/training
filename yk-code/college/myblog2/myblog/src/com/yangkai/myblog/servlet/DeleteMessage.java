package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.constants.Constants;
import com.yangkai.myblog.domain.Message;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class DeleteMessage extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		this.doPost(request, response);	

	}


	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		HttpSession session=request.getSession(true);
		int message_mark=Integer.parseInt(request.getParameter("message_mark"));
		String currpage=request.getParameter("page");
		String currstep=request.getParameter("step");
		int message_id=Integer.parseInt(request.getParameter("message_id"));
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		int count=service.deleteMessage(message_id);
		if(count>0)
		{	
			
			List<Message> user_message=(List<Message>) session.getAttribute("user_message");
			for(int i=0;i<user_message.size();i++)
			{
				if(user_message.get(i).getMessageid()==message_id)
				{
					user_message.remove(i);
					break;
				}
			}
			/*
			if(message_mark!=null && Integer.parseInt(message_mark)==Constants.DELETE_MESSAGE_FROM_AJAX)
			{
				RequestDispatcher rd=request.getRequestDispatcher("/message_list_flush.jsp");
				//rd.forward(request, response);
				rd.include(request, response);
				System.out.println("×ª·¢³É¹¦");
				String fuck=(String) request.getAttribute("word");
				System.out.println("fuck:"+fuck);
			}
			*/
			if(message_mark==Constants.DELETE_MESSAGE_FROM_USER_MESSAGE)
			{
				response.sendRedirect("user_message.jsp?page="+currpage+"&step="+currstep+"");
			}
			if(message_mark==Constants.DELETE_MESSAGE_FROM_MESSAGE_LIST)
			{
				response.sendRedirect("message_list.jsp?page="+currpage+"&step="+currstep+"");
			}
			System.out.println("É¾³ıÁôÑÔ³É¹¦");
		}else{	
			response.sendRedirect("include/error.jsp");
			System.out.println("É¾³ıÁôÑÔÊ§°Ü");
		}	
	}

}
