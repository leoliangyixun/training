<%@page import="java.net.URLDecoder"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<jsp:useBean id="d" class="com.yangkai.bean.AjaxFriendsList" scope="session"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>����ͨѶ¼</title>
    <script type="text/javascript" src="../js/AjaxExample.js"></script>
    <script type="text/javascript" language="javascript">
    <!--
    function getHttpResponse()
    {	
    	if(xmlhttp.readyState==4 )
    	{
			if(xmlhttp.status==200 || xmlhttp.status==0)
			{
				document.getElementById("content").innerHTML=xmlhttp.responseText;
			}
    	}		
    }
    -->
    </script>
<style type="text/css">
#nav_second{}
#nav_second input{ margin-bottom:10px; margin-top:10px;}
#nav_second select{ margin-bottom:10px; margin-top:10px;}
#table_marign{margin-bottom:20px;}
</style>
	

  </head>
  
  <body>
  <div id="table_marign">
<table border="1" align="center" width="500" cellspacing="0" cellpadding="0" bordercolor="#000000" >
  <tr>
    <td colspan="2" align="center">����ͨѶ¼</td>
  </tr>
  
  <tr>
    <td width="247" align="center">����</td>
    <td width="247" align="center">�绰/QQ/E-mail</td>
  </tr>
</table>
</div>
<div>
<table border="1" align="center" width="500" cellspacing="0" cellpadding="0" bordercolor="#000000" >
  <tr>
    <td colspan="2" align="center">���ѹ���</td>
  </tr>
  <tr>
    <td width="247">
    <form method="post" action="">
      <input name="username" type="text" value="��д����"/>
      <input type="submit" name="button" value="��ѯ" />
    </form>
    </td>
    <td width="247"> 
    <form method="post" action="">
      <input name="delete" type="text" value="��д����" />
      <input type="submit" name="button2" value="ɾ��" />
    </form>
    </td>
  </tr>
  <tr id="nav_second">
    <td align="left" valign="middle">
    <form method="post" action="">
      <input name="add" type="text" value="��д����" />
      <input name="tel" type="text" value="��д��ϵ��ʽ" />
	  <input type="submit" name="button3" value="���" />
    </form>
    </td>
    <td align="left" valign="middle">
     <form method="post" name="classForm" action="">
     
      <select name="userclass" onchange="sendHttpRequest(this.value)">
      <option value="null">--ͨѶ¼���--</option>
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
      <a id="content">
     <select name="username">
       <option value="null">--ѡ������--</option>
       <%--
       ResultSet username_rs=null;
       String usernamestr=null;
       usernamestr="SELECT name FROM calldetails";
       username_rs=d.myexecuteQuery(usernamestr);
       while(username_rs.next())
       {
       		out.print("<option value="+username_rs.getString(1).toString()+">"+username_rs.getString(1).toString()+"</option>");  		
       }
       username_rs.close();
       --%>
       <%--
       ResultSet username_rs=null;
       String usernamestr=null;
       String userclass=request.getParameter("userclass");
       if(userclass=="null" || userclass==null)
       {
    	    //out.println("<script language='javascript'>alert('Are you kidding me!!!')</script>");
    	    //usernamestr="SELECT name FROM calldetails";
    	    out.println("");
       }
       else{
            userclass=URLDecoder.decode(userclass, "UTF-8");
          //userclass=URLDecoder.decode(userclass, "gb2312");
          	usernamestr="SELECT name FROM calldetails WHERE userclass='"+userclass+"'";    
            username_rs=d.myexecuteQuery(usernamestr); 
	       while(username_rs.next())
	       {
	       	out.print("<option value="+username_rs.getString(1).toString()+">"+username_rs.getString(1).toString()+"</option>");  		
	       }
	       username_rs.close();    
       }
       
      --%>
     </select></a><br />
       <input name="tel" type="text" value="��д��ϵ��ʽ" />
      <input type="submit" value="�޸�"/>
    </form>
</td>
  </tr>
</table>
</div>
</body>
</html>


