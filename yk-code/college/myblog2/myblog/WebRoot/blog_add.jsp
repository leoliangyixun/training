<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>д����</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>
<%List<String> user_blog_class=(List<String>)session.getAttribute("user_blog_class");//��ȡ�û��Ĳ��ķ����б�%>
<div>
<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
<div>
	<ul>
		 <li><a href="user_blog.jsp">�ҵĲ���</a></li>
		 <li class="current_page_item"><a href="blog_add.jsp">д����</a></li>
		 <li><a href="blog_private.jsp">˽����־</a></li>	
		 <li><a href="blog_draft.jsp">�ݸ���</a></li>	
		 <li><a href="blog_list.jsp">���Ĺ���</a></li>		
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
			      <td width="95">���±��⣺</td>
			      <td colspan="3"><input type="text" name="blog_subject" size="40"/></td>
			    </tr>
			    <tr>
			      <td>&nbsp;</td>
			      <td colspan="3"><textarea name="blog_content" class="content" cols="50" rows="20"></textarea></td>
			    </tr>
			    <tr>
			      <td>�������</td>
			      <td width="160">
			      <select name="blog_class" id="blog_class">
			        <option value="default">==ѡ���������==</option>
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
			      <td width="95"><a href='javascript:showText()'>�½�����</a></td>
			      <td width="211"><a id='create_class'></a></td>
			    </tr>
			    <tr>
			      <td colspan="4">
				      <input type="submit" value="����" style="font-size: 24px;width: 110px;height:40px"/>
				      <input type="reset" value="ȡ��" style="font-size: 24px;width: 80px;height:40px"/>
				      <a href="javascript:addDraftBlog()" style="margin-left:50px;">����Ϊ�ݸ�</a>
				      <a href="javascript:addPrivateBlog()" style="margin-left:10px;">����Ϊ˽����־</a>
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

