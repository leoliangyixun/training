<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="com.yangkai.myblog.domain.Blog"%>
<%@ page import="com.yangkai.myblog.domain.BlogComment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>编辑博文</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/myblog.js"></script>
</head>

<body>
<%
	int blog_id=Integer.parseInt(request.getParameter("blog_id"));
	int mark=Integer.parseInt(request.getParameter("mark"));
	String blog_link_mark=request.getParameter("blog_link_mark");
	List<String> user_blog_class=(List<String>)session.getAttribute("user_blog_class");
	
%>
<div>
<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
<div>
	<ul>
		<li><a href="index.jsp">最新博客</a></li>
		<li><a href="friends_blog.jsp">好友动态</a></li>
		<li class="current_page_item"><a href="user_blog.jsp">博客</a></li>			
		<li><a href="user_album.jsp">相册</a></li>
		<li><a href="user_mood.jsp">说说</a></li>
		<li><a href="user_message.jsp">留言</a></li>
		<li><a href="user_home.jsp">我的主页</a></li>	
	</ul>
</div>
</div>
</div>
<div id="list">
<div id="list_top">
<div id="list_middle">
<div id="list_bottom">
<div id="blog_list">
<%
	List<Blog> current_blog=null;
	if(mark==Constants.BLOG_FOR_USER || mark==Constants.BLOG_FOR_LATEST)
	{
		current_blog=(ArrayList<Blog>) session.getAttribute("user_blog");
	}
	if(mark==Constants.BLOG_FOR_DRAFT)
	{
		current_blog=(ArrayList<Blog>) session.getAttribute("draft_blog");
	}
	if(mark==Constants.BLOG_FOR_PRIVATE)
	{
		current_blog=(ArrayList<Blog>) session.getAttribute("private_blog");
	}
	for (int i = 0; i < current_blog.size(); i++) 
	{
		Blog blog = (Blog) current_blog.get(i);
		if(blog_id==blog.getBlogid())
		{
			String username = blog.getUsername();
			String blog_subject = blog.getBlogsubject();
			
			String blog_class = blog.getBlogclass();
			//Date blog_date = blog.getBlogdate();
			String blog_content = blog.getBlogcontent();
			out.println("<div id='single_list'>");
			/*****************************这里显示文章的全文信息*****************************/
			String str="";
			if(blog_link_mark!=null && !blog_link_mark.equals("null"))
			{
				if(Integer.parseInt(blog_link_mark)==Constants.BLOG_LINK_MARK_DRAFT)
				{
					str="<a href='blog_draft.jsp'>草稿箱<a>>><a href='blog_view.jsp?blog_id="+blog_id+"&mark="+mark+"&blog_link_mark="+Constants.BLOG_LINK_MARK_DRAFT+"'>浏览草稿博文</a>>><a style='font-style:normal'>编辑草稿博文</a>";
				}
				if(Integer.parseInt(blog_link_mark)==Constants.BLOG_LINK_MARK_PRIVATE)
				{
					str="<a href='blog_private.jsp'>私密日志<a>>><a href='blog_view.jsp?blog_id="+blog_id+"&mark="+mark+"&blog_link_mark="+Constants.BLOG_LINK_MARK_PRIVATE+"'>浏览私密日志</a>>><a style='font-style:normal'>编辑私密日志</a>";
				}
				out.println("<div align='left' class='back'>当前位置：<a href='user_blog.jsp'>博客</a>>>"+str+"</div>");
			}
			else{
			    out.println("<div align='left' class='back'>当前位置：<a href='user_blog.jsp'>博客</a>>><a href='blog_view.jsp?blog_id="+blog_id+"&mark="+mark+"'>浏览博文</a>>><a style='font-style:normal'>编辑博文</a></div>");
			}
			out.println("<!--div align='right' class='back'><a href='blog_view.jsp?blog_id="+blog_id+"&mark="+mark+"'>返回</a></div-->");
			//out.println("<div class='title' align='center'><a>文章标题：" + blog_subject + "</a></div>");	
			/*
			out.println("<div><a>作者：" + username
					  + "</a></div><div class='tag'><a>文章类型：" + blog_class
					  + "</a></div><div><a>发表时间：" + blog_date + "</a></div>");
			*/
			out.println("<div class='blog_content'>");
			out.println("<form action='EditBlog' method='post'>");
			out.println("<input type='hidden' name='blog_id' value='"+blog_id+"' />");	
			out.println("<input type='hidden' name='mark' value='"+mark+"' />");	
			out.println("<input type='hidden' name='blog_link_mark' value='"+blog_link_mark+"' />");
			out.println("<a style='margin-bottom:10px;font-size:16px'>文章标题：</a><input type='text' name='blog_subject' size='65' value='"+blog_subject+"' style='margin-left:7px'/>");
			out.println("<a style='margin-bottom:10px;font-size:16px'>文章类别：</a>");
			out.println("<select name='blog_class'>");
			out.println("<option value='"+blog_class+"' selected='selected'>"+blog_class+"</option>");
	        if(user_blog_class!=null && user_blog_class.size()>0)
	    	{
				for(int j=0;j<user_blog_class.size();j++)
				{
					out.println("<option value="+user_blog_class.get(j)+">"+user_blog_class.get(j)+"</option>");
				}
	       	}   
	    	out.println("</select>");
	        
			out.println("<textarea class='textarea' name='blog_content' cols='60' rows='20'>"+ blog_content + "</textarea>");
			out.println("<div align='center' class='button_style'><input type='submit' value='提交修改' /><input type='reset' value='取消'/></div>");	
			out.println("</form>");
			out.println("</div>");//end of blog_content
			out.println("</div>");//end of single_list
			break;
		}
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