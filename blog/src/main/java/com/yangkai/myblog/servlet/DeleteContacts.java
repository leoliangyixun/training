package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.Contacts;
import com.yangkai.myblog.domain.Mood;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class DeleteContacts extends HttpServlet {

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
			String currpage=request.getParameter("page");
			String currstep=request.getParameter("step");
			int contacts_id=Integer.parseInt(request.getParameter("contacts_id"));
			MyBlogService service=ServiceFactory.getMyBlogService(request);
			int count=service.deleteContacts(contacts_id);
			if(count>0){
				System.out.println("删除联系人成功！！！");
				List<Contacts> user_contacts=(List<Contacts>) session.getAttribute("user_contacts");
				for(int i=0;i<user_contacts.size();i++){
					if(user_contacts.get(i).getContactsid()==contacts_id){
						user_contacts.remove(i);
						break;
					}
				}
				response.sendRedirect("user_contacts.jsp?page="+currpage+"&step="+currstep);
			}else{
				System.out.println("删除联系人失败！！！");
			}
	}

	
	public void init() throws ServletException {
		
	}

}
