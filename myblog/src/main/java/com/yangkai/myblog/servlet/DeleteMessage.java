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
			/*******************************************更新数据库*******************************************/
			int count=service.deleteMessage(message_id);
			if(count>0)
			{	
				/*******************************************更新集合*******************************************/
				List<Message> user_message=(List<Message>) session.getAttribute("user_message");
				//List<MessageReply> all_message_reply=(List<MessageReply>) session.getAttribute("all_message_reply");
				List<Message> all_friends_message=null;
				if(message_mark==Constants.DELETE_MESSAGE_FROM_USER_MESSAGE_FROM_FRIENDS_DYNAMIC)
				{
					all_friends_message=(List<Message>) session.getAttribute("all_friends_message");
				}
				for(int i=0;i<user_message.size();i++)
				{
					if(user_message.get(i).getMessageid()==message_id)
					{
						if(all_friends_message!=null && all_friends_message.contains(user_message.get(i)))
						{
							all_friends_message.remove(user_message.get(i));
						}
						user_message.remove(i);
						/**
						 * 删除留言的回复。没有必要？？？
						 * */
						/*
						for(int j=0;j<all_message_reply.size();j++)
						{
							if(all_message_reply.get(j).getMessageid()==message_id)
							{
								all_message_reply.remove(j);
							}
						}
						*/
						break;
					}
				}
				/*
				if(message_mark!=null && Integer.parseInt(message_mark)==Constants.DELETE_MESSAGE_FROM_AJAX)
				{
					RequestDispatcher rd=request.getRequestDispatcher("/message_list_flush.jsp");
					//rd.forward(request, response);
					rd.include(request, response);
					System.out.println("转发成功");
					String fuck=(String) request.getAttribute("word");
					System.out.println("fuck:"+fuck);
				}
				*/
				System.out.println("删除留言成功！！！");
				if(message_mark==Constants.DELETE_MESSAGE_FROM_USER_MESSAGE)
				{
					response.sendRedirect("user_message.jsp?page="+currpage+"&step="+currstep+"");
					return;
				}
				if(message_mark==Constants.DELETE_MESSAGE_FROM_MESSAGE_LIST)
				{
					response.sendRedirect("message_list.jsp?page="+currpage+"&step="+currstep+"");
					return;
				}
				if(message_mark==Constants.DELETE_MESSAGE_FROM_USER_MESSAGE_VIEW)
				{
					response.sendRedirect("message_view.jsp?message_id="+message_id);
					return;
				}
				if(message_mark==Constants.DELETE_MESSAGE_FROM_USER_MESSAGE_FROM_FRIENDS_DYNAMIC)
				{
					response.sendRedirect("friends_message.jsp?page="+currpage+"&step="+currstep+"");
					return;
				}
			}else{	
				response.sendRedirect("include/error.jsp");
				System.out.println("删除留言失败！！！");
			}	
	}

}
