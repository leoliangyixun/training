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
import com.yangkai.myblog.domain.UserRequest;
import com.yangkai.myblog.domain.UserResponse;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncoderUtil;

public class AddUserRequest extends HttpServlet {

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
		    String username=request.getParameter("username") ;
		    String guest=(String) session.getAttribute("loginuser");
			String request_content=EncoderUtil.encode(request.getParameter("request_content")) ;
			String request_date=request.getParameter("request_date");
			
			UserRequest userrequest=new UserRequest();
			if(username==null){
				int response_id=Integer.parseInt(request.getParameter("response_id"));
				List<UserResponse> user_response=(List<UserResponse>) session.getAttribute("user_response");
				for(int i=0;i<user_response.size();i++){
					if(user_response.get(i).getResponseid()==response_id){
						username=user_response.get(i).getUsername();
						break;
					}
				}
			}else{
				username=EncoderUtil.encode(request.getParameter("username")) ;
			}
			userrequest.setUsername(username);
			userrequest.setGuest(guest);
			userrequest.setRequestcontent(request_content);
			try {
				userrequest.setRequestdate(DateFormat.getDateTimeInstance().parse(request_date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			userrequest.setRequeststate(Constants.USER_REQUEST_UNCHECKED);
			
			MyBlogService service=ServiceFactory.getMyBlogService(request);
			int count=service.addUserRequest(userrequest);
			if(count>0){
				System.out.println("添加好友请求成功！！！");
				response.sendRedirect("include/friend_add_success.jsp");
			}else{
				response.sendRedirect("include/error.jsp");
				System.out.println("添加好友请求失败！！！");
			}
	}

	public void init() throws ServletException {
		
	}

}
