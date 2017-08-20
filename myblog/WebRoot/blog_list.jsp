<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@page import="com.yangkai.myblog.domain.Blog"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>博文列表</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>

<div>
<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
<div>
	<ul>
		<li class="current_page_item"><a href="blog_list.jsp">博文列表</a></li>
		<li><a href="blogclass_add.jsp">新建分类</a></li>			
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
				    <a href="blog_list.jsp" style="background-color:#E97B56;">我的文章</a>
					<a href="blog_private_list.jsp">私密日志</a>
					<a href="blog_draft_list.jsp">草稿箱</a>
				</div>
				<%
					String curr_page=request.getParameter("page");
					String curr_step=request.getParameter("step");
					List<Blog> user_blog=(List<Blog>)session.getAttribute("user_blog");
					if(user_blog!=null && user_blog.size()>0)
					{
						Integer currpage=null;
						Integer countpage=null;
						int pagesize=5;
						Integer countstep=null;
						Integer currstep=null;
						int pagestep=5;
						countpage=PagingUtil.getCountpage(user_blog.size(), pagesize);
						countstep=PagingUtil.getCountstep(user_blog.size(), pagesize, pagestep);
						currpage=PagingUtil.getCurrentpage(curr_page, countpage);
						currstep=PagingUtil.getCurrentstep(curr_step, countstep); 
						//out.println("<div align='center'><table border='1'><tr><td>按时间</td><td>按类别</td></table></div>");
						out.println("<div id='subject_list'>");
						out.println("<table border='0' width='580' cellspacing='0' cellpadding='4'>");
						//out.println("<tr style='font-size:18px;'><td align='center'>文章分类</td><td width='400'>文章标题</td><td width='80'>文章操作</td></tr>");
						//for(int i=0;i<user_blog.size();i++)
						for (int i = (currpage-1)*pagesize;i < currpage*pagesize; i++) 
						{
							if(i<user_blog.size())
							{
								int blog_id=user_blog.get(i).getBlogid();
								String blog_subject=user_blog.get(i).getBlogsubject();
								Date blog_date =user_blog.get(i).getBlogdate();
								 
								if(blog_subject.length()>20)
								{
									 blog_subject=blog_subject.substring(0,15);
								}
								String blog_class=user_blog.get(i).getBlogclass();
								out.println("<tr><td align='left' width='80' style='font-size:12px'>【"+blog_class+"】</td>"
										   +"<td align='left' width='300' style='font-size:12px'><a href='ViewBlog?blog_id="+blog_id+"&mark="
										   +Constants.BLOG_FOR_USER+"'>" +blog_subject+"</a>["+new SimpleDateFormat("yyyy-MM-dd").format(blog_date)+"]</td>" 
										   +"<td align='right'><span><a href='blog_edit.jsp?blog_id="+blog_id
										   +"&mark="+Constants.BLOG_FOR_USER+"' style='font-size:12px'>编辑</a><a href='DeleteBlog?blog_id="
										   +blog_id+"&mark="+Constants.BLOG_FOR_USER+"&blog_mark="+Constants.DELETE_BLOG_FROM_BLOG_LIST+"&page="
										   +currpage+"&step="+currstep+"' style='font-size:12px'>删除</a></span></td></tr>");
									  
							}
						}
						out.println("</table>");
						out.println("</div>");
						out.println("<div align='center' id='pages'>");
						if(countpage>pagestep)
						{
							out.println("<a href='blog_list.jsp?page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
							for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
							{
								out.println("<a href='blog_list.jsp?page="+k+"&step="+currstep+"'>"+k+"</a>");
							}
							int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
							out.println("<a href='blog_list.jsp?page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
						}else{
							if(countpage>1)
							{
								for(int k=1;k<=countpage;k++)
								{
									out.println("<a href='blog_list.jsp?page="+k+"'>"+k+"</a>");
								}
							}
						}
						out.println("</div>");	
					}else{
						out.println("<div align='center'><a  class='success_style'>你还没有发表博客!!!</a><a href='blog_add.jsp' class='continue_style'>写博客</a></div>");
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

