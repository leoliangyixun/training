<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@page import="com.yangkai.myblog.domain.User"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yangkai.myblog.tools.EncoderUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>枫雅博客:添加好友</title>
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
<div>
<div id="nav"><jsp:include page="include/friend_add_logo.jsp" flush="true"></jsp:include></div>
<div id="regist">
  <div id="list_top">
    <div id="list_middle">
      <div id="list_bottom">
        <div>
	       <%
			   	String username=URLDecoder.decode(request.getParameter("username"), "UTF-8");
			       //String username=new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
			   	//System.out.println(username);
			   	boolean isFriend=Boolean.parseBoolean(request.getParameter("isFriend"));
			   	//System.out.println(isFriend);
				if(isFriend==Constants.isFriend){
					out.println("<div align='center'><a style='color:red;'>"+username+"</a>已经是你的好友！！！<a href='javascript:history.back()' style='margin-left:10px'>返回</a></div>");
				}else{
					User toFriend=(User)session.getAttribute("toFriend");
					//User user=(User)request.getAttribute("user");
	       %>
          <fieldset id="legend">
            <legend style="font-size:14px">你正在给<a style="color:red;"><%=username%></a>发送好友请求</legend>
             <div align="center" style="margin-top:20px">
              <form action="AddFriend" method="post" name="friendRequestForm">
                <table width="450" border="0" cellspacing='0' cellpadding='4'>
                  <tr>
                    <td width="110" rowspan="3" align="left" valign="top" style="font-size:12px">
                    	<img src="upload/相册/<%=username%>/image/me.jpg" width="110" height="110"/></br>
                    	<a style="color:red">To:<%=username%></a></br>
                    	<a>性别：<%=toFriend.getSex() %></a></br>
                    	<a>生日：<%=toFriend.getBirthday() %></a></br>
                    	<a>所在地：<%=toFriend.getAddress() %></a></br>
                    </td>
                    <td>请输入好友请求消息：</td>
                  </tr>  
                  <tr>
                    <td><textarea name="request_content" cols="35" rows="5"></textarea></td>
                  </tr>
                  <tr>
                    <td align="left">
                    <input type="hidden" name="username" value="<%=username%>">
                      <input type="hidden" name="request_date" value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%>">
                      <input type="submit" value="发送请求"/>
                      <input type="reset" value="清除"/>
                    </td>
                  </tr>
                </table>
              </form>
            </div>
          </fieldset>
         <%} %>
        </div>
      </div>
    </div>
  </div>
  
</div>
	<jsp:include page="include/bottom.html" flush="true"></jsp:include>
</div>
</body>
</html>
