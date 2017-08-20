<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<%@page import="java.sql.ResultSet"%>
<jsp:useBean id="d" class="com.yangkai.bean.AjaxFriendsList" scope="session"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>My JSP 'selectlist.jsp' starting page</title>
  </head>
  
  <body>
  <form action="selectlistresult.jsp" method="get">
    <select name="userclass">
      <option value="Default">--通讯录类别--</option>
     <%
       ResultSet class_rs=null;
       String classstr="SELECT DISTINCT(userclass) FROM calldetails";
       class_rs=d.myexecuteQuery(classstr);
       while(class_rs.next())
       {
       		out.print("<option value="+class_rs.getString(1).toString()+">"+class_rs.getString(1).toString()+"</option>");  		
       }
       class_rs.close();
      %>
      </select>
      <input type="submit" value="提交"> 
  </form>
  </body>
</html>
