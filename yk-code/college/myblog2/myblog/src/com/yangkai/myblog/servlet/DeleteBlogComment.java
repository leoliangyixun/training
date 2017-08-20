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
		/******************************************��ɾ�����µ����ݿ�******************************************/
		//ɾ�����۵�ͬʱҲҪ�������۵Ļظ�Ҳɾ����
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		System.out.println("DeleteBlogComment:"+service);
		service.deleteBlogComment(blog_comment_id);
		/******************************************��ɾ�����µ�����********************************************/
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
		//ͬ��ɾ���û����ۺ�ҳ���ض���BlogView,�����Ͳ��ø��¼����ˣ�������ή��ϵͳ�����ܣ���ΪҪ���¼��ز������ۡ�
		response.sendRedirect("ViewBlog?blog_id="+blog_id+"&mark="+mark+"");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
