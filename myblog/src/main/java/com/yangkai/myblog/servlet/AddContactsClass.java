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

import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncoderUtil;

public class AddContactsClass extends HttpServlet {

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
			String loginuser=(String) session.getAttribute("loginuser");
			String contacts_class=request.getParameter("contacts_class");
			if(contacts_class!=null && !contacts_class.equals("")){
				contacts_class=EncoderUtil.encode(contacts_class);
			}
			MyBlogService service=ServiceFactory.getMyBlogService(request);
			int count=service.addContactsClass(loginuser,contacts_class);
			if(count>0){
				List<String> user_contacts_class=(List<String>) session.getAttribute("user_contacts_class");
				if(user_contacts_class==null){
					user_contacts_class=new ArrayList<String>();
					user_contacts_class.add(contacts_class);
					session.setAttribute("user_contacts_class", user_contacts_class);
				}else{
					user_contacts_class.add(contacts_class);
				}
				response.sendRedirect("user_contacts.jsp");
				System.out.println("添加通讯录类别成功！！！");
			}else{
				response.sendRedirect("include/error.jsp");
				System.out.println("添加通讯录类别失败！！！");
			}
	}

	
	public void init() throws ServletException {
		
	}

}
