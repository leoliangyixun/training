<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.domain.Contacts"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>好友说说</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	<meta http-equiv="Content-Type" content="UTF-8"/>
	</head>

<body>
<div>
	<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
	<div id="content">
		<div id="menu_up">
			<div id="menu">
			<div>
				<ul>
					<li><a href="user_home.jsp">我的主页</a></li>
					<li><a href="user_friends.jsp">好友管理</a></li>			
					<li class="current_page_item"><a href="user_contacts.jsp">通讯录</a></li>
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
						<%
							String curr_page=request.getParameter("page");
							String curr_step=request.getParameter("step");
							List<String> user_contacts_class=(List<String>)session.getAttribute("user_contacts_class");
							List<Contacts> user_contacts=(List<Contacts>)session.getAttribute("user_contacts");
						%>
							<script type="text/javascript"  language="javascript">
							<!--
								showContacts(null,<%=curr_page%>,<%=curr_step%>);
							-->
							</script>
							<div>
								<div id="contacts_navigator">
									<div><a href="javascript:showContacts(null,<%=curr_page%>,<%=curr_step%>)">所有类别</a></div>
									
									<%
										if(user_contacts_class!=null){ 
											for(int i=0;i<user_contacts_class.size();i++){
									%>
									<div><a href="javascript:showContacts(<%=i%>,<%=curr_page%>,<%=curr_step%>)"><%=user_contacts_class.get(i)%></a><!--a href="javascript:showContacts(encodeURIComponent(<%=user_contacts_class.get(i)%>))"><%=user_contacts_class.get(i)%></a--></div>

									<%
											}
										}
									%>
								</div>
								<div id="contacts_content">
									<div id="contacts_function_navigator"><a href="javascript:showContactsClassAddArea()">添加通讯录类别</a><a href="javascript:showContactsAddArea()">添加联系人</a><a href="javascript:showContactsQueryArea()">查找联系人</a></div>
									<div id="contacts_list_area"></div>
									<div id="contacts_function_area"></div>
									<!--div id="contacts_query_area"></div-->
									<div id="contacts_alter_area"></div>
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		<div class="clear"></div>
	</div>
	<jsp:include page="include/bottom.html" flush="true"></jsp:include>	
</div>
</body>
</html>
	
