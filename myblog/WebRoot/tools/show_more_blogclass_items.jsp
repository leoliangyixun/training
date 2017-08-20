<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	List<String> user_blog_class=(ArrayList<String>)session.getAttribute("user_blog_class");
	//out.println("<form action='SearchBlogByClass' method='get' name='moreBlogClassItems'>");
	out.println("<select name='link_blog_class' onchange='document.moreBlogClassItems.submit()'>");
	out.println("<option>==博文类别==</option>");
	for(int i=0;i<user_blog_class.size();i++)
	{
		out.println("<option value="+user_blog_class.get(i)+">"+user_blog_class.get(i)+"</option>");
	}
	out.println("</select>");
	//out.println("<form>");
%>
