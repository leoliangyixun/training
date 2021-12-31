package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.constants.Constants;
import com.yangkai.myblog.domain.Blog;
import com.yangkai.myblog.domain.User;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncoderUtil;

public class AddDraftBlog extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		String loginuser=(String)session.getAttribute("loginuser");
		String blog_subject=EncoderUtil.encode(request.getParameter("blog_subject"))	;
		String blog_class=EncoderUtil.encode(request.getParameter("blog_class"));
		String blog_content=EncoderUtil.encode(request.getParameter("blog_content"));
		String blog_date=request.getParameter("blog_date");
		Blog blog=new Blog();
		blog.setUsername(loginuser);
		blog.setBlogsubject(blog_subject);
		blog.setBlogclass(blog_class);
		blog.setBlogcontent(blog_content);
		try {
			blog.setBlogdate(DateFormat.getDateTimeInstance().parse(blog_date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		blog.setBlogstate(Constants.BLOG_STATE_FOR_DRAFT);
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		
		/*
		 * ???????????????????
		 */
		int count=service.addBlog(blog);;
		/*
		 * ??????????
		 */
		//System.out.println("count:"+count);
		if(count>0)
		{
			/*
			 *????????Ч?????????跨???????????????????
			 */
			List<Blog> draft_blog = service.getDraftBlog(loginuser);
			session.setAttribute("draft_blog",draft_blog);
			//List<Blog> draft_blog = (List<Blog>) session.getAttribute("draft_blog");
			//draft_blog.add(blog);
			//System.out.println("draft_blog??"+draft_blog.size());
//			int num=service.alterBlogNum(loginuser,Constants.BLOG_ADD_NUM);
//			if(num>0)
//			{
//				User bloger=(User)session.getAttribute("bloger");
//				bloger.setBlognum(bloger.getBlognum()+1);
//				session.setAttribute("bloger", bloger);
//			}
			response.sendRedirect("blog_draft.jsp");
		}else{
			response.sendRedirect("include/error.jsp");
		}
		/*
		 * ???????
		 */
		/*
		System.out.println("???????"+loginuser);
		System.out.println("???????"+blog_subject);
		System.out.println("???????"+blog_class);
		System.out.println("?????????"+blog_content);
		System.out.println("???????"+blog_date);
		*/
	}

}
