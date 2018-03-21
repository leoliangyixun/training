<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="yk" uri="com.yangkai.tag"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'tag.jsp' starting page</title>
  </head>
  
  <body>

   ip=<yk:mytag></yk:mytag>
   <yk:view>
   	这是显示的内容
   </yk:view>
   <yk:loop>
   	这是重复显示的内容
   </yk:loop>
  </body>
</html>
