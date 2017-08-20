<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="com.yangkai.myblog.domain.Album"%>
<%@page import="com.yangkai.myblog.domain.Photo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>好友相册</title>
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
		<li class="current_page_item"><a href="friends_blog.jsp">好友动态</a></li>
		<li><a href="user_blog.jsp">我的博客</a></li>			
		<li><a href="user_album.jsp">我的相册</a></li>
		<li><a href="user_mood.jsp">我的说说</a></li>
		<li><a href="user_msg.jsp">留言板</a></li>
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
				    <a href="friends_blog.jsp">好友博文</a>
				    <a href="friends_album.jsp" style="background-color:#E97B56;">好友相册</a>
					<a href="friends_mood.jsp">好友说说</a>
					<a href="friends_message.jsp">好友留言</a>
		        </div>
				<%
						String curr_page=request.getParameter("page");
						String curr_step=request.getParameter("step");
						Map<String,Map<String,List<Photo>>> friends_album_map=(Map<String,Map<String,List<Photo>>>)session.getAttribute("friends_album_map");
						Map<String,List<String>> friends_album_map_key=(Map<String,List<String>>)session.getAttribute("friends_album_map_key");//由album_map的key组成的集合。
						List<String> friends=(List<String>)session.getAttribute("friends");
						//System.out.println(friends);
						//System.out.println(friends_album_map);
						//System.out.println(friends_album_map_key);
						if(friends!=null && friends.size()>0)	
						{
							/*****************************相册分页*****************************/
							Integer currpage=null;//当前页
							Integer countpage=null;//相册总页数
							int pagesize=2;//每页显示的相册数
							int pagestep=3;
							Integer currstep=null;
							Integer countstep=null;
							countpage=PagingUtil.getCountpage(friends.size(), pagesize);
							countstep=PagingUtil.getCountstep(friends.size(), pagesize, pagestep);
							currpage=PagingUtil.getCurrentpage(curr_page, countpage);
							currstep=PagingUtil.getCurrentstep(curr_step, countstep);
							//System.out.println(countpage+":"+pagestep);
							/************************************相册显示************************************/
							for(int i=0;i<2;i++)
							{	
								int index=pagesize*(currpage-1)+i;
								if(index<friends.size())
								{
									String username=friends.get(index);
									out.println("<div align='left'>");
									/*
									out.println("<table border='1' width='548' bordercolor='#438945' cellspacing='0' cellpadding='10' ><tr>");
									out.println("<td style='font-size:12px' width='120'><img src='upload/相册/"+username+"/image/me.jpg' width='60' height='60'/></br>"
											   +"好友：<a style='color:red;'>"+username+"</a></br></td>");
									out.println("<td><a id='message_textarea'></a></td>");
									out.println("<td align='right' valign='top' width='60'><a href='javascript:showMessageTextarea("+currpage+","+currstep+")'>添加留言</a></td>");
									out.println("</tr></table>");
									*/
									/************************************相册用户信息************************************/
									out.println("<table border='0' width='548' bordercolor='#438945' cellspacing='0' cellpadding='10' >");
									out.println("<tr><td style='font-size:12px' width='200'><img src='upload/相册/"+username+"/image/me.jpg' width='60' height='60'/></br>"
											   +"好友：<a style='color:red;'>"+username+"</a>&nbsp的相册</br></td>");
									if(friends_album_map==null || friends_album_map.get(username)==null)
									{
										out.println("<td algin='left'>该好友还没有相册！！！</td>");
										out.println("</tr></table>");
										continue;
									}
									out.println("</tr></table>");
									Map<String,List<Photo>> album_map=friends_album_map.get(username);//获取某一好友的所有相册。
									if(album_map!=null && album_map.size()>0)
									{
										int count=album_map.size();//获取某一好友的所有相册数量。
										out.println("<table border='0' width='548' bordercolor='#438945' cellspacing='0' cellpadding='10'>");
										out.println("<tr>");	
										for(int j=0;j<3;j++)
										{
											if(j<count)
											{
												String album_name=friends_album_map_key.get(username).get(j);
												if(album_map.get(album_name)!=null)
												{
													List<Photo> photos=album_map.get(album_name);//获得某一相册下的所有照片的对象(Photo)。
													int photo_num=photos.size();//获得某一相册下的照片对象(Photo)的数量。
													Photo photo=photos.get(0);//随机获取指定相册下的第一张照片对象（Photo对象，该对象封装了照片的详细信息）。
													String photo_name=photo.getPhotoname();//获得随机产生的照片名称。
													out.println("<td align='left'><img src='upload/相册/"+username+"/"+album_name+"/"+photo_name
																+"' width='160' height='220'/><br>"
																+"相册名：<a href='friends_album_view.jsp?username="
																+URLEncoder.encode(username, "UTF-8")+"&album_name="
																+URLEncoder.encode(album_name, "UTF-8")+"&photo_num="+photo_num+"'>"+album_name+"</a></br>"
																+"<a>"+photo_num+"张</a></td>");
												}else{
													 out.println("<td align='left'><img src='upload/error.jpg' width='160' height='220'/>相册名：<a>"+album_name+"</a></td>");									
												}		
											}else{
												break;
											}
										}
										out.println("</tr>");	
										if(count>3)
										{
											out.println("<tr><td colspan='3'><a href='friends_more_album.jsp?username="+URLEncoder.encode(username, "UTF-8")+"' style='font-style:italic;font-size:16px'>好友其它相册>></a></td></tr>");
										}
										out.println("</table>");
									}
									
									out.println("</div>");
								}else{
									break;
								}
							}
						
						/************************************分页************************************/
						
						out.println("<div align='center' id='pages'>");
						if(countpage>pagestep)
						{
							out.println("<a href='friends_album.jsp?page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
							for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
							{
								out.println("<a href='friends_album.jsp?page="+k+"&step="+currstep+"'>"+k+"</a>");
							}
							int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
							out.println("<a href='friends_album.jsp?page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");
						}
						else{
							if(countpage>1)
							{
								for(int k=1;k<=countpage;k++)
								{
									out.println("<a href='friends_album.jsp?page="+k+"'>"+k+"</a>");
								}
							}
						}
						out.println("</div>");
							
						}else{
							if(friends==null)
							{
								out.println("<div align='center'><a class='success_style'>你还没有好友!!!</a></div>");
							}else{
								out.println("<div align='center'><a class='success_style'>你的好友还没有创建相册!!!</a></div>");
							}
						}
				%>
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

