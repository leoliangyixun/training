package com.yangkai.myblog.servlet;

import java.io.IOException;
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

public class DeleteBlogComment extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		int blog_comment_id=Integer.parseInt(request.getParameter("blog_comment_id"));
		int blog_id=Integer.parseInt(request.getParameter("blog_id"));
		int mark=Integer.parseInt(request.getParameter("mark"));
		/******************************************�������ݿ�******************************************/
		//���ƣ�ɾ�����۵�ͬʱҲҪ�������۵Ļظ�Ҳɾ����
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		service.deleteBlogComment(blog_comment_id);
		/******************************************���¼���********************************************/
		List<BlogComment> blog_comment=(List<BlogComment>) session.getAttribute("blog_comment");
		Map<Integer,List<BlogCommentReply>> blog_comment_reply_map=(Map<Integer, List<BlogCommentReply>>) session.getAttribute("blog_comment_reply_map");
		for(int i=0;i<blog_comment.size();i++)
		{
			BlogComment blogcomment=blog_comment.get(i);
			if(blogcomment.getBlogcommentid()==blog_comment_id)
			{
				blog_comment.remove(blogcomment);
				break;
			}
		}
		if(blog_comment_reply_map!=null && blog_comment_reply_map.containsKey(blog_comment_id))
		{
			blog_comment_reply_map.remove(blog_comment_id);//ɾ���ò������۵Ļظ���
		}
		//ע�⣺ɾ���û����ۺ�û�б�Ҫ��ҳ���ض���ViewBlog,�����ή��ϵͳ�����ܣ���ΪҪ���¼��ز������ۺ����۵Ļظ���
		response.sendRedirect("blog_view.jsp?blog_id="+blog_id+"&mark="+mark+"");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
