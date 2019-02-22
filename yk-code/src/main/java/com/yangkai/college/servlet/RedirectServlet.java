package org.yangkai.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectServlet extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your hujiang here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username=request.getParameter("username");
		if(username.equals("lavender"))//ǧ��Ҫ��username=="lavender",��Ϊ����ͬ����ʹֵ��ȣ�username=="lavender"Ϊfalse
		{
			username="yangkai";
		}
		request.setAttribute("username", username);
		RequestDispatcher rs=request.getRequestDispatcher("test/redirect.jsp");
		rs.forward(request, response);
		
		//response.sendRedirect("test/redirect.jsp");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your hujiang here
	}

}
