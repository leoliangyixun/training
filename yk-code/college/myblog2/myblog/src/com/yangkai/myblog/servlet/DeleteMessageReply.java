package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.constants.Constants;
import com.yangkai.myblog.domain.MessageReply;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class DeleteMessageReply extends HttpServlet {


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
		int message_reply_id=Integer.parseInt(request.getParameter("messsage_reply_id"));
		int message_link_mark=Integer.parseInt(request.getParameter("message_link_mark"));
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		System.out.println("DeleteMessageReply:"+service);
		int count=service.deleteMessageReply(message_reply_id);
		if(count>0)
		{	
			List<MessageReply> all_message_reply=(List<MessageReply>) session.getAttribute("all_message_reply");
			for(int i=0;i<all_message_reply.size();i++)
			{
				if(all_message_reply.get(i).getMessagereplyid()==message_reply_id)
				{
					all_message_reply.remove(i);
					break;
				}
			}
			if(message_link_mark==Constants.DELETE_MESSAGE_REPLY_FROM_MY_LEAVE_MESSAGE)
			{
				response.sendRedirect("my_leave_message.jsp?page="+currpage+"&step="+currstep+"");
			}
			if(message_link_mark==Constants.DELETE_MESSAGE_REPLY_FROM_USER_MESSAGE)
			{
				response.sendRedirect("user_message.jsp?page="+currpage+"&step="+currstep+"");
			}
		}else{
			response.sendRedirect("error.jsp");
		}
	}

}
