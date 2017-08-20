package org.yangkai.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestURIServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		//String req=request.getRemoteAddr();/*    127.0.0.1     */
		//String req=request.getContextPath();/*     /test    */
		//String req=request.getRemoteUser();/*    null    */
		//String req=request.getRemoteHost();/*     127.0.0.1   */
		//String req=request.getServletPath();/*    /RequestURIServlet    */
		//String req=request.getRequestURI();/*     /test/RequestURIServlet     */
		//StringBuffer req=request.getRequestURL();/*    http://localhost:8080/test/RequestURIServlet     */
		//String req=request.getServletContext().getRealPath("//fileupload");/*   G:\test\WebRoot\fileupload     */
		//out.println(req);
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
