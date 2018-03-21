package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yangkai.myblog.domain.BlogComment;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncoderUtil;

public class AddBlogComment extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int blog_id=Integer.parseInt(request.getParameter("blog_id"));//��ȡҪ���۲��ĵ�ID�š�
		int mark=Integer.parseInt(request.getParameter("mark"));
		String guest=EncoderUtil.encode(request.getParameter("guest"));//��ȡ�������û�����
		String blog_comment_content=EncoderUtil.encode(request.getParameter("blog_comment_content"));//��ȡ�������ݡ�
		String blog_comment_date=request.getParameter("blog_comment_date");//��ȡ�ύ���۵�ʱ�䡣
		BlogComment blogcomment=new BlogComment();
		blogcomment.setBlogid(blog_id);
		blogcomment.setGuest(guest);
		blogcomment.setBlogcommentcontent(blog_comment_content);
		try {
			blogcomment.setBlogcommentdate(DateFormat.getDateTimeInstance().parse(blog_comment_date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		/**************************************�����۸��µ����ݿ�**************************************/
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		service.addBlogComment(blogcomment);
		/***************************************�����۸��µ�����***************************************/
		/*
		BlogCommentListBean mub_list = new BlogCommentListBean();
		mub_list.setBlogCommentId(0);//����֮�����������������۵�id�ڼ����ж�Ϊ0��
		mub_list.setBlogId(blog_id);
		mub_list.setGuestUsername(guest_username);
		mub_list.setBlogCommentContent(blogcomment_content);
		mub_list.setBlogCommentDate(blogcomment_date);
		HttpSession session=request.getSession();
		
		List<BlogCommentListBean> bc_comment=(List<BlogCommentListBean>) session.getAttribute("bc_comment");
		if(bc_comment==null)
		{
			bc_comment=new ArrayList<BlogCommentListBean>();
		}
		bc_comment.add(mub_list);
		session.setAttribute("bc_comment", bc_comment);
		*/
		
		//ͨ���ض���BlogView��û�б�Ҫ�����۸��µ����ϣ��Ӷ����������ۼ����е�����id�޷�ȷ�������ξ��档
		response.sendRedirect("ViewBlog?blog_id="+blog_id+"&mark="+mark+"");
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doGet(request, response);
	}

}
