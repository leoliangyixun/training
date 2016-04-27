package com.yangkai.myblog.servlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.constants.Constants;
import com.yangkai.myblog.domain.Blog;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncoderUtil;
public class EditBlog extends HttpServlet {

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			HttpSession session=request.getSession();
			String loginuser=(String)session.getAttribute("loginuser");
			int blog_id=Integer.parseInt(request.getParameter("blog_id"));
			int mark=Integer.parseInt(request.getParameter("mark"));
			//int blog_link_mark=Integer.parseInt(request.getParameter("blog_link_mark"));
			String blog_link_mark=request.getParameter("blog_link_mark");
			String blog_subject=EncoderUtil.encode(request.getParameter("blog_subject"));
			String blog_class=EncoderUtil.encode(request.getParameter("blog_class"));
			String blog_content=EncoderUtil.encode(request.getParameter("blog_content"));
			/*
			 *���޸ĸ��µ����ݿ�
			 */
			MyBlogService service=ServiceFactory.getMyBlogService(request);
			Blog blog=new Blog();
			blog.setBlogid(blog_id);
			blog.setBlogclass(blog_class);
			blog.setBlogsubject(blog_subject);
			blog.setBlogcontent(blog_content);
			int count=service.updateBlogContent(blog);
			if(count>0){
				if(mark==Constants.BLOG_FOR_LATEST || mark==Constants.BLOG_FOR_USER){
					/*
					 *���������󽵵�ϵͳ�����ܣ�����������Ϊ�޸���һƪ���Ľ����Ҫ���¼������е����²��ġ�
					 *List<Blog> latest_blog=service.getLatestBlog();
					 *session.setAttribute("latest_blog", latest_blog);
					 *Ϊ�����ϵͳ�����ܣ�Ӧ����ô����
					 */
					List<Blog> user_blog=(List<Blog>) session.getAttribute("user_blog");
					List<Blog> latest_blog=(List<Blog>) session.getAttribute("latest_blog");
					for(int i=0;i< user_blog.size();i++){
						Blog oldblog=user_blog.get(i);
						if(oldblog.getBlogid()==blog_id){
							oldblog.setBlogsubject(blog_subject);
						    oldblog.setBlogclass(blog_class);
							oldblog.setBlogsubject(blog_subject);
							oldblog.setBlogcontent(blog_content);
							latest_blog.remove(oldblog);
							latest_blog.add(user_blog.get(i));
						    break;
						}
					}
				}
				
				if(mark==Constants.BLOG_FOR_USER)
				{
				    List<Blog> user_blog=(List<Blog>) session.getAttribute("user_blog");
					for(int i=0;i< user_blog.size();i++)
					{
						if( user_blog.get(i).getBlogid()==blog_id)
						{
							user_blog.get(i).setBlogclass(blog_class);
							user_blog.get(i).setBlogsubject(blog_subject);
							user_blog.get(i).setBlogcontent(blog_content);
							break;
						}
					}
				}
				
				if(mark==Constants.BLOG_FOR_DRAFT)
				{
					/*
					 * ���������󽵵�ϵͳ�����ܣ�����������Ϊ�޸���һƪ���Ľ����Ҫ���¼����û����вݸ����еĲ��ġ�
					 * List<Blog> draft_blog=service.getDraftBlog(loginuser);
					 * session.setAttribute("draft_blog", draft_blog);
					 */
					List<Blog> draft_blog=(List<Blog>) session.getAttribute("draft_blog");
					for(int i=0;i< draft_blog.size();i++)
					{
						if( draft_blog.get(i).getBlogid()==blog_id)
						{
							draft_blog.get(i).setBlogclass(blog_class);
							draft_blog.get(i).setBlogsubject(blog_subject);
							draft_blog.get(i).setBlogcontent(blog_content);
							break;
						}
					}
				}
				
				if(mark==Constants.BLOG_FOR_PRIVATE)
				{
					/*
					 * ���������󽵵�ϵͳ�����ܣ�����������Ϊ�޸���һƪ���Ľ����Ҫ���¼����û����вݸ����еĲ��ġ�
					 * List<Blog> private_blog=service.getPrivateBlog(loginuser);
					 * session.setAttribute("private_blog", private_blog);
					 */
					List<Blog> private_blog=(List<Blog>) session.getAttribute("private_blog");
					for(int i=0;i< private_blog.size();i++)
					{
						if( private_blog.get(i).getBlogid()==blog_id)
						{
							private_blog.get(i).setBlogclass(blog_class);
							private_blog.get(i).setBlogsubject(blog_subject);
							private_blog.get(i).setBlogcontent(blog_content);
							break;
						}
					}
				}
				/*
				 * ��һ��Ϊʲô����ֱ����ת��JSPҳ�棬�����ù��ȵ�ViewBlog���Servlet��
				 * ��Ϊ��ʱ���ĵ����ۺͻظ��Ѿ���session���ˣ�û�б�Ҫ���»�ȡ��
				 */
				response.sendRedirect("blog_view.jsp?blog_id="+blog_id+"&mark="+mark+"&blog_link_mark="+blog_link_mark);
				System.out.println("�޸Ĳ��ĳɹ���");
			}else{
				response.sendRedirect("include/error.jsp");
			}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
