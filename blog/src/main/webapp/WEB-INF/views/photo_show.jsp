<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>浏览照片</title>
  </head>
  
  <body>
   <%
	String username =URLDecoder.decode(request.getParameter("username"), "UTF-8");
    String album_name=request.getParameter("album_name");
	String photo_name=request.getParameter("photo_name");
	if(username!=null && album_name!=null && photo_name!=null)
	{
		album_name=URLDecoder.decode(album_name,"UTF-8");
		out.println("<div align='center'>");
		out.println("<img src='upload/相册/"+username+"/"+album_name+"/"+photo_name+"'/>");
		out.println("</div>");
		out.println("<div align='center'><a href='javascript:history.back()'>返回</a></div>");
	}
   %>
  </body>
</html>