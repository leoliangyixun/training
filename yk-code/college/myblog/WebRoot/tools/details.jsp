<%@page import="com.yangkai.myblog.domain.User"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	List<String> friends=(List<String>)session.getAttribute("friends");
	Map<String,User> friends_info=(Map<String,User>)session.getAttribute("friends_info");
	int index=Integer.parseInt(request.getParameter("index"));
	out.println("<div>");
	out.println("<a>兴趣爱好：</a><br>");
	out.println("<a>注册时间：</a><br>");
	out.println("</div>");
%>

