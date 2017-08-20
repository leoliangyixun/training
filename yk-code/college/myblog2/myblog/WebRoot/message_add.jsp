<%@page import="java.net.URLDecoder"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yangkai.myblog.tools.EncodingTool"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>枫雅博客:添加留言</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/myblog.js"></script>
<style type="text/css">
#regist {
	width:620px;
	margin:20px auto;
}
#legend{
	margin:10px auto;width: 500px; font-size:14px;
	/*border: #000000 solid 1px;*/ 
}
#legend tr {
	text-align:left;
}

</style>
</head>

<body>
<%
String loginuser=(String)session.getAttribute("loginuser");
String username=URLDecoder.decode(request.getParameter("username"),"UTF-8");
%>
<div>
<div id="nav"><jsp:include page="include/message_logo.jsp" flush="true"></jsp:include></div>
<div id="regist">
  <!--div style="font-size:24px;" align="center">我要留言</div-->
  <div id="list_top">
    <div id="list_middle">
      <div id="list_bottom">
        <div>
          <fieldset id="legend">
            <legend style="font-size:14px">你正在给<a style="color:red;"><%=username%></a>留言</legend>
             <div align="center" style="margin-top:20px">
              <form action="AddMessage" method="post" name="Form">
                <table width="450" border="0">
                  <tr>
                    <td width="80">留言人：</td>
                    <td><%if(loginuser==null){%><input type="text" name="guest"/><%}else{out.println(loginuser);}%></td>
                  </tr>
                  <tr>
                    <td>留言内容：</td>
                    <td><textarea name="message_content" cols="40" rows="10"></textarea></td>
                  </tr>
                    <td>
                    	<input type="hidden" name="username" value="<%=username%>">
                    	<input type="hidden" name="message_date" value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%>">
                    </td>
                    <td>
                      <input type="submit" value="添加留言" />
                      <input type="reset" value="清除" />
                    </td>
                  </tr>
                </table>
              </form>
            </div>
          </fieldset>
        </div>
      </div>
    </div>
  </div>
  
</div>
	<jsp:include page="include/bottom.html" flush="true"></jsp:include>
</div>
</body>
</html>
