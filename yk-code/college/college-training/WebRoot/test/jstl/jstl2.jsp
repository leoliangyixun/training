<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'jstl2.jsp' starting page</title>

  </head>
  
  <body>
   <c:out value="${page}"></c:out><br>
   <c:out value="${request}"></c:out><br>
   <c:out value="${session}"></c:out><br>
   <c:out value="${application}"></c:out><br>
  </body>
</html>
