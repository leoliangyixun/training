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

public class AddBlog extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession(true);
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
		blog.setBlogstate(Constants.BLOG_STATE_FOR_RELEASE);
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		
		/*
		 * 将发表的文章插入数据库。
		 */
		int count=service.addBlog(blog);
		/*
		 * 更新博客集合
		 */
		if(count>0)
		{	
			//这种方式不好，因为没有必要重新加载用户的所有博客，这样会增大数据库的开销，从而减低系统的性能。
			List<Blog> user_blog = service.getBlog(loginuser);
			session.setAttribute("user_blog", user_blog);
			/*
			 *如果使用下面这种方式更新博客，问题会更大,
			 *虽然降低了系统的开销，由于新添的博文的编号是由数据库自动生成的，
			 *在这个方式中无法获得博文编号，将导致后期与博文编号相关的操作无法进行,
			 *综合考虑，还是使用上面的方式。
			 */
			//List<Blog> user_blog=(List<Blog>) session.getAttribute("user_blog");
			//blog对象中没有博文编号这个属性（null），那么将导致后期与博文编号相关的操作无法进行。
			//user_blog.add(blog);
			
//			int num=service.alterBlogNum(loginuser,Constants.BLOG_ADD_NUM);
//			if(num>0)
//			{
//				User bloger=(User)session.getAttribute("bloger");
//				bloger.setBlognum(bloger.getBlognum()+1);
//				session.setAttribute("bloger", bloger);	
//			}
			//RequestDispatcher rd=request.getRequestDispatcher("user_blog.jsp");
			//rd.forward(request, response);
			response.sendRedirect("user_blog.jsp");
		}else{
			response.sendRedirect("include/error.jsp");
		}
		/*
		 * 测试代码
		 */
		/*
		System.out.println("用户名："+loginuser);
		System.out.println("博文标题："+blog_subject);
		System.out.println("博文分类："+blog_class);
		System.out.println("博文内容："+blog_content);
		System.out.println("发表时间："+blog_date);
		*/
	}

}
