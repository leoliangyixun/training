package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import com.yangkai.myblog.constants.Constants;
import com.yangkai.myblog.domain.User;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class FriendCheck extends HttpServlet {

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
		List<String> friends=(List<String>)session.getAttribute("friends");
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		
		String username=request.getParameter("username");
		username=URLDecoder.decode(username, "UTF-8");
  		if(friends!=null && friends.contains(username))
  		{
  			response.sendRedirect("friends_add.jsp?username="+URLEncoder.encode(username, "UTF-8")+"&isFriend=true");
  			//response.sendRedirect("friend_add.jsp?username="+username+"&isFriend=true");
  			//RequestDispatcher rd=request.getRequestDispatcher("friend_add.jsp?username="+URLEncoder.encode(username, "UTF-8")+"&isFriend=true");//´íÎó¡£
  		    //RequestDispatcher rd=request.getRequestDispatcher("friend_add.jsp?username="+username+"&isFriend=true");//´íÎó¡£
  			//RequestDispatcher rd=request.getRequestDispatcher("friend_add.jsp?isFriend=true");//ÕýÈ·¡£
  			//rd.forward(request, response);
  		}else{
  			User toFriend=service.getUser(username);
  			//request.setAttribute("user", user);
  			session.setAttribute("toFriend", toFriend);
  			response.sendRedirect("friends_add.jsp?username="+URLEncoder.encode(username, "UTF-8")+"&isFriend=false");
  			//response.sendRedirect("friend_add.jsp?username="+username+"&isFriend=false");
  			//RequestDispatcher rd=request.getRequestDispatcher("friend_add.jsp?username="+URLEncoder.encode(username, "UTF-8")+"&isFriend=false");//´íÎó¡£
  			//RequestDispatcher rd=request.getRequestDispatcher("friend_add.jsp?username="+username+"&isFriend=false");//´íÎó¡£
  			//RequestDispatcher rd=request.getRequestDispatcher("friend_add.jsp?isFriend=false");//ÕýÈ·¡£
  			//rd.forward(request, response);
  		}
	}

	public void init() throws ServletException {
		
	}

}
