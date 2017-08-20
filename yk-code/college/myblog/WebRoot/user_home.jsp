<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yangkai.myblog.tools.CalculateNumUtil"%>
<%@page import="com.yangkai.myblog.domain.Album"%>
<%@page import="com.yangkai.myblog.domain.Mood"%>
<%@page import="com.yangkai.myblog.domain.Blog"%>
<%@page import="com.yangkai.myblog.domain.User"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>个人中心</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	<script type="text/javascript"  language="javascript">
	<!--
		getMyBlog(null,null);	
	-->
	</script>
	</head>

<body>
<div>
<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
	<div>
		<ul>
		<li class="current_page_item"><a href="user_home.jsp">我的主页</a></li>
		<li><a href="user_friends.jsp">好友管理</a></li>	
		<li><a href="user_contacts.jsp">通讯录</a></li>
		<li><a href="user_setting.jsp">博客设置</a></li>
		</ul>
	</div>

</div>
</div>
<div id="list">
<div id="list_top">
	<div id="list_middle">
		<div id="list_bottom">
			<div id="blog_list">
				<!--div id="link_list_style">
					<a href="" style="background-color:#E97B56;">我的主页</a>
					<a href="" >博客信息</a>
				</div-->
				<%
					User bloger=(User)session.getAttribute("bloger");
					List<Blog> user_blog = (List<Blog>) session.getAttribute("user_blog");
					List<Mood> user_mood=(List<Mood>)session.getAttribute("user_mood");
					List<Album> user_album=(List<Album>)session.getAttribute("user_album");
					int blog_num=CalculateNumUtil.getBlogNum(user_blog);
					int mood_num=CalculateNumUtil.getMoodNum(user_mood);
					int album_num=CalculateNumUtil.getAlbumNum(user_album);
				%>
				
				<!--div style="margin-left:7px; border:#E97B56 solid 1px;">
				 <table width="570" border="0" >
				  <tr>
				    <td colspan="3" rowspan="9" width="150" height="200" align="left" valign="top"><img src="upload/相册/<%=bloger.getUsername() %>/image/me.jpg" width="160" height="210"/></td>
				    <td colspan="2" align="left" valign="top" height="30" class="user_about"><a><%=bloger.getUsername() %></a></td>
				  </tr>
				  <tr class="user_info" >
				    <td align="left" valign="top" width="80">真实姓名：</td>
				    <td><%=bloger.getName() %></td>
				  </tr>
				  <tr class="user_info" >
				    <td align="left" valign="top" >年龄：</td>
				    <td><%=bloger.getAge() %></td>
				  </tr>
				  <tr class="user_info" >
				    <td align="left" valign="top" >性别：</td>
				    <td><%=bloger.getSex() %></td>
				  </tr>
				  <tr class="user_info" >
				    <td align="left" valign="top" >所在地：</td>
				    <td><%=bloger.getAddress() %></td>
				  </tr>
				  <tr class="user_info" >
				    <td align="left" valign="top" >出身日期：</td>
				    <td><%=bloger.getBirthday() %></td>
				  </tr>
				  <tr class="user_info" >
				    <td align="left" valign="top" >兴趣爱好：</td>
				    <td><%=bloger.getInterest()%></td>
				  </tr>
				  <tr class="user_info" >
				    <td align="left" valign="top" >E-mail：</td>
				    <td><%=bloger.getMail() %></td>
				  </tr>
				  <tr class="user_info" >
				    <td align="left" valign="top" >博客名：</td>
				    <td><%=bloger.getBlogname() %></td>
				  </tr>
				  <tr class="user_info" >
				    <td rowspan="2"  width="50" height="40" align="center" valign="middle" class="num"><a href="user_blog.jsp"><%=blog_num %><br/>博客</a></td>
				    <td rowspan="2"  width="50" height="40" align="center" valign="middle" class="num"><a href="user_album.jsp"><%=album_num %><br/>相册</a></td>
				    <td rowspan="2"  width="50" height="40" align="center" valign="middle" class="num"><a href="user_mood.jsp"><%=mood_num %><br/>说说</a></td>
				    <td align="left" valign="top" >博客logo：</td>
				    <td><%=bloger.getBloglogo() %></td>
				  </tr>
				  <tr class="user_info" >
				    <td align="left" valign="top" class="user_info">注册时间：</td>
				    <td><%=bloger.getRegisttime() %></td>
				  </tr>
				  <tr>
				    <td colspan="3">&nbsp;</td>
				    <td colspan="2" class="latest_info"  height="50" align="left" valign="center">
		    			<a href="javascript:getMyBlog(null,null)" >我的博客</a>
			      		<a href="javascript:getMyAlbum(null,null)">我的相册</a>
			      		<a href="javascript:getMyMood(null,null)">我的说说</a>
			      		<a href="javascript:getMyMessage(null,null)">最新留言</a>
				    </td>
				   </tr>
				</table>
				</div-->
				
				<div style="margin-left:7px; border:#E97B56 solid 1px;">
					  <table width="570" border="0" >
					    <tr>
					      <td colspan="3" rowspan="2" width="150" height="200" align="left" valign="top"><img src="upload/相册/<%=bloger.getUsername() %>/image/me.jpg" width="150" height="200"/></td>
					      <td align="left" valign="top" height="30" class="user_about"><a><%=bloger.getUsername() %></a></td>
					    </tr>
					    <tr>
					      <td align="left" valign="top" class="user_info">
					      	<a>真实姓名：<%=bloger.getName() %></a><br/>
					      	<a>性别：<%=bloger.getSex() %></a><br/>
					      	<a>年龄：<%=bloger.getAge() %></a><br/>
					      	<a>居住地：<%=bloger.getAddress() %></a><br/>
					      	<a>出身日期：<%=bloger.getBirthday() %></a><br/>
					      	<a>兴趣爱好：<%=bloger.getInterest()%></a><br/>
					      	<a>博客名：<%=bloger.getBlogname() %></a><br/>
					      	<a>博客logo：<%=bloger.getBloglogo() %></a><br/>
					      	<a>E-mail：<%=bloger.getMail() %></a><br/>
					      	<a>注册时间：<%=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(bloger.getRegisttime()) %></a><br/>
					      </td>
					    </tr>
					    <tr>
					      <td width="50" height="40" align="center" valign="middle" class="num"><a href="user_blog.jsp"><%=blog_num %><br/>博客</a></td>
					      <td width="50" height="40" align="center" valign="middle" class="num"><a href="user_album.jsp"><%=album_num %><br/>相册</a></td>
					      <td width="50" height="40" align="center" valign="middle" class="num"><a href="user_mood.jsp"><%=mood_num %><br/>说说</a></td>
					      <td class="latest_info" >
					      		<a href="javascript:getMyBlog(null,null)" >我的博客</a>
					      		<a href="javascript:getMyAlbum(null,null)">我的相册</a>
					      		<a href="javascript:getMyMood(null,null)">我的说说</a>
					      		<a href="javascript:getMyMessage(null,null)">我的留言</a>
					      </td>
					    </tr>
					  </table>
				</div>
				<div id="my_dynamic" ></div>
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

