package com.yangkai.myblog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class DeleteBlogComment extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//HttpSession session=request.getSession();
		int blog_comment_id=Integer.parseInt(request.getParameter("blog_comment_id"));
		int blog_id=Integer.parseInt(request.getParameter("blog_id"));
		int mark=Integer.parseInt(request.getParameter("mark"));
		/******************************************将删除更新到数据库******************************************/
		//删除评论的同时也要将该评论的回复也删除。
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		System.out.println("DeleteBlogComment:"+service);
		service.deleteBlogComment(blog_comment_id);
		/******************************************将删除更新到集合********************************************/
		/*
		HttpSession session=request.getSession();
		List<BlogCommentListBean> bc_comment=(List<BlogCommentListBean>) session.getAttribute("bc_comment");
		for(int i=0;i<bc_comment.size();i++)
		{
			BlogCommentListBean bc=bc_comment.get(i);
			if(bc.getBlogCommentId()==blogcomment_id)
			{
				bc_comment.remove(bc);
				break;
			}
		}
		session.setAttribute("bc_comment", bc_comment);
		*/
		//同理，删除用户评论后将页面重定向到BlogView,这样就不用更新集合了，不过这会降低系统的性能，因为要重新加载博文评论。
		response.sendRedirect("ViewBlog?blog_id="+blog_id+"&mark="+mark+"");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
