<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.yangkai.myblog.domain.User"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.yangkai.myblog.domain.Blog"%>
 <%
         String curr_page=request.getParameter("page");
         String curr_step=request.getParameter("step");
         List<Blog> user_blog = (List<Blog>) session.getAttribute("user_blog");
         out.println("<div style='margin-left:7px; border:#E97B56 solid 1px;margin-top:20px;padding-bottom:20px'>");
     	 if(user_blog!=null && user_blog.size()>0)
         {
			Integer currpage=null;
			Integer countpage=null;
			int pagesize=2;
			Integer countstep=null;
			Integer currstep=null;
			int pagestep=5;
     		countpage=PagingUtil.getCountpage(user_blog.size(), pagesize);
     		countstep=PagingUtil.getCountstep(user_blog.size(), pagesize, pagestep);
     		currpage=PagingUtil.getCurrentpage(curr_page, countpage);
     		currstep=PagingUtil.getCurrentstep(curr_step, countstep);   
     		
			for (int i = (currpage-1)*pagesize;i < currpage*pagesize; i++) 
			{
				if(i<user_blog.size())
				{
					 Blog blog= user_blog.get(i);
					 int blog_id = blog.getBlogid();
					 String blog_subject =blog.getBlogsubject();
					 String blog_class = blog.getBlogclass();
					 Date blog_date = blog.getBlogdate();
					 String blog_content = blog.getBlogcontent();
					 if(blog_content.length()>100)
					 {
						 blog_content=blog_content.substring(0, 100)+"......";
					 }
					
					 out.println("<div id='single_list'>");
					 out.println("<div class='title'><a>文章标题：" + blog_subject
							   + "</a></div><div class='tag'><a>文章类型：" 
							   + blog_class + "</a></div>");
					 out.println("<div>" + blog_content + "</div>");
					 out.println("<div align='left' class='link_style'><a>发表时间："+ new SimpleDateFormat("yyyy-MM-dd HH:mm").format(blog_date) + "</a>"
								+"<span style='margin-left:160px;'>" 
								+"<a href='ViewBlog?blog_id="+blog_id+"&mark="+Constants.BLOG_FOR_USER+"'>阅读</a>"
								+"<a href='blog_edit.jsp?blog_id="+blog_id+"&mark="+Constants.BLOG_FOR_USER+"'>编辑</a>"
								+ "<a href='DeleteBlog?blog_id="+blog_id+"&mark="+Constants.BLOG_FOR_USER+"'>删除</a>"
								+"<a href='javascript:sendBlogCommentRequest("+blog_id+")'>查看评论</a><!--a href='AlterBlogState?blog_id="+blog_id+"&type="+Constants.BLOG_FOR_USER+"'>转为私密日志</a--></span></div>");						
					 out.println("<div id='blog_comment"+blog_id+"'></div>");
					 out.println("</div>");
				}
				else{
					break;
				}
			}//end of for
     		
		    out.println("<div align='center' id='pages'>");
		    if(countpage>pagestep)
		    {   	
		    	out.println("<a href='javascript:getMyBlog("+(pagestep*(currstep-1))+","+(currstep-1)+")'>&laquo;</a>");
		    	for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
		    	{	
		    		out.println("<a href='javascript:getMyBlog("+k+","+currstep+")'>"+k+"</a>");
		    	}
		    	int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
		    	out.println("<a href='javascript:getMyBlog("+max+","+(currstep+1)+")'>&raquo;</a>");	
		    }else{
		    	if(countpage>1)
		    	{
		    		for(int k=1;k<=countpage;k++)
		    		{
		    			out.println("<a href='javascript:getMyBlog("+k+","+null+")'>"+k+"</a>");
		    		}
		    	}
		    }
		    out.println("</div>");	
     	}else{
     		out.println("<div align='center'><a  class='success_style'>你还没有发表博客!!!</a><a href='blog_add.jsp' class='continue_style'>写博客</a></div>");
     	}
		out.println("<div>");
%>                 		
