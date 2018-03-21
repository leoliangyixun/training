package com.yangkai.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class CheckServlet extends HttpServlet 
{@Override
	public void init() throws ServletException 
	{
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
		{
		String n=request.getParameter("num");
		int num=Integer.parseInt(n);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("  <BODY>");
		
	
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	    }
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		
	}

}
