package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.UserRequest;
import com.yangkai.myblog.domain.UserResponse;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncoderUtil;

public class AddUserResponse extends HttpServlet {

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
			String username=(String) session.getAttribute("loginuser");
			List<UserRequest> user_request=(List<UserRequest>) session.getAttribute("user_request");
			String currpage=request.getParameter("page");
			String currstep=request.getParameter("step");
			int request_id=Integer.parseInt(request.getParameter("request_id"));
			String response_content=request.getParameter("response_content");
			if(response_content==null || response_content.equals("")){
				response_content="<a style='color:#F00'>"+username+"</a>拒绝了你的好友请求。";
			}else{
				response_content=EncoderUtil.encode(response_content);
			}
			String response_date=request.getParameter("response_date");
			UserResponse userresponse=new UserResponse();
			userresponse.setRequestid(request_id);
			userresponse.setUsername(username);
			for(int i=0;i<user_request.size();i++){
				if(user_request.get(i).getRequestid()==request_id){
					userresponse.setGuest(user_request.get(i).getGuest());
					break;
				}
			}
			userresponse.setResponsecontent(response_content);
			try {
				userresponse.setResponsedate(DateFormat.getDateTimeInstance().parse(response_date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			MyBlogService service=ServiceFactory.getMyBlogService(request);
			int count=service.addUserRequestResponse(userresponse);
			if(count>0){
				service.alterRequestState(request_id);
				for(int i=0;i<user_request.size();i++){
					if(user_request.get(i).getRequestid()==request_id){
						user_request.remove(i);
						break;
					}
				}
				response.sendRedirect("user_request.jsp?page="+currpage+"&step="+currstep);
				System.out.println("处理好友请求成功！！！");
			}else{
				response.sendRedirect("include/error.jsp");
				System.out.println("处理好友请求失败！！！");
			}

	}


	public void init() throws ServletException {
		
	}

}
