package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.Contacts;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncoderUtil;

public class AddContacts extends HttpServlet {

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
			String name=request.getParameter("name");
			String contacts_class=request.getParameter("contacts_class");
			String telephone=request.getParameter("telephone");
			String qq=request.getParameter("qq");
			String mail=request.getParameter("mail");

			if(name!=null && !name.equals("")){
				name=EncoderUtil.encode(name);
			}
			if(contacts_class!=null && !contacts_class.equals("")){
				contacts_class=EncoderUtil.encode(contacts_class);
			}
		
			Contacts contacts=new Contacts();
			contacts.setUsername(loginuser);
			contacts.setName(name);
			contacts.setContactsclass(contacts_class);
			contacts.setTelephone(telephone);
			contacts.setQq(qq);
			contacts.setMail(mail);
			MyBlogService service=ServiceFactory.getMyBlogService(request);
			int count=service.addContacts(contacts);
			if(count>0){
				List<Contacts> user_contacts=service.getContacts(loginuser);
				session.setAttribute("user_contacts", user_contacts);
				response.sendRedirect("user_contacts.jsp");
				System.out.println("添加联系人成功！！！");
			}else{
				response.sendRedirect("include/error.jsp");
				System.out.println("添加联系人失败！！！");
			}		
	}


	public void init() throws ServletException {
		
	}

}
