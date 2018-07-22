<%@page import="java.text.DateFormat"%>
<%@page import="com.yangkai.myblog.tools.com.yangkai.myblog.tools.PageApart"%>
<%@page import="com.yangkai.myblog.tools.com.yangkai.myblog.tools.CharactorEncoder"%>
<%@page import="com.yangkai.myblog.domain.Album"%>
<%@page import="com.yangkai.myblog.domain.Photo"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>������</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>
	<%
		/*********************************�������*********************************/
			String username=URLDecoder.decode(request.getParameter("username"), "UTF-8");
			String album_name=URLDecoder.decode(request.getParameter("album_name"), "UTF-8");
			String curr_page=request.getParameter("page");
			String curr_step=request.getParameter("step");
			int photo_num=Integer.parseInt(request.getParameter("photo_num"));
		    Map<String,Map<String,List<Photo>>> friends_album_map=(Map<String,Map<String,List<Photo>>>)session.getAttribute("friends_album_map");
		    Map<String,List<String>> friends_album_map_key=(Map<String,List<String>>)session.getAttribute("friends_album_map_key");//��album_map��key��ɵļ��ϡ�
		    Map<String,List<Album>> friends_albums=(Map<String,List<Album>>)session.getAttribute("friends_albums");
	%>
	<div>
	<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
	<div id="content">
	<div id="menu_up">
	<div id="menu">
	<div>
		<ul>
		<li><a href="index.jsp">���²���</a></li>
		<li class="current_page_item"><a href="friends_blog.jsp">���Ѷ�̬</a></li>
		<li><a href="user_blog.jsp">����</a></li>			
		<li><a href="user_album.jsp">���</a></li>
		<li><a href="user_mood.jsp">˵˵</a></li>
		<li><a href="user_msg.jsp">����</a></li>
		<li><a href="user_home.jsp">��������</a></li>
		</ul>
	</div>
	
    <%
	    	List<Photo> photos=friends_album_map.get(username).get(album_name);// ���ָ������µ�������Ƭ�ļ��ϡ�
	    		/*****************************����ҳ����*****************************/
	    		Integer currpage=null;//��ǰҳ��
	    		Integer countpage=null;//�����ҳ��
	    		int pagesize=6;//ÿҳ��ʾ�������
	    		Integer countstep=null;
	    		Integer currstep=null;
	    		int pagestep=5;
	    		countpage=PageApart.getCountpage(photos.size(), pagesize);
	    		countstep=PageApart.getCountstep(photos.size(), pagesize, pagestep);
	    		currpage=PageApart.getCurrentpage(curr_page, countpage);
	    		currstep=PageApart.getCurrentstep(curr_step, countstep);
	    %>
	</div>
	</div>
	<div id="list">
	<div id="list_top">
		<div id="list_middle">
			<div id="list_bottom">
				<div id="blog_list">
				<div id="link_list_style">
				    <a href="friends_blog.jsp">���Ѳ���</a>
				    <a href="friends_album.jsp" style="background-color:#E97B56;">�������</a>
					<a href="friends_mood.jsp">����˵˵</a>
					<a href="friends_message.jsp">��������</a>
		        </div>
				<%
					
					/*******************************************��ʾ������***************************************/
		        	int count=0;
					out.println("<div align='left'>");
					
					/************************************����û���Ϣ************************************/
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
					out.println("<tr><td style='font-size:12px' width='200' rowspan='2'><img src='upload/���/"+username+"/image/me.jpg' width='60' height='60'/></br>"
							   +"���ѣ�<a style='color:red;'>"+username+"</a></br></td><td width='80'>�����</td><td>"+album_name+"</td></tr>");
					out.println("<tr><td>���������</td><td>"+album_desc+"</td></tr>");
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
								 out.println("<td><img src='upload/���/"
											 +username+"/"
											 +album_name+"/"
											 + photo.getPhotoname()+"' width='160' height='220'/><br>"
											 +"<div align='center' class='pic_link'>"
											 +"<a href='photo_show.jsp?username="
											 +URLEncoder.encode(username,"UTF-8")+"&album_name="
								 			 +URLEncoder.encode(album_name,"UTF-8")+"&photo_name="
											 +photo.getPhotoname()+"'>���</a>"
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
					/*******************************************��ҳ��ʾ*******************************************/
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