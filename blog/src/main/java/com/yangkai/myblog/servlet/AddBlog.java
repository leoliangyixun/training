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
		 * ����������²������ݿ⡣
		 */
		int count=service.addBlog(blog);
		/*
		 * ���²��ͼ���
		 */
		if(count>0)
		{	
			//���ַ�ʽ���ã���Ϊû�б�Ҫ���¼����û������в��ͣ��������������ݿ�Ŀ������Ӷ�����ϵͳ�����ܡ�
			List<Blog> user_blog = service.getBlog(loginuser);
			session.setAttribute("user_blog", user_blog);
			/*
			 *���ʹ���������ַ�ʽ���²��ͣ���������,
			 *��Ȼ������ϵͳ�Ŀ�������������Ĳ��ĵı���������ݿ��Զ����ɵģ�
			 *�������ʽ���޷���ò��ı�ţ������º����벩�ı����صĲ����޷�����,
			 *�ۺϿ��ǣ�����ʹ������ķ�ʽ��
			 */
			//List<Blog> user_blog=(List<Blog>) session.getAttribute("user_blog");
			//blog������û�в��ı��������ԣ�null������ô�����º����벩�ı����صĲ����޷����С�
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
		 * ���Դ���
		 */
		/*
		System.out.println("�û�����"+loginuser);
		System.out.println("���ı��⣺"+blog_subject);
		System.out.println("���ķ��ࣺ"+blog_class);
		System.out.println("�������ݣ�"+blog_content);
		System.out.println("����ʱ�䣺"+blog_date);
		*/
	}

}
