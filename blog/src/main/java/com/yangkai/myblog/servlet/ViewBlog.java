package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.constants.Constants;
import com.yangkai.myblog.domain.BlogComment;
import com.yangkai.myblog.domain.BlogCommentReply;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class ViewBlog extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			HttpSession session=request.getSession();
			int blog_id=Integer.parseInt(request.getParameter("blog_id"));
			int mark=Integer.parseInt(request.getParameter("mark"));
			String blog_link_mark=request.getParameter("blog_link_mark");
			MyBlogService service=ServiceFactory.getMyBlogService(request);
			Map<Integer,List<BlogCommentReply>> blog_comment_reply_map=null;
			List<BlogComment>blog_comment=null;
			/*
			 * Map<Integer,List<BlogCommentReply>>
			 * key--->blog_comment_id
			 * value--->List<BlogCommentReply>
			 * */
		    blog_comment=service.getBlogComment(blog_id);//获取该博文对应的评论。
			if(blog_comment!=null)
			{
				//根据博文评论编号获取对应的回复信息。
				for(int i=0;i<blog_comment.size();i++)
				{
					int blog_comment_id=blog_comment.get(i).getBlogcommentid();
					List<BlogCommentReply> blog_comment_reply=service.getBlogCommentReply(blog_comment_id);//获取博主对该博文某一评论对应的回复
					if(blog_comment_reply!=null)
					{
						if(blog_comment_reply_map==null)
						{
							blog_comment_reply_map=new HashMap<Integer,List<BlogCommentReply>>();//获取评论编号-回复信息的Map集合。
						}
						blog_comment_reply_map.put(blog_comment_id, blog_comment_reply);
					}	
				}
			}
			session.setAttribute("blog_comment", blog_comment);
			session.setAttribute("blog_comment_reply_map", blog_comment_reply_map);
			response.sendRedirect("blog_view.jsp?blog_id="+blog_id+"&mark="+mark+"&blog_link_mark="+blog_link_mark+"");
	}	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
