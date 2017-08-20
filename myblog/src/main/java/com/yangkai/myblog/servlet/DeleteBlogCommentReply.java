package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.BlogCommentReply;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class DeleteBlogCommentReply extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			HttpSession session=request.getSession(true);
			int blog_comment_reply_id=Integer.parseInt(request.getParameter("blog_comment_reply_id"));
			int blog_comment_id=Integer.parseInt(request.getParameter("blog_comment_id"));
			int blog_id=Integer.parseInt(request.getParameter("blog_id"));
			int mark=Integer.parseInt(request.getParameter("mark"));
			Map<Integer,List<BlogCommentReply>> blog_comment_reply_map=(Map<Integer, List<BlogCommentReply>>) session.getAttribute("blog_comment_reply_map"); 
			/***********************************更新数据库***********************************/
			MyBlogService service=ServiceFactory.getMyBlogService(request);
			int count=service.deleteBlogCommentReply(blog_comment_reply_id);
			/***********************************更新集合***********************************/
			if(count>0)
			{
				List<BlogCommentReply> blog_comment_reply=blog_comment_reply_map.get(blog_comment_id);
				for(int i=0;i<blog_comment_reply.size();i++)
				{
					if(blog_comment_reply.get(i).getBlogcommentreplyid()==blog_comment_reply_id)
					{
						blog_comment_reply.remove(i);
						break;
					}
				}
				response.sendRedirect("blog_view.jsp?blog_id="+blog_id+"&mark="+mark+"");
			}
	}
}
