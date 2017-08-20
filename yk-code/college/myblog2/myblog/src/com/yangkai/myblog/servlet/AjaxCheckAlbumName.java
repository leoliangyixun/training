package com.yangkai.myblog.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
public class AjaxCheckAlbumName extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		HttpSession session= request.getSession();
		boolean exsist=false;
		String album_name=request.getParameter("album_name");
		String loginuser=(String)session.getAttribute("loginuser");
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		System.out.println("AjaxChectAlbumName:"+service);
		if(album_name!=null)
		{
			album_name=URLDecoder.decode(album_name, "UTF-8");
			exsist=service.checkAlbumName(loginuser,album_name);
		}
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		if(exsist==true)
		{
			out.println("<a style='color:red'>*该相册已经存在!</a>");
		}else{
			out.println("<a style='color:red'>*该相册名可以使用。</a>");
		}
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			this.doPost(request,response);
	}

}
