<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.domain.Photo"%>
<%@page import="com.yangkai.myblog.domain.Album"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>相册</title>
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
		<li class="current_page_item"><a href="user_album.jsp">我的相册</a></li>
		<li><a href="user_mood.jsp">我的说说</a></li>
		<li><a href="user_message.jsp">留言板</a></li>
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
				    <a href="user_album.jsp" style="background-color:#E97B56;">我的相册</a>
					<a href="photo_upload.jsp">上传照片</a>
					<a href="album_create.jsp">创建相册</a>	
				</div>
				<%
					String loginuser=(String)session.getAttribute("loginuser");
					String curr_page=request.getParameter("page");
					String curr_step=request.getParameter("step");
					Map<String, List<Photo>> album_map=(Map<String, List<Photo>>)session.getAttribute("album_map");
					List<String> album_map_key=(List<String>)session.getAttribute("album_map_key");//由album_map的key组成的集合。
					List<Album> user_album=(List<Album>)session.getAttribute("user_album");
					if(album_map!=null && album_map.size()>0)	
					{
						/*****************************相册分页*****************************/
						Integer currpage=null;//当前页。
						Integer countpage=null;//相册总页数。
						int rowpagesize=2;
						int colpagesize=3;
						int pagesize=rowpagesize * colpagesize;//每页显示的相册数。
						int pagestep=5;
						Integer currstep=null;
						Integer countstep=null;
						countpage=PagingUtil.getCountpage(album_map_key.size(), pagesize);
						countstep=PagingUtil.getCountstep(album_map_key.size(), pagesize, pagestep);
						currpage=PagingUtil.getCurrentpage(curr_page, countpage);
						currstep=PagingUtil.getCurrentstep(curr_step, countstep);
						/************************************相册显示************************************/
						int i,j;
						int count=0;//用来记录当前相册标号。
						out.println("<div align='left'>");
						out.println("<table border='0' bordercolor='#438945' cellspacing='0' cellpadding='10' "
										+"style='border-collapse:collapse'>");
						for(i=0;i<rowpagesize;i++)
						{
							out.println("<tr align='center'>");	
							for(j=0;j<colpagesize;j++)
							{
								count=pagesize*(currpage-1)+3*i+j;//根据分页参数获取当前相册标号(从0开始)。
								if(count<album_map_key.size())
								{	
									String album_name=album_map_key.get(count);
									if(album_map.get(album_name)!=null && album_map.get(album_name).size()>0 )
									{
										List<Photo> photos=album_map.get(album_name);//获得某一相册下的所有照片的对象(Photo)。
										int photo_num=photos.size();//获得某一相册下的照片对象(Photo)的数量。
										Photo photo=photos.get(0);//随机获取指定相册下的第一张照片对象（Photo对象，该对象封装了照片的详细信息）。	 
										//int album_id=album.getAlbumId();//获得相册id。
										//String albumName=albums.get(count).getAlbumName();//获得相册名。
										//albumName=URLEncoder.encode(albumName, "UTF-8");
										//String album_date=albums.get(count).getCreateDate();//获得相册的创建日期。
										String photo_name=photo.getPhotoname();//获得随机产生的照片名称。
										out.println("<td align='left'><a href='album_view.jsp?album_name="
											     	+URLEncoder.encode(album_name, "UTF-8")+"'>"
											    	+"<img src='upload/相册/"+loginuser+"/"+album_name+"/" +photo_name
													+"' width='160' height='220'/></a></br>"
													+"相册名：<a href='album_view.jsp?album_name="
													+URLEncoder.encode(album_name, "UTF-8")+"'>"+album_name+"</a></br>"
													+"<a>"+photo_num+"张</a></br><a href='#' style='margin-right:10px'>编辑</a><a href='DeleteAlbum?album_name="
													+URLEncoder.encode(album_name, "UTF-8")+"&page="+currpage+"&step="+currpage+"'>删除</a></td>");
									}else{
										 out.println("<td align='left'><img src='upload/error.jpg' width='160' height='220'/></br>相册名：<a>"
													+album_name+"</a></br><a href='photo_upload.jsp?album_name="+URLEncoder.encode(album_name, "UTF-8")
													+"'>上传照片</a></br><a href='#' style='margin-right:10px'>编辑</a><a href='DeleteAlbum?album_name="
													+URLEncoder.encode(album_name, "UTF-8")+"&page="+currpage+"&step="+currpage+"'>删除</a></td>");
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
							out.println("<a href='user_album.jsp?page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
							for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
							{
								out.println("<a href='user_album.jsp?page="+k+"&step="+currstep+"'>"+k+"</a>");
							}
							int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
							out.println("<a href='user_album.jsp?page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");
						}
						else{
							if(countpage>1)
							{
								for(int k=1;k<=countpage;k++)
								{
									out.println("<a href='user_album.jsp?page="+k+"'>"+k+"</a>");
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
<jsp:include page="include/bottom.html" flush="true"></jsp:include>
</div>
</body>
</html>