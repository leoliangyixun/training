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

import com.yangkai.myblog.constants.Constants;
import com.yangkai.myblog.domain.Message;
import com.yangkai.myblog.domain.MessageReply;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncodingTool;

public class AddMessageReply extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doPost(request, response);
	}
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session=request.getSession(true);
		String currpage=request.getParameter("page");
		String currstep=request.getParameter("step");
		int message_link_mark=Integer.parseInt(request.getParameter("message_link_mark"));
		int message_id=Integer.parseInt(request.getParameter("message_id"));
		String username=(String) session.getAttribute("loginuser");
		String guest=null;
		String message_reply_content=EncodingTool.setCharacterEncoding(request.getParameter("message_reply_content"));
		String message_reply_date=request.getParameter("message_reply_date");
		MessageReply messagereply=new MessageReply();
		messagereply.setMessageid(message_id);
		messagereply.setUsername(username);
		if(message_link_mark==Constants.ADD_MESSAGE_REPLY_FROM_MY_LEAVE_MESSAGE)
		{
			List<Message> my_leave_message=(List<Message>) session.getAttribute("my_leave_message");
			for(int i=0;i<my_leave_message.size();i++)
			{
				if(my_leave_message.get(i).getMessageid()==message_id)
				{
					guest=my_leave_message.get(i).getUsername();//Ajax无法传递字符参数，故只能采用这种低效率的方式。
				}
			}
		}
		if(message_link_mark==Constants.ADD_MESSAGE_REPLY_FROM_USER_MESSAGE)
		{
			List<Message> user_message=(List<Message>) session.getAttribute("user_message");
			for(int i=0;i<user_message.size();i++)
			{
				if(user_message.get(i).getMessageid()==message_id)
				{
					guest=user_message.get(i).getGuest();//Ajax无法传递字符参数，故只能采用这种低效率的方式。
				}
			}
		}
		messagereply.setGuest(guest);
		messagereply.setMessagereplycontent(message_reply_content);
		try {
			messagereply.setMessagereplydate(DateFormat.getDateTimeInstance().parse(message_reply_date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		System.out.println("AddMessageReply:"+service);
		int count=service.addMessageReply(messagereply);
		if(count>0)
		{
			//List<MessageReply> user_message_reply=(List<MessageReply>) session.getAttribute("user_message_reply");
			//user_message_reply.add(messagereply);
			//List<MessageReply> user_message_reply=service.getMessageReply(username);
			//session.setAttribute("user_message_reply", user_message_reply);
			List<MessageReply> all_message_reply=service.getAllMessageReply(username);
			session.setAttribute("all_message_reply", all_message_reply);
			if(message_link_mark==Constants.ADD_MESSAGE_REPLY_FROM_MY_LEAVE_MESSAGE)
			{
				response.sendRedirect("my_leave_message.jsp?page="+currpage+"&step="+currstep+"");
			}
			if(message_link_mark==Constants.ADD_MESSAGE_REPLY_FROM_USER_MESSAGE)
			{
				response.sendRedirect("user_message.jsp?page="+currpage+"&step="+currstep+"");
			}
		}else{
			response.sendRedirect("error.jsp");
		}
	}

}
