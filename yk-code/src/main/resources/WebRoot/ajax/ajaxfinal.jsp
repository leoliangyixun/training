<%@page import="java.net.URLDecoder"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="GB2312"%>
<jsp:useBean id="d" class="com.yangkai.bean.AjaxFriendsList" scope="session"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=GB2312" />
    <title>好友通讯录</title>
    <script type="text/javascript" language="javascript">
    <!--
    var xmlhttp=null;
    function createXMLHttpRequest()
    {
       var objxmlhttp=null;
       var xmlhttpTypes=["Microsoft.XMLHTTP","Msxml2.XMLHTTP","Msxml2.XMLHTTP.3.0","Msxml2.XMLHTTP.4.0","Msxml2.XMLHTTP.5.0","Msxml2.XMLHTTP.6.0"];
       if(window.ActiveXObject)
       {
       	for(var i=0;i<xmlhttpTypes.length;i++)
           {
       	    	try{
		       			objxmlhttp=new ActiveXObject(xmlhttpTypes[i]);
		       			break;
       	           }
       	    	catch(e){continue;}
       	   }
       }
       else if(window.XMLHttpRequest)
       {
       		objxmlhttp=new XMLHttpRequest();
       }
       return objxmlhttp;
     }
     function sendHttpRequest(userclass)
     {
   		xmlhttp=createXMLHttpRequest();
       	if(xmlhttp==null)
       	{
       		alert("你的浏览器不支持Ajax!!!");
       	}
       	else{
   				xmlhttp.onreadystatechange=getHttpResponse;
       			var url="ajaxfinalresult.jsp?userclass="+encodeURI(encodeURI(userclass));
       			xmlhttp.open("GET",url,true);
       			xmlhttp.send();
       		}
    }
    function getHttpResponse()
    {	
    	if(xmlhttp.readyState==4)
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
    <td colspan="2" align="center">好友通讯录</td>
  </tr>
  
  <tr>
    <td width="247" align="center">姓名</td>
    <td width="247" align="center">电话/QQ/E-mail</td>
  </tr>
</table>
</div>
<div>
<table border="1" align="center" width="500" cellspacing="0" cellpadding="0" bordercolor="#000000" >
  <tr>
    <td colspan="2" align="center">好友管理</td>
  </tr>
  <tr>
    <td width="247">
    <form method="post" action="">
      <input name="username" type="text" value="填写姓名"/>
      <input type="submit" name="button" value="查询" />
    </form>
    </td>
    <td width="247"> 
    <form method="post" action="">
      <input name="delete" type="text" value="填写姓名" />
      <input type="submit" name="button2" value="删除" />
    </form>
    </td>
  </tr>
  <tr id="nav_second">
    <td align="left" valign="middle">
    <form method="post" action="">
      <input name="add" type="text" value="填写姓名" />
      <input name="tel" type="text" value="填写联系方式" />
	  <input type="submit" name="button3" value="添加" />
    </form>
    </td>
    <td align="left" valign="middle">
     <form method="post" name="classForm" action="">
     
      <select name="userclass" onchange="sendHttpRequest(this.value)">
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
      <a id="content">
     <select name="username">
       <option value="Default">--选择姓名--</option>
     </select>
     </a><br />
       <input name="tel" type="text" value="填写联系方式" />
      <input type="submit" value="修改"/>
    </form>
</td>
  </tr>
</table>
</div>
</body>
</html>


