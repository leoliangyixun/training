<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>My JSP 'exam.jsp' starting page</title>
  </head>
  
  <body>
 <%java.util.Date date=new java.util.Date();%>
 当前时间为：
 <%=date.getYear()+1900%>年
 <%=date.getMonth()+1 %>月
 <%=date.getDate() %>日
 <%=date.getHours() %>时
 <%=date.getMinutes() %>分
 <%=date.getSeconds() %>秒
 <br>
 <hr>
 <%
 int time=date.getHours();
 String msg;
 switch (time){
	 case 8:
	 case 9:
	 case 10:
	 case 11:{msg="欢迎你上午访问本。";
		      break;}
	 case 12:{msg="中午好，欢迎你。";
		 	  break;}
	 case 13:
	 case 14:
	 case 15:
	 case 16:
	 case 17:
	 case 18:{msg="欢迎你下午访问本网。";
	         break;}
	 case 19:
	 case 20:
	 case 21:
	 case 22:{msg="晚上好，注意休息哦！";
		      break;}
	 case 23:
	 case 24:{msg="非常感谢你这么晚了还在关注我们。";
	          break;}
	 default:{msg="还没休息 ，请注意身体哟。";}
 }
 %>
 <%out.print(msg);%>
 <hr>
  </body>
</html>
