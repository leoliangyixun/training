<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.yangkai.myblog.tools.PagingParamTool"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="com.yangkai.myblog.domain.Blog"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<title>欢迎访问枫雅博客</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
</head>

<body>
<%
List<Blog> latest_blog = (List<Blog>) session.getAttribute("latest_blog");//latest_blog表示最新博客列表。
if(latest_blog==null)
{
	response.sendRedirect("LatestBlog");//思考：能不能通过过滤器实现。
}else{
	String loginuser=(String)session.getAttribute("loginuser");
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
	<li class="current_page_item"><a href="index.jsp">最新博客</a></li>
	<%if(loginuser!=null){%>
	<li><a href="friends_blog.jsp">好友动态</a></li>
	<%}%>
	<li><a href="user_blog.jsp">博客</a></li>			
	<li><a href="user_album.jsp">相册</a></li>
	<li><a href="user_mood.jsp">说说</a></li>
	<li><a href="user_msg.jsp">留言</a></li>
	<li><a href="user_home.jsp">个人中心</a></li>
	</ul>
	</div>
	<!--div id="album_info">
	<table border="1" width="280" bordercolor="#FF6633" style="border-collapse: collapse">
	  <tr>
	    <td>信息栏</td>
	    <td>信息栏</td>
	  </tr>
	  <tr>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	  </tr>
	</table>
	</div-->
	</div>
	</div>
	<div id="list">
	<div id="list_top">
	<div id="list_middle">
	<div id="list_bottom">
	<div id="blog_list">
	<%
	/**********************************************检查用户登录结果。**********************************************/
	String login=(String)session.getAttribute("login");
	if(login!=null && login.equals("false"))//在用“==”，“equals” 等运算符之前要先确保运算符左边不能为null。
	{
		out.println("<script>checkLogin();</script>");
		session.removeAttribute("login");
	}
	/**********************************************这里显示最新博客列表**********************************************/
	Integer currpage=null;
	Integer countpage=null;
	int pagesize=5;
	Integer countstep=null;
	Integer currstep=null;
	int pagestep=5;
	countpage=PagingParamTool.getCountpage(latest_blog.size(), pagesize);
	countstep=PagingParamTool.getCountstep(latest_blog.size(), pagesize, pagestep);
	currpage=PagingParamTool.getCurrentpage(curr_page, countpage);
	currstep=PagingParamTool.getCurrentstep(curr_step, countstep);
	for (int i = (currpage-1)*pagesize; i < currpage*pagesize; i++) 
	{
		if(i<latest_blog.size())
		{
			Blog blog = latest_blog.get(i);
			int blog_id = blog.getBlogid();
			String username=blog.getUsername();
			String blog_subject = blog.getBlogsubject();
			String blog_class = blog.getBlogclass();
			Date blog_date = blog.getBlogdate();
			String blog_content = blog.getBlogcontent();
			if(blog_content.length()>100)
			{
				blog_content=blog_content.substring(0, 100)+"......";
			}
			out.println("<div id='single_list'>");
			out.println("<div class='title'><a>文章标题：" + blog_subject
					 + "</a></div><div><a>作者："+username+"</a></div><div class='tag'><a>文章类型：" 
					 + blog_class + "</a></div><div><a>发表时间："
					 + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(blog_date) + "</a></div>");
			out.println("<div>" + blog_content + "</div>");
			out.println("<div align='left'><a href='ViewBlog?blog_id="+blog_id+"&mark="+Constants.BLOG_FOR_LATEST+"'>阅读全文>></a></div>");	   
			out.println("</div>");
		}
		else{
		     break;
		}
	}
	/***************************************这里显示分页信息***************************************/

	out.println("<div align='center' id='pages'>");
	if(countpage>=pagestep)
	{
		out.println("<a href='index.jsp?page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
		for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
		{
			out.println("<a href='index.jsp?page="+k+"&step="+currstep+"'>"+k+"</a>");
		}
		int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
		out.println("<a href='index.jsp?page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
	}else{
		if(countpage>1)
		{
			for(int k=1;k<=countpage;k++)
			{
				out.println("<a href='index.jsp?page="+k+"'>"+k+"</a>");
			}
		}
	}
	out.println("</div>");
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

