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
					<li class="current_page_item"><a href="user_contact.jsp">通讯录</a></li>
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
							String contacts_class=request.getParameter("contacts_class");
											System.out.println("top"+contacts_class);
											List<String> user_contacts_class=(List<String>)session.getAttribute("user_contacts_class");
											List<Contacts> user_contacts=(List<Contacts>)session.getAttribute("user_contacts");
						%>
							<div>
								<div id="contact_navigator">
									<div><a href="user_contact.jsp">所有类别</a></div>
									<%
										if(user_contacts_class!=null){ 
											for(int i=0;i<user_contacts_class.size();i++){
									%>
									<div><a href="user_contact.jsp?contact_class=<%=user_contacts_class.get(i)%>"><%=user_contacts_class.get(i)%></a></div>

									<%
											}
									}
									%>
								</div>
								<div id="contact_content">
									<div id="contact_function_navigator"><a href="">添加联系人</a><a href="">添加类别</a><a href="">查找联系人</a></div>
									<div id="contact_list">
									
											<table width="490" border="1" style="margin:10px auto;border-collapse: collapse;">
											  <tr align="center">
												<td width="90">姓名</td>
												<td width="125">手机</td>
												<td width="90">QQ</td>
												<td width="125">E-mail</td>
												<td width="60"><a >操作</a></td>
											  </tr>
											<%
												if(contacts_class==null) {
													if(user_contacts!=null){
														for(int i=0;i<user_contacts.size();i++){
											%>
											  <tr align="center">
												<td width="90"><%=user_contacts.get(i).getName()%></td>
												<td width="125"><%=user_contacts.get(i).getTelephone()%></td>
												<td width="90"><%=user_contacts.get(i).getQq()%></td>
												<td width="125"><%=user_contacts.get(i).getMail()%></td>
												<td width="60"><a href="">修改</a>|<a href="">删除</a></td>
											  </tr>
											  <%    }
													}else{%>
														<tr align="center"><td>你的通讯录为空！！！</td></tr>
												<%}
												} else{
													System.out.println("middle"+contacts_class);
													//contact_class=new String(contact_class.getBytes("ISO-8859-1"),"UTF-8");
													//contact_class=URLDecoder.decode(contact_class, "UTF-8");
													System.out.println("bottom"+contacts_class);
													if(user_contacts!=null){
														for(int i=0;i<user_contacts.size();i++){
															
															if(user_contacts.get(i).getContactsclass().equals(contacts_class)){
														%>
															<tr align="center">
																<td width="90"><%=user_contacts.get(i).getName()%></td>
																<td width="125"><%=user_contacts.get(i).getTelephone()%></td>
																<td width="90"><%=user_contacts.get(i).getQq()%></td>
																<td width="125"><%=user_contacts.get(i).getMail()%></td>
																<td width="60"><a href="">修改</a>|<a href="">删除</a></td>
															</tr>
														<%
															}
														}
													}else{
													%>
													<tr><td colspan="5"align="center">你的通讯录为空！！！</td></tr>
													<%
													}
												}
											  %>
											</table>
										
									</div>
									<div id="contact_function_area"></div>
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
		<jsp:include page="include/bottom.html" flush="true"></jsp:include>
	</div>
</div>
</body>
</html>

