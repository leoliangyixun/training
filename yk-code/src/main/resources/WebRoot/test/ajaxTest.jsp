<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <script type="text/javascript">
          function to(){
              var name=document.getElementById("username").value;
              var url="dbop.jsp?username="+encodeURI(encodeURI(name)); //��������
              var xmlHttpRequest=new ActiveXObject("microsoft.xmlhttp");
                    xmlHttpRequest.open("post",url,true);
                    xmlHttpRequest.send(null);
                    xmlHttpRequest.onreadystatechange=function(){
	                    if(xmlHttpRequest.readystate==4 && xmlHttpRequest.status==200){
	                    ajax.innerHTML = xmlHttpRequest.responseText;
	                    }
                 } 
          }
      </script>
  </head>  
  <body><div id="ajax"></div>
    <form name="name1"> 
        ������ע��ID��<input id="username" type="text" name="username" value=""/><br>
        <input type="button" value="ע��" onclick="to()">    
    </form>
  </body>
</html>