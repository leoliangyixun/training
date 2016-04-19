<%@page import="java.net.URLDecoder"%>
<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.domain.Photo"%>
<%@page import="com.yangkai.myblog.domain.Album"%>
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
							String username=URLDecoder.decode(request.getParameter("username"), "UTF-8");
							String curr_page=request.getParameter("page");
							String curr_step=request.getParameter("step");
							Map<String,Map<String,List<Photo>>> friends_album_map=(Map<String,Map<String,List<Photo>>>)session.getAttribute("friends_album_map");
							Map<String,List<String>> friends_album_map_key=(Map<String,List<String>>)session.getAttribute("friends_album_map_key");//由album_map的key组成的集合。
							Integer currpage=null;
							Integer countpage=null;
							int pagesize=6;
							int pagestep=5;
							Integer currstep=null;
							Integer countstep=null;
							//countpage=PagingParamTool.getCountpage(friends_album_map.get(username).size(), pagesize);
							//countstep=PagingParamTool.getCountstep(friends_album_map.get(username).size(), pagesize, pagestep);
							countpage=PagingUtil.getCountpage(friends_album_map_key.get(username).size(), pagesize);
							countstep=PagingUtil.getCountstep(friends_album_map_key.get(username).size(), pagesize, pagestep);
							currpage=PagingUtil.getCurrentpage(curr_page, countpage);
							currstep=PagingUtil.getCurrentstep(curr_step, countstep);
							/************************************相册用户信息************************************/
							out.println("<div align='left' >");
							out.println("<table border='0' width='548' bordercolor='#438945' cellspacing='0' cellpadding='10' >");
							out.println("<tr><td style='font-size:12px' width='200'><img src='upload/相册/"+username+"/image/me.jpg' width='60' height='60'/></br>"
									   +"好友：<a style='color:red;'>"+username+"</a>&nbsp的相册</br></td></tr>");
							out.println("</table>");
							/************************************相册显示************************************/
							Map<String,List<Photo>> album_map=friends_album_map.get(username);//获取指定好友的所有相册。
							List<String> album_map_key=friends_album_map_key.get(username);//获取指定好友的所有相册名。
							int count=0;
							out.println("<table border='0' width='548' bordercolor='#438945' cellspacing='0' cellpadding='10' style='border-collapse:collapse'>");	
							for(int i=0;i<2;i++)
							{
								out.println("<tr align='center'>");	
								for(int j=0;j<3;j++)
								{
									count=pagesize*(currpage-1)+3*i+j;//获取指定好友当前相册标号(从0开始)。
									if(count<album_map_key.size())
									{	
										String album_name=album_map_key.get(count);
										if(album_map.get(album_name)!=null)
										{
											List<Photo> photos=album_map.get(album_name);//获得某一相册下的所有照片的对象(Photo)。
											int photo_num=photos.size();//获得某一相册下的照片对象(Photo)的数量。
											Photo photo=photos.get(0);//随机获取指定相册下的第一张照片对象（Photo对象，该对象封装了照片的详细信息）。	 

											String photo_name=photo.getPhotoname();//获得随机产生的照片名称。
											out.println("<td align='left'><img src='upload/相册/"+username+"/"+album_name+"/" +photo_name
														+"' width='160' height='220'/></br>"
														+"相册名：<a href='friends_album_view.jsp?username="
														+URLEncoder.encode(username, "UTF-8")+"&album_name="
														+URLEncoder.encode(album_name, "UTF-8")+"&photo_num="+photo_num+"'>"+album_name+"</a></br>"
														+"<a>"+photo_num+"张</a></td>");
										}else{
											 out.println("<td align='left'><img src='upload/error.jpg' width='160' height='220'/></br>相册名：<a>"
														+album_name+"</a></br></td>");
										}					
									}else{
										break;
									}
								}
								out.println("</tr>");	
							}
							out.println("</table>");
							out.println("</div>");
							/************************************分页************************************/
							out.println("<div align='center' id='pages'>");
							if(countpage>pagestep)
							{
								out.println("<a href='friends_more_album.jsp?username="+URLEncoder.encode(username,"UTF-8")+"&page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
								for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
								{
									out.println("<a href='friends_more_album.jsp?username="+URLEncoder.encode(username,"UTF-8")+"&page="+k+"&step="+currstep+"'>"+k+"</a>");
								}
								int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
								out.println("<a href='friends_more_album.jsp?username="+URLEncoder.encode(username,"UTF-8")+"&page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");
							}
							else{
								if(countpage>1)
								{
									for(int k=1;k<=countpage;k++)
									{
										out.println("<a href='friends_more_album.jsp?username="+URLEncoder.encode(username,"UTF-8")+"&page="+k+"'>"+k+"</a>");
									}
								}
							}
							out.println("</div>");
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