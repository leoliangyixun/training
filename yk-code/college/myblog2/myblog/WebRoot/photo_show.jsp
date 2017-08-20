<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>‰Ø¿¿’’∆¨</title>
  </head>
  
  <body>
   <%
	String loginuser = (String) session.getAttribute("loginuser");
    String album_name=request.getParameter("album_name");
	String photo=request.getParameter("photo");
	if(loginuser!=null && album_name!=null && photo!=null)
	{
		album_name=URLDecoder.decode(album_name,"UTF-8");
		out.println("<div align='center'>");
		out.println("<img src='upload/œ‡≤·/"+loginuser+"/"+album_name+"/"+photo+"'/>");
		out.println("</div>");
		out.println("<div align='center'><a href='javascript:history.back()'>∑µªÿ</a></div>");
	}
   %>
  </body>
</html>