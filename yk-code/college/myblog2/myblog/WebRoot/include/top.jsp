<%@page import="com.yangkai.myblog.domain.User"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<style type="text/css">
#submit{ 
	width: 51px;
	height: 20px;
	border: none;
	/*background: none;*/
	text-indent: -99999px; 
	background-image:url(css/images/submit.gif);
} 
#reset{ 
	width: 51px;
	height: 20px;
	border: none;
	text-indent: -99999px; 
	background-image:url(css/images/reset.gif);
}
#register{ 
	width: 51px;
	height: 20px;
	border: none;
	text-indent: -99999px; 
	background-image:url(css/images/register.gif);
}
#login input{
	margin-right: 5px;
}
#welcome {
	margin-top: 20px;
} 

#welcome a{
	margin-right: 5px;
	font-size: 12px;
	color: black;
}
.input{
	font-family: "����";
	font-size: 9pt;
	background-color:#DFD4A7;
	border: 1px solid #D1B681;
}  
</style>
<%
	//String loginuser=(String)session.getAttribute("loginuser"); 
	User bloger=(User)session.getAttribute("bloger");
%>
<div id="top_wrapper">
	<div id="top_header">
		<div id="top_logo">
		<%
		if(bloger==null) 
		{%>
		<h1><a>���Ų���</a></h1>
        <p><a>���Ų��ͣ���������ң���¼����ľ���</a></p>
		<%}else{
			String blog_name=null;
			String blog_logo=null;
			if(bloger.getBlogname()==null){
				blog_name="���Ų���";
			}else{
				blog_name=bloger.getBlogname();
			}
			if(bloger.getBloglogo()==null){
				blog_logo="���Ų��ͣ���������ң���¼����ľ���";
			}else{
				blog_logo=bloger.getBloglogo();
			}
			out.println("<h1><a>"+blog_name+"</a></h1>");
			out.println("<p><a>"+blog_logo+"</a></p>");
		}
		%>
		</div>
		<div id="top_search">
		<%if(bloger==null){%>
            <div align="center" id="login">
		 	<form action="LoginCheck" method="post">
	         <table>
	         <tr><td> �û���:</td><td> <input type="text" name="username" size="29" class="input"/></td></tr>
	         <tr><td> ��&nbsp;&nbsp;��:</td><td> <input type="text" name="password" size="29" class="input"/></td></tr>
	         <tr><td><input type="hidden" name="login_mark" value="<%=Constants.BLOG_LOGIN_MARK_FROM_INDEX%>"/></td>
	         <td>
	             <input type="submit" value="��½" id="submit"/> 
		         <input type="reset" value="ȡ��" id="reset"/> 
		         <a href="regist/regist.jsp">ע�����û�</a>
		         <!--input type="button" value="ע��" id="register" onclick="location.href='regist.jsp'"/-->
	         </td></tr>
	         </table>
	         <div id="fail"></div>
   	      </form>
   	      </div>
   	      <%}else{%>
	      <div id="welcome"><span>
	      <table border="0">
	      <tr>
		      <td><img src="upload/���/<%=bloger.getUsername()%>/image/me.jpg" width="30" height="30""/></td>
		      <td valign="top"><a href="user_setting.jsp" style="margin-left:5px"><%=bloger.getUsername()%></a><a href="LoginOut">[�˳�]</a></td>
	      </tr>
	      </table><span></div>
		<%}%> 
	</div>
   
</div>
	<!-- end #header -->
	<div id="top_menu">
		<ul>
			<li><a href="index.jsp">��ҳ</a></li>
		    <li><a href="user_blog.jsp">����</a></li>
		    <li><a href="user_album.jsp">���</a></li>
		    <li><a href="user_mood.jsp">˵˵</a></li>
			<li><a href="user_message.jsp">����</a></li>
			<li><a href="user_home.jsp">��������</a></li>
		</ul>
	</div>
</div>
