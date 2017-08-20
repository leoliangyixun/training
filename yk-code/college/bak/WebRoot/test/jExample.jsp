<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'JExample.jsp' starting page</title>
    
	
  </head>
  
  <body>
  <%! public class Caculate
   {
	   public double sum(double a,double b)
	   {return a+b;}
	   public double dec(double a,double b) 
	   {return a-b;}
	   public double che(double a,double b) 
	   {return a*b;}   
   }
   %>
  <%
     String s1=request.getParameter("num1") ;
     String s2=request.getParameter("num2") ;
    Double s11,s22;
    if(s1!=null&&s2!=null)
    {	
    	s11=Double.parseDouble(s1);
    	s22=Double.parseDouble(s2);
    }
    else
    {
    	s11=0.0;
    	s22=0.0;
    }
    Caculate d=new Caculate();
   %>
   
  
    <hr>
    <center>
    <form action=""  method="post" name="Form1">
   	 请输入第一个数：<input type="text" name="num1"/><br>
   	 请输入第二个数：<input type="text" name="num2"/><br>
    <input type="submit" value="计算"/><br>
    </form>
    </center>
    <hr>
    <center>
      	二个数的和为：<%=d.sum(s11,s22)%><br>
      	二个数的差为：<%=d.dec(s11,s22)%><br>
      	二个数的积为：<%=d.che(s11,s22)%><br>
    </center>  
    <hr>
  </body>
</html>
