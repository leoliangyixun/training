package com.yangkai.myblog.servlet;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.User;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncoderUtil;

public class UserRegist extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		HttpSession session=request.getSession(true);
		String username=EncoderUtil.encode(request.getParameter("username"));
		String password=request.getParameter("password");
		String mail=request.getParameter("mail");
		String sex=EncoderUtil.encode(request.getParameter("sex"));
		String address=EncoderUtil.encode(request.getParameter("address"));
		String regist_time=request.getParameter("regist_time");
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setMail(mail);
		user.setSex(sex);
		user.setAddress(address);
		try {
			user.setRegisttime(DateFormat.getDateTimeInstance().parse(regist_time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		int count=service.regist(user);
		if(count>0)
		{
			File userAlbumDir=new File(this.getServletContext().getRealPath("/")
					+"upload"+File.separator+"Ïà²á"+File.separator+username);
			if(!userAlbumDir.exists())
			{
				userAlbumDir.mkdir();
			}
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			response.sendRedirect("include/regist_success.jsp");
		}else{
			response.sendRedirect("include/error.jsp");
		}
		/*
		 * ²âÊÔ´úÂë
		 */
		/*
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		System.out.println("mail:"+mail);
		System.out.println("sex:"+sex);
		System.out.println("address:"+address);
		System.out.println("regist_date:"+regist_date);
		*/
	}

}
