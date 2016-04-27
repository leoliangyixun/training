package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.User;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncoderUtil;

public class AlterUserInfo extends HttpServlet {

	public void destroy() {
		super.destroy(); 
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			this.doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			HttpSession session=request.getSession(true);
			String loginuser=(String) session.getAttribute("loginuser");
			String name=request.getParameter("name").trim();
			String sex=request.getParameter("sex");
			String birthday=request.getParameter("birthday").trim();
			String address=request.getParameter("address").trim();
			String mail=request.getParameter("mail").trim();
			String interest=request.getParameter("interest").trim();
			if(name!=null && !name.equals("")){
				name=EncoderUtil.encode(name);
			}
			if(sex!=null && !sex.equals("")){
				sex=EncoderUtil.encode(sex);
			}
			if(address!=null && !address.equals("")){
				address=EncoderUtil.encode(address);
			}
			if(interest!=null && !interest.equals("")){
				interest=EncoderUtil.encode(interest);
			}

			MyBlogService service=ServiceFactory.getMyBlogService(request);
			User user=new User();
			user.setName(name);
			if(birthday!=null && !birthday.equals("")){
				try {
					user.setBirthday(DateFormat.getDateInstance().parse(birthday));

				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			user.setSex(sex);
			user.setAddress(address);
			user.setMail(mail);
			user.setInterest(interest);
			int count=service.alterUserInfo(user,loginuser);
			if(count>0){
				User bloger=service.getBloger(loginuser);
				session.setAttribute("bloger", bloger);
				response.sendRedirect("user_home.jsp");
				System.out.println("修改用户信息成功！！！");
			}else{
				response.sendRedirect("include/error.jsp");
				System.out.println("修改用户信息失败！！！");
			}
	}

	
	public void init() throws ServletException {
		
	}

}
