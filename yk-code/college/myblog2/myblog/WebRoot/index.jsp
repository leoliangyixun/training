<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.yangkai.myblog.tools.PagingParamTool"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="com.yangkai.myblog.domain.Blog"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<title>��ӭ���ʷ��Ų���</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
</head>

<body>
<%
List<Blog> latest_blog = (List<Blog>) session.getAttribute("latest_blog");//latest_blog��ʾ���²����б�
if(latest_blog==null)
{
	response.sendRedirect("LatestBlog");//˼�����ܲ���ͨ��������ʵ�֡�
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
	<li class="current_page_item"><a href="index.jsp">���²���</a></li>
	<%if(loginuser!=null){%>
	<li><a href="friends_blog.jsp">���Ѷ�̬</a></li>
	<%}%>
	<li><a href="user_blog.jsp">����</a></li>			
	<li><a href="user_album.jsp">���</a></li>
	<li><a href="user_mood.jsp">˵˵</a></li>
	<li><a href="user_msg.jsp">����</a></li>
	<li><a href="user_home.jsp">��������</a></li>
	</ul>
	</div>
	<!--div id="album_info">
	<table border="1" width="280" bordercolor="#FF6633" style="border-collapse: collapse">
	  <tr>
	    <td>��Ϣ��</td>
	    <td>��Ϣ��</td>
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
	/**********************************************����û���¼�����**********************************************/
	String login=(String)session.getAttribute("login");
	if(login!=null && login.equals("false"))//���á�==������equals�� �������֮ǰҪ��ȷ���������߲���Ϊnull��
	{
		out.println("<script>checkLogin();</script>");
		session.removeAttribute("login");
	}
	/**********************************************������ʾ���²����б�**********************************************/
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
			out.println("<div class='title'><a>���±��⣺" + blog_subject
					 + "</a></div><div><a>���ߣ�"+username+"</a></div><div class='tag'><a>�������ͣ�" 
					 + blog_class + "</a></div><div><a>����ʱ�䣺"
					 + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(blog_date) + "</a></div>");
			out.println("<div>" + blog_content + "</div>");
			out.println("<div align='left'><a href='ViewBlog?blog_id="+blog_id+"&mark="+Constants.BLOG_FOR_LATEST+"'>�Ķ�ȫ��>></a></div>");	   
			out.println("</div>");
		}
		else{
		     break;
		}
	}
	/***************************************������ʾ��ҳ��Ϣ***************************************/

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

