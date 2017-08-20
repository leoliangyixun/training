<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'ListBoxValue.jsp' starting page</title>
    <script type="text/javascript" language="javascript">
    <!--
    function getValue()
    {
    	var user=document.Myform.user.value;
    	alert(user);
    }
    function getValue1(name)
    {
    	alert(name);
    }
    -->
    </script>
  </head>
  
  <body>
    <form method="post" name="Myform" action="ListBoxValue.jsp">
    <select name="user" onchange="getValue()">
	    <option value="no" selected="selected">--请选择--</option>
	    <option value="季晴川">季晴川</option>
	    <option value="梁以薰">梁以薰</option>
    </select>
    <select name="name" onchange="getValue1(this.value)">
	    <option value="no" selected="selected">--请选择--</option>
	    <option value="季晴川">季晴川</option>
	    <option value="梁以薰">梁以薰</option>
    </select>
</form>
<% %>
  </body>
</html>
