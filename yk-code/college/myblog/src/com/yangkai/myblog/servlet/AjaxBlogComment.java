package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.BlogComment;
import com.yangkai.myblog.domain.BlogCommentReply;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class AjaxBlogComment extends HttpServlet {


	@SuppressWarnings("unused")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		String loginuser=(String)session.getAttribute("loginuser");
		int blog_id=Integer.parseInt(request.getParameter("blog_id"));
		//int mark=Integer.parseInt(request.getParameter("mark"));
		MyBlogService service=ServiceFactory.getMyBlogService(request);
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
				 out.println("<table border='0' width='570' bgcolor='#FFFF80' bordercolor='#000000' style='margin:10px 0;border-collapse: collapse;'>");
				 out.println("<tr>");
				 out.println("<td rowspan='2' width='60' align='left' valign='top' style='font-size:12px'>");
				 out.println("<img src='upload/相册/"+guest+"/image/me.jpg' width='60' height='60''/>");
				 out.println("</td>");
				 out.println("<td align='left' width='165'><a style='font-size:12px;color:red'>"+guest+"</a>发表评论：</td>");
				 out.println("<td align='right' width='350'><a style='font-size:12px;'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(blog_comment_date)+"</a></td>");
				 out.println("</tr>");
				 out.println("<tr>");
				 out.println("<td colspan='2' align='left' >"+blog_comment_content+"</td>");
				 out.println("</tr>");
			 	 out.println("</table>");
				//out.println("<div><a>评论人："+guest+"</a></div>");
				//out.println("<div><a>评论内容："+blog_comment_content+"</a></div>");
				//out.println("<div><a>评论时间："+blog_comment_date+"</a></div>");
			 	/*************************************显示评论的回复*************************************/
				List<BlogCommentReply> blog_comment_reply=service.getBlogCommentReply(blog_comment_id);//获取博主对该博文某一评论对应的回复
				if(blog_comment_reply!=null && blog_comment_reply.size()>0)
				{
						for(int j=0;j<blog_comment_reply.size();j++)
						{
							BlogCommentReply blogcommentreply=blog_comment_reply.get(j);
							int blog_comment_reply_id=blogcommentreply.getBlogcommentreplyid();
							String blog_comment_reply_content=blogcommentreply.getBlogcommentreplycontent();
							Date blog_comment_reply_date=blogcommentreply.getBlogcommentreplydate();
							out.println("<div class='link_style3'>");
							out.println("<table border='0' width='510' bgcolor='#FFFF80' style='margin:0 0 10px 60px;border-collapse: collapse;'>");
							out.println("<tr>");
							out.println("<td rowspan='2' width='60' align='center' style='font-size:12px'>");
							out.println("<img src='upload/相册/"+loginuser+"/image/me.jpg' width='60' height='60''/><br>");
							out.println("<a>"+DateFormat.getDateTimeInstance().format(blog_comment_reply_date)+"</a><br>");
							out.println("</td>");
							out.println("<td valign='top' align='left'>"+blog_comment_reply_content+"</td>");
							out.println("</tr>");
							out.println("<tr>");
							out.println("<td valign='top' align='left' height='20'></td>");
							out.println("</tr>");
							out.println("</table>");
							out.println("</div>");
						}
				}
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
