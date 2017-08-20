<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>写博客</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>
<%List<String> user_blog_class=(List<String>)session.getAttribute("user_blog_class");//获取用户的博文分类列表。%>
<div>
<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
<div>
	<ul>
		 <li><a href="user_blog.jsp">我的博客</a></li>
		 <li class="current_page_item"><a href="blog_add.jsp">写博客</a></li>
		 <li><a href="blog_private.jsp">私密日志</a></li>	
		 <li><a href="blog_draft.jsp">草稿箱</a></li>	
		 <li><a href="blog_list.jsp">博文管理</a></li>		
	</ul>
</div>
</div>
</div>
<div id="list">
<div id="list_top">
	<div id="list_middle">
		<div id="list_bottom">
			<div id="blog_list">
			<div id="blog_style">
			<form name="addblogForm" method="post" action="AddBlog">
			  <table width="570" border="0">
			    <tr>
			      <td width="95">文章标题：</td>
			      <td colspan="3"><input type="text" name="blog_subject" size="40"/></td>
			    </tr>
			    <tr>
			      <td>&nbsp;</td>
			      <td colspan="3"><textarea name="blog_content" class="content" cols="50" rows="20"></textarea></td>
			    </tr>
			    <tr>
			      <td>文章类别：</td>
			      <td width="160">
			      <select name="blog_class" id="blog_class">
			        <option value="default">==选择文章类别==</option>
			        	<%
			        	if(user_blog_class!=null && user_blog_class.size()>0)
			        	{
							for(int i=0;i<user_blog_class.size();i++)
							{
								out.println("<option value="+user_blog_class.get(i)+">"+user_blog_class.get(i)+"</option>");
							}
			        	}
				        %>
			      </select>
			      </td>
			      <td width="95"><a href='javascript:showText()'>新建分类</a></td>
			      <td width="211"><a id='create_class'></a></td>
			    </tr>
			    <tr>
			      <td colspan="4">
				      <input type="submit" value="发表" style="font-size: 24px;width: 110px;height:40px"/>
				      <input type="reset" value="取消" style="font-size: 24px;width: 80px;height:40px"/>
				      <a href="javascript:addDraftBlog()" style="margin-left:50px;">保存为草稿</a>
				      <a href="javascript:addPrivateBlog()" style="margin-left:10px;">保存为私密日志</a>
			      	  <input type="hidden" name="blog_date" value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%>"/>
			      </td>
			    </tr>
			  </table>
			</form>
			</div><!--end of blog_style-->
			</div><!--end of blog_list-->
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

