<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.yangkai.myblog.tools.PagingParamTool"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="com.yangkai.myblog.domain.Blog"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <title>好友动态</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="js/myblog.js"></script>
  </head>
  
  <body>
     <%
		String curr_page=request.getParameter("page");
        String curr_step=request.getParameter("step");
     %> 
	<div>
	<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
	<div id="content">
	<div id="menu_up">
	<div id="menu">
	<div>
	<ul>
		<li><a href="index.jsp">最新博客</a></li>
		<li class="current_page_item"><a href="friends_blog.jsp">好友动态</a></li>
		<li><a href="user_blog.jsp">博客</a></li>			
		<li><a href="user_album.jsp">相册</a></li>
		<li><a href="user_mood.jsp">说说</a></li>
		<li><a href="user_msg.jsp">留言</a></li>
		<li><a href="user_home.jsp">个人中心</a></li>		
	</ul>
	</div>
	</div>
	</div>
	<div id="list">
	<div id="list_top">
	<div id="list_middle">
	<div id="list_bottom">
	<div id="blog_list">
		<div id="link_list_style">
		    <a href="friends_blog.jsp" style="background-color:#E97B56;">好友博文</a>
		    <a href="friends_album.jsp">好友相册</a>
			<a href="friends_mood.jsp">好友说说</a>
			<a href="friends_message.jsp">好友留言</a>
		</div>
		<%
		/*******************************分页参数*******************************/
		List<Blog> friends_blog = (List<Blog>) session.getAttribute("friends_blog");
		if(friends_blog!=null && friends_blog.size()>0)
		{
		    Integer currpage=null;
			Integer countpage=null;
			int pagesize=5;
			Integer countstep=null;
			Integer currstep=null;
			int pagestep=5;
			countpage=PagingParamTool.getCountpage(friends_blog.size(), pagesize);
			countstep=PagingParamTool.getCountstep(friends_blog.size(), pagesize, pagestep);
			currpage=PagingParamTool.getCurrentpage(curr_page, countpage);
			currstep=PagingParamTool.getCurrentstep(curr_step, countstep);
			/*******************************显示好友的博客*******************************/
			for (int i = (currpage-1)*pagesize; i < currpage*pagesize; i++) 
			{
				if(i<friends_blog.size())
				{
					Blog blog_info=(Blog)friends_blog.get(i);
					int blog_id = blog_info.getBlogid();
					String friends_name=blog_info.getUsername();
					String blog_subject = blog_info.getBlogsubject();
					String blog_class = blog_info.getBlogclass();
					Date blog_date = blog_info.getBlogdate();
					String blog_content = blog_info.getBlogcontent();
			        if(blog_content.length()>100)
			        {
			        	blog_content=blog_content.substring(0, 100)+"......";
			        }
		 			out.println("<div id='single_list'>");
					out.println("<div class='title'><a>文章标题：" + blog_subject
						+ "</a></div><div><a>作者："+friends_name+"</a></div><div class='tag'><a>文章类型：" 
						+ blog_class + "</a></div><div><a>发表时间："
						+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(blog_date) + "</a></div>");
					out.println("<div>" + blog_content + "</div>");
					out.println("<div align='left'><a href='ViewBlog?blog_id="+blog_id+"&mark="
								+Constants.BLOG_FOR_FRIENDS+"'>阅读全文>></a></div>");
					out.println("</div>");
				}
				else{
					break;
				}
			}
	    	out.println("<div align='center' id='pages'>");
	    	if(countpage>=pagestep)
	    	{
	    		out.println("<a href='friends_blog.jsp?page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
	    		for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
	    		{
	    			out.println("<a href='friends_blog.jsp?page="+k+"&step="+currstep+"'>"+k+"</a>");
	    		}
	    		int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
	    		out.println("<a href='friends_blog.jsp?page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
	    	}else{
	    		if(countpage>1)
	    		{
	    			for(int k=1;k<=countpage;k++)
	    			{
	    				out.println("<a href='friends_blog.jsp?page="+k+"'>"+k+"</a>");
	    			}
	    		}
	    	}
	    	out.println("</div>");	
		}
		else{
		     out.println("<div align='center'><a class='success_style'>没有好友最新博文动态</a></div>");
		}
	%>
	</div>
	</div>
	</div>
	</div>
	</div>
	<div style="clear:both"></div>
	</div>
	<jsp:include page="include/bottom.html" flush="true"></jsp:include>
	</div>
  </body>
</html>
