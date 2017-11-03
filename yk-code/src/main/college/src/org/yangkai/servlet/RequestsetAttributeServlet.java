package org.yangkai.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RequestsetAttributeServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id=16;
		HttpSession session=request.getSession(true);
		ServletContext application=this.getServletContext();
		//session.setAttribute("id",id);
		request.setAttribute("id", id);
		RequestDispatcher rd=request.getRequestDispatcher("test/requestsetAttribute.jsp");
		/*����forward()������Ҫ�ǽ�������������֮������󴫵ݸ�������ҳ�档������������װ���µ�������*/
		rd.forward(request, response);
		//response.sendRedirect("");
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
