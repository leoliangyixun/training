<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>无刷新分页</title>
    <script type="text/javascript" language="javascript">
	<!--
	   var xmlhttp=null;
	   function createXMLHttpRequest()
	   {
	   	var objxmlhttp=null;
	   	var xmlhttpTypes=["Microsoft.XMLHTTP","Msxml2.XMLHTTP.3.0","Msxml2.XMLHTTP.4.0","Msxml2.XMLHTTP.5.0","Msxml2.XMLHTTP.6.0"];
	   	if(window.ActiveXObject)
	   		{
	   		for(var i=0;i<xmlhttpTypes.length;i++)
	   		{
	   			try{
	   					objxmlhttp=new ActiveXObject(xmlhttpTypes[i]);
	   					break;
	   			   }catch(e){continue;}
	   		}
	   	}
	   	else if(window.XMLHttpRequest)
	   	{
	   		objxmlhttp=new XMLHttpRequest();
	   	}
	   	return objxmlhttp;
	   }
	   function sendHttp()
	   {
			xmlhttp=createXMLHttpRequest();
	   	if(xmlhttp==null)
	   	{
	   		alert("你的浏览器不支持Ajax!!!");
	   	}
	   	else{
				     var bookname=document.getElementById("bookname").value;
	   		           xmlhttp.onreadystatechange=getHttp;
	   			     var url="ajaxmultipagesresult.jsp?bookname="+encodeURI(encodeURI(bookname));
	   			     xmlhttp.open("GET",url,true);
	   			     xmlhttp.send(null);
	   		}
	   }
	   function getHttp()
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
	

  </head>
  
  <body>
   <div align="center">
   <input type="text" name="bookname"  id="bookname" value="请输入图书信息"/>
   <input type="button" value="查询"  onclick="sendHttp()"/> 
   </div>
   <div id="content" align="center"></div>
  </body>
</html>
