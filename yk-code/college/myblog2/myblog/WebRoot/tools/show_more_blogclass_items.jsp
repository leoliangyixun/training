<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
	List<String> user_blog_class=(ArrayList<String>)session.getAttribute("user_blog_class");
	//out.println("<form action='SearchBlogByClass' method='get' name='moreBlogClassItems'>");
	out.println("<select name='link_blog_class' onchange='document.moreBlogClassItems.submit()'>");
	out.println("<option>==�������==</option>");
	for(int i=0;i<user_blog_class.size();i++)
	{
		out.println("<option value="+user_blog_class.get(i)+">"+user_blog_class.get(i)+"</option>");
	}
	out.println("</select>");
	//out.println("<input type='submit' value='�ύ'/>");
	//out.println("<form>");
%>
