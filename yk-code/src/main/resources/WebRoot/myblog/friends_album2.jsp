<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.tools.com.yangkai.myblog.tools.PageApart"%>
<%@page import="com.yangkai.myblog.domain.Album"%>
<%@page import="com.yangkai.myblog.domain.Photo"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>好友相册</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>
<%
	String loginuser=(String)session.getAttribute("loginuser");
	String curr_page=request.getParameter("page");
	String curr_step=request.getParameter("step");
%>
<div>
<div id="nav"><jsp:include page="../include/top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
<div>
	<ul>
		<li><a href="index.jsp">最新博客</a></li>
		<li class="current_page_item"><a href="friends_blog.jsp">好友动态</a></li>
		<li><a href="user_blog.jsp">博客</a></li>			
		<li><a href="user_album.jsp">相册</a></li>
		<li><a href="user_mood.jsp">说说</a></li>
		<li><a href="user_msg.jsp">留言</a></li>
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
					Map<String, List<Photo>> album_map=(Map<String, List<Photo>>)session.getAttribute("album_map");
							List<String> album_map_key=(List<String>)session.getAttribute("album_map_key");//由album_map的key组成的集合。
							List<Album> albums=(List<Album>)session.getAttribute("albums");
							if(album_map!=null && album_map.size()>0)	
							{
							    /*****************************相册分页*****************************/
								Integer currpage=null;//当前页
								Integer countpage=null;//相册总页数
								int pagesize=3;//每页显示的相册数
								int pagestep=3;
								Integer currstep=null;
								Integer countstep=null;
								countpage=PageApart.getCountpage(album_map_key.size(), pagesize);
								countstep=PageApart.getCountstep(album_map_key.size(), pagesize, pagestep);
								currpage=PageApart.getCurrentpage(curr_page, countpage);
								currstep=PageApart.getCurrentstep(curr_step, countstep);
								//System.out.println(countpage+":"+pagestep);
								/************************************相册显示************************************/
								
								
								int count=0;//用来记录当前相册标号
								out.println("<div align='left'>");
								/*
								out.println("<table border='1' width='548' bordercolor='#438945' cellspacing='0' cellpadding='10' ><tr>");
								out.println("<td style='font-size:12px' width='120'><img src='upload/相册/"+loginuser+"/image/me.jpg' width='60' height='60'/></br>"
										   +"好友：<a style='color:red;'>"+loginuser+"</a></br></td>");
								out.println("<td><a id='message_textarea'></a></td>");
								out.println("<td align='right' valign='top' width='60'><a href='javascript:showMessageTextarea("+currpage+","+currstep+")'>添加留言</a></td>");
								out.println("</tr></table>");
								*/
								
								out.println("<table border='1' width='548' bordercolor='#438945' cellspacing='0' cellpadding='10' >");
								out.println("<tr><td style='font-size:12px' width='120'><img src='upload/相册/"+loginuser+"/image/me.jpg' width='60' height='60'/></br>"
										   +"好友：<a style='color:red;'>"+loginuser+"</a></br></td>");
								
								out.println("</tr></table>");
								
								out.println("<table border='1' bordercolor='#438945' cellspacing='0' cellpadding='10'>");   
								out.println("<tr align='center'>");	
								for(int j=0;j<3;j++)
								{
									count=pagesize*(currpage-1)+j;//根据分页参数获取当前相册标号(从0开始)。
									if(count<album_map_key.size())
									{	
										String album_name=album_map_key.get(count);
										if(album_map.get(album_name)!=null)
										{
											List<Photo> photos=album_map.get(album_name);//获得某一相册下的所有照片的对象(Photo)
											int photo_num=photos.size();//获得某一相册下的照片对象(Photo)的数量
											Photo photo=photos.get(0);//随机获取指定相册下的第一张照片对象（Photo对象，该对象封装了照片的详细信息）	 
											//int album_id=album.getAlbumId();//获得相册id
											//String albumName=albums.get(count).getAlbumName();//获得相册名
											//albumName=URLEncoder.encode(albumName, "UTF-8");
											//String album_date=albums.get(count).getCreateDate();//获得相册的创建日期
											String photo_name=photo.getPhotoname();//获得随机产生的照片名称
											out.println("<td><img src='upload/相册/"+loginuser+"/"+album_name+"/" +photo_name
														+"' width='160' height='220'/><br>"
														+"<div align='left'>相册名：<a href='album_view.jsp?album_name="
														+URLEncoder.encode(album_name, "UTF-8")+"&photo_num="+photo_num+"'>"+album_name+"</a></div>"
														+"<div align='left'><a>"+photo_num+"张</a></div></td>");
										}else{
											 out.println("<td><img src='upload/error.jpg' width='160' height='220'/><div align='left'>你还没有上传照片!!!</div><div align='left'>相册名：<a>"
														+album_name+"</a><a href='photo_upload.jsp?album_name="+URLEncoder.encode(album_name, "UTF-8")
														+"' style='margin-left:10px;'>上传照片</a></div></td>");
										}					
									}else{
										break;
									}
								}

								out.println("</tr>");	
								out.println("<tr><td colspan='3'><a href='#' style='font-style:italic;font-size:16px'>好友其它相册>></a></td></tr>");
								out.println("</table>");
								out.println("</div>");
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
								 out.println("<div  align='center'><a class='success_style'>你还没有相册!!!</a><a href='album_create.jsp' class='continue_style'>创建相册<a></div>");
							}
				%>
			</div>
		</div>
	</div>
</div>
</div>
<div style="clear:both"></div>
</div>
<jsp:include page="../include/bottom.html" flush="true"></jsp:include>
</div>
</body>
</html>

