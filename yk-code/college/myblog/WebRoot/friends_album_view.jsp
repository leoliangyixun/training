<%@page import="java.text.DateFormat"%>
<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="com.yangkai.myblog.tools.EncoderUtil"%>
<%@page import="com.yangkai.myblog.domain.Album"%>
<%@page import="com.yangkai.myblog.domain.Photo"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>相册浏览</title>
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
					/*********************************请求参数*********************************/
					String username=URLDecoder.decode(request.getParameter("username"), "UTF-8");
					String album_name=URLDecoder.decode(request.getParameter("album_name"), "UTF-8");
					String curr_page=request.getParameter("page");
					String curr_step=request.getParameter("step");
					int photo_num=Integer.parseInt(request.getParameter("photo_num"));
					Map<String,Map<String,List<Photo>>> friends_album_map=(Map<String,Map<String,List<Photo>>>)session.getAttribute("friends_album_map");
					Map<String,List<String>> friends_album_map_key=(Map<String,List<String>>)session.getAttribute("friends_album_map_key");//由album_map的key组成的集合。
					Map<String,List<Album>> friends_albums=(Map<String,List<Album>>)session.getAttribute("friends_albums");
			    	List<Photo> photos=friends_album_map.get(username).get(album_name);// 获得指定相册下的所有照片的集合。
    	    		/*****************************相册分页参数*****************************/
    	    		Integer currpage=null;//当前页。
    	    		Integer countpage=null;//相册总页数。
    	    		int pagesize=6;//每页显示的相册数。
    	    		Integer countstep=null;
    	    		Integer currstep=null;
    	    		int pagestep=5;
    	    		countpage=PagingUtil.getCountpage(photos.size(), pagesize);
    	    		countstep=PagingUtil.getCountstep(photos.size(), pagesize, pagestep);
    	    		currpage=PagingUtil.getCurrentpage(curr_page, countpage);
    	    		currstep=PagingUtil.getCurrentstep(curr_step, countstep);
					/*******************************************显示相册分类***************************************/
		        	int count=0;
					out.println("<div align='left'>");
					
					/************************************相册用户信息************************************/
					String album_desc=null;
					List<Album> albums=friends_albums.get(username);
					for(int i=0;i<albums.size();i++)
					{
						if(albums.get(i).getAlbumname().equals(album_name))
						{
							album_desc=albums.get(i).getAlbumdesc();
							break;
						}
					}
					
					album_desc=(album_desc==null?"":album_desc);
					out.println("<table border='0' width='548' bordercolor='#438945' cellspacing='0' cellpadding='10' >");
					out.println("<tr><td style='font-size:12px' width='200' rowspan='2'><img src='upload/相册/"+username+"/image/me.jpg' width='60' height='60'/></br>"
							   +"好友：<a style='color:red;'>"+username+"</a></br></td><td width='80'>相册名：</td><td>"+album_name+"</td></tr>");
					out.println("<tr><td>相册描述：</td><td>"+album_desc+"</td></tr>");
					out.println("</table>");
					
					
					out.println("<table border='0' bordercolor='#438945' cellspacing='0' cellpadding='10' "
								+"style='border-collapse:collapse'>");
					for(int i=0;i<2;i++)
					{
						out.println("<tr align='center'>");
						for(int j=0;j<3;j++)
						{
							count=pagesize*(currpage-1)+3*i+j;
							if(count<photos.size())
							{
								Photo photo=photos.get(count);
								 out.println("<td><img src='upload/相册/"
											 +username+"/"
											 +album_name+"/"
											 + photo.getPhotoname()+"' width='160' height='220'/><br>"
											 +"<div align='center' class='pic_link'>"
											 +"<a href='photo_show.jsp?username="
											 +URLEncoder.encode(username,"UTF-8")+"&album_name="
								 			 +URLEncoder.encode(album_name,"UTF-8")+"&photo_name="
											 +photo.getPhotoname()+"'>浏览</a>"
											 +"<a></a></div></td>");
											 	 			
							}
							else{
								break;
							}
						}
						if(count>=photos.size())
						{
							break;
						}
						out.println("</tr>");	
					}
					out.println("</table>");
					out.println("</div>");
					/*******************************************分页显示*******************************************/
					out.println("<div align='center' id='pages'>");
					if(countpage>=pagestep)
					{
						out.println("<a href='friends_album_view.jsp?username="+URLEncoder.encode(username,"UTF-8")+"&album_name="+URLEncoder.encode(album_name,"UTF-8")
								+"&photo_num="+photo_num+"&page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
						for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
						{
							out.println("<a href='friends_album_view.jsp?username="+URLEncoder.encode(username,"UTF-8")+"&album_name="+URLEncoder.encode(album_name,"UTF-8")+"&photo_num="+photo_num+"&page="+k+"&step="+currstep+"'>"+k+"</a>");
						}
						int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
						out.println("<a href='friends_album_view.jsp?username="+URLEncoder.encode(username,"UTF-8")+"&album_name="+URLEncoder.encode(album_name,"UTF-8")
								+"&photo_num="+photo_num+"&page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
					}else{
						if(countpage>1)
						{
							for(int k=1;k<=countpage;k++)
							{
								out.println("<a href='friends_album_view.jsp?username="+URLEncoder.encode(username,"UTF-8")+"&album_name="
											+URLEncoder.encode(album_name,"UTF-8")+"&photo_num="+photo_num+"&page="+k+"'>"+k+"</a>");
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