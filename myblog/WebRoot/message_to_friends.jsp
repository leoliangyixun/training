<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.domain.MessageReply"%>
<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="com.yangkai.myblog.domain.Message"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>留言</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>
<div>
<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
<div>
	<ul>
	      <li><a href="friends_blog.jsp">好友动态</a></li>
		<li><a href="user_blog.jsp">我的博客</a></li>			
		<li><a href="user_album.jsp">我的相册</a></li>
		<li><a href="user_mood.jsp">我的说说</a></li>
		<li  class="current_page_item"><a href="user_message.jsp">留言板</a></li>
		<li><a href="user_home.jsp">个人中心</a></li>
	</ul>
</div>
</div>
</div>
<div id="list">
<div id="list_top">
	<div id="list_middle">
		<div id="list_bottom">
			<div id="blog_list">
				<div id="link_list_style">
				    <a href="user_message.jsp">给我的留言</a>
					<a href="my_leave_message.jsp">我写的留言</a>
					<a href="message_to_friends.jsp" style="background-color:#E97B56;">给好友留言</a>
					<a href="message_list.jsp">留言管理</a>
				</div>
				<div style="margin-top:20px;margin-left:10px;">
					<%	
						String loginuser=(String) session.getAttribute("loginuser"); 
						List<String> friends=(List<String>)session.getAttribute("friends");
					    if(friends!=null && friends.size()>0){
				    %>	
	              <form action="AddMessage" method="post" name="messageForm">
	                <table width="550" border="0">
	                 <tr>
	                    <td width="80" height="80" id="friend_image"><img src="upload/default.jpg" width="80" height="80"/></td>
	                    <td valign="top" >
	                    	<select name="username" onchange="showFriendImage(this.value)">
			       				 <option value="default">==选择好友==</option>
								<%
									for(int i=0;i<friends.size();i++)
									{
										out.println("<option value="+friends.get(i)+">"+friends.get(i)+"</option>");
									}
								%>
						      </select>
	                    </td>
	                  </tr>
	                  <tr>
	                    <td>留言内容：</td>
	                    <td><textarea name="message_content" cols="50" rows="10"></textarea></td>
	                  </tr>
	                   <tr>
	                    <td>
	                    	<input type="hidden" name="guest" value="<%=loginuser%>">
	                    	<input type="hidden" name="message_date" value="<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%>">
	                    </td>
	                    <td align="center" >
	                      <input type="submit" value="添加留言" />
	                      <input type="reset" value="清除" />
	                    </td>
	                  </tr>
	                </table>
	              </form>
	              <%}else{ out.println("<div align='center'><a class='success_style'>你还没有好友!!!</a></div>");  } %>
	            </div>
			</div>
		</div>
	</div>
</div>
</div>
<div style="clear:both"></div>
</div>
<jsp:include page="include/bottom.html" flush="true"></jsp:include>
</div>
</body>
</html>