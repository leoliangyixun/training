package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.BlogComment;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class AjaxBlogComment extends HttpServlet {


	@SuppressWarnings("unused")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		//String loginuser=(String)session.getAttribute("loginuser");
		int blog_id=Integer.parseInt(request.getParameter("blog_id"));
		//int mark=Integer.parseInt(request.getParameter("mark"));
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		System.out.println("AjaxBlogComment:"+service);
		List<BlogComment> blog_comment=null;
		//String bloger=dao.getBloger(blog_id);
		blog_comment=service.getBlogComment(blog_id);
		if(blog_comment==null)
		{
			out.println("<div class='border'><a>该博文还没有评论。</a></div>");
		}
		else{
			for(int i=0;i<blog_comment.size();i++)
			{
				BlogComment blogcomment=blog_comment.get(i);
				int blog_comment_id=blogcomment.getBlogcommentid();
				String guest=blogcomment.getGuest();
				String blog_comment_content=blogcomment.getBlogcommentcontent();
				Date blog_comment_date=blogcomment.getBlogcommentdate();
				out.println("<div class='border'>");
				/*
				out.println("<table border='0' width='570'>");
				out.println("<tr>");
				out.println("<td width='70' align='center' style='font-size:12px'>");
				out.println("<a>"+guest+"</a><br>");
				out.println("<img src='upload/error.jpg' width='60' height='60''/><br>");
				out.println("<a>"+blog_comment_date+"</a><br>");
				out.println("</td>");
				out.println("<td valign='center' align='left'>");
				out.println(blog_comment_content);
				out.println("</td>");
				out.println("</tr>");
				out.println("</table>");
				*/
				 out.println("<table border='0' width='550' bgcolor='#FFFF80' bordercolor='#000000' style='margin:10px auto;border-collapse: collapse;'>");
				 out.println("<tr>");
				 out.println("<td rowspan='2' width='70' align='left' valign='top' style='font-size:12px'>");
				 out.println("<img src='upload/相册/"+guest+"/image/me.jpg' width='60' height='60''/>");
				 out.println("</td>");
				 out.println("<td align='left' width='150'><a style='font-size:12px;color:red'>"+guest+"</a>发表评论：</td>");
				 out.println("<td align='right' width='350'><a style='font-size:12px;'>"+blog_comment_date+"</a></td>");
				 out.println("</tr>");
				 out.println("<tr>");
				 out.println("<td colspan='2' align='left' >"+blog_comment_content+"</td>");
				 out.println("</tr>");
			 	 out.println("</table>");
				//out.println("<div><a>评论人："+guest+"</a></div>");
				//out.println("<div><a>评论内容："+blog_comment_content+"</a></div>");
				//out.println("<div><a>评论时间："+blog_comment_date+"</a></div>");
				out.println("</div>");
			}
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
