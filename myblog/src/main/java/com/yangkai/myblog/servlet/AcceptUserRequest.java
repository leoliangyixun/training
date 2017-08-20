package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.UserRequest;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class AcceptUserRequest extends HttpServlet {

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

			HttpSession session=request.getSession(true);
			String currpage=request.getParameter("page");
			String currstep=request.getParameter("step");
			int request_id=Integer.parseInt(request.getParameter("request_id"));
			String friend_name=URLDecoder.decode(request.getParameter("friend_name"), "UTF-8");
			String loginuser=(String) session.getAttribute("loginuser");
			MyBlogService service=ServiceFactory.getMyBlogService(request);
			int count=service.alterRequestState(request_id);
			if(count>0){
				List<UserRequest> user_request=(List<UserRequest>) session.getAttribute("user_request");
				for(int i=0;i<user_request.size();i++){
					if(user_request.get(i).getRequestid()==request_id){
						user_request.remove(i);
						break;
					}
				}
				int num=service.addFriend(friend_name,loginuser);
				if(num>0){
					request.getRequestDispatcher("LoadUserFriends").include(request, response);
					response.sendRedirect("user_request.jsp?page="+currpage+"&step="+currstep);
					System.out.println("处理好友请求成功！！！");
				}else{
					response.sendRedirect("include/error.jsp");
					System.out.println("处理好友请求失败！！！");
				}
			}else{
				response.sendRedirect("include/error.jsp");
				System.out.println("处理好友请求失败！！！");
			}
	}

	
	public void init() throws ServletException {
		
	}

}
