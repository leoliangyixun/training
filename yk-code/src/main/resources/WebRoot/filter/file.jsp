<%@page import="com.yangkai.bean.FileBean"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'file.jsp' starting page</title>
  </head>
  
  <body>
   <%
   Vector v=new Vector();
   String path=request.getParameter("path");
   String type=request.getParameter("type");
   path=URLDecoder.decode(path,"UTF-8");
   FileBean fb=new FileBean();
   v=fb.showfiles(path, type);
   for(int i=0;i<v.size();i++)
   {
	   out.println(v.get(i).toString());
   }
   %>
  </body>
</html>
