<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>My JSP 'equaltest.jsp' starting page</title>
  </head>
 
  <body>
    <form action="equaltest.jsp" method="get">
    <input type="text" name="name"/>
    <input type="text" name="num"/>
    <input type="submit"/> 
    </form>
    <%
    String name=request.getParameter("name");
    String num=request.getParameter("num");
    if(name!=null && !name.equals(""))
    {
    	if(num!=null && !num.equals(""))
    	{
    		out.println("shit");
    	}
    }
    
    /*
    else{
  		 if(!name.equals(""))
    	 {out.println("shit");}
    }
    */
    %>
  </body>
</html>
