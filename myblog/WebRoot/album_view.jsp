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
			<li><a href="photo_upload.jsp">上传照片</a></li>
			<li><a href="album_create.jsp">创建相册</a></li>	
		</ul>
	</div>
	<!--div id="album_info"-->
    <%
		/*********************************页面参数*********************************/
		String loginuser = (String) session.getAttribute("loginuser");
		String curr_page=request.getParameter("page");
		String curr_step=request.getParameter("step");
		String album_name=URLDecoder.decode(request.getParameter("album_name"), "UTF-8");//没有乱码。
		//String albumName=request.getParameter("albumName");//没有乱码。
		//String albumName=GBKEncodingTool.setCharacterEncoding(request.getParameter("albumName"));//有乱码。
		//out.println(albumName);
		Map<String, List<Photo>> album_map=(Map<String,List<Photo>>)session.getAttribute("album_map");
		List<Album> user_album=(List<Album>)session.getAttribute("user_album");
		List<Photo> photos=album_map.get(album_name);// 获得指定相册下的所有照片的集合。
		int photo_num=photos.size();
		/*****************************相册分页参数*****************************/
		Integer currpage=null;//当前页。
		Integer countpage=null;//相册总页数。
		int rowpagesize=3;
		int colpagesize=3;
		int pagesize=rowpagesize * colpagesize;//每页显示的相册数。
		Integer countstep=null;//总的步数。
		Integer currstep=null;//当前步。
		int pagestep=5;//步长。
		countpage=PagingUtil.getCountpage(photos.size(), pagesize);
		countstep=PagingUtil.getCountstep(photos.size(), pagesize, pagestep);
		currpage=PagingUtil.getCurrentpage(curr_page, countpage);
		currstep=PagingUtil.getCurrentstep(curr_step, countstep);
		String album_desc=null;
		Date create_date=null;
		for(int k=0;k<user_album.size();k++)
		{
			if(user_album.get(k).getAlbumname().equals(album_name))
			{
				album_desc=user_album.get(k).getAlbumdesc();
				create_date=user_album.get(k).getAlbumcreatedate();
				break;
			}
			
			//out.println(albums.get(k).getAlbumName()+"<br/>");
		}
    %>
    <div style="margin-left:10px;margin-top: 20px;font-size: 22px;color: #093;">相册信息</div>
    <div style="border: #093 solid 1px;width:270px;margin-left:10px">
    <div  class="info">
	<table border="0" width="280" cellpadding="4" cellspacing="4" style="border-collapse: collapse;">
	  <tr>
	    <td width="80">相册名：</td>
	    <td><%=album_name%></td>
	  </tr>
	  <tr>
	    <td>照片数：</td>
	    <td><%=photo_num%>张</td>
	  </tr>
	  <tr>
	    <td>相册描述：</td>
	    <td><!--textarea name="album_desc" cols="20" rows="5"--><%=(album_desc==null?"":album_desc)%><!--/textarea--></td>
	  </tr>
	  <tr>
	    <td>创建时间：</td>
	    <td><%=DateFormat.getDateTimeInstance().format(create_date)%></td>
	  </tr>
	</table>
	</div>
	</div>
	<!--/div-->
	</div>
	</div>
	<div id="list">
	<div id="list_top">
		<div id="list_middle">
			<div id="list_bottom">
				<div id="blog_list">
				<%
					/*******************************************上传照片*******************************************/
					out.println("<div align='left' id='pic_upload'><a href='photo_upload.jsp?album_name="
					+URLEncoder.encode(album_name,"UTF-8")+"'>上传照片</a></div>");
					/*******************************************显示相册分类***************************************/
					int count=0;
					out.println("<div align='left'>"
								+"<table border='0' bordercolor='#438945' cellspacing='0' cellpadding='10' "
								+"style='border-collapse:collapse'>");
					for(int i=0;i<rowpagesize;i++)
					{
						out.println("<tr align='center'>");
						for(int j=0;j<colpagesize;j++)
						{
							count=pagesize*(currpage-1)+3*i+j;
							if(count<photos.size())
							{
								Photo photo=photos.get(count);
								 out.println("<td><a href='photo_show.jsp?username="
											 +URLEncoder.encode(loginuser,"UTF-8")+"&album_name="
							 				 +URLEncoder.encode(album_name,"UTF-8")+"&photo_name="
											 + photo.getPhotoname()+"'><img src='upload/相册/"
											 +loginuser+"/"
											 +album_name+"/"
											 + photo.getPhotoname()+"' width='160' height='220'/></a><br>"
											 +"<div align='center' class='pic_link'>"
											 +"<!--a href='photo_show.jsp?username="
											 +URLEncoder.encode(loginuser,"UTF-8")+"&album_name="
								 			 +URLEncoder.encode(album_name,"UTF-8")+"&photo_name="
											 + photo.getPhotoname()+"'>浏览</a-->"
											 +"<!--a href='#'>详细信息</a-->"
											 +"<a href='DeletePhoto?album_name="+URLEncoder.encode(album_name,"UTF-8")
											 +"&photo_id="+photo.getPhotoid()
											 +"&page="+currpage
											 +"&step="+currstep+"'>删除</a>"
											 +"</div></td>");	 			
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
						out.println("<a href='album_view.jsp?album_name="+URLEncoder.encode(album_name,"UTF-8")
								+"&page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
						for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
						{
							out.println("<a href='album_view.jsp?album_name="+URLEncoder.encode(album_name,"UTF-8")+"&page="+k+"&step="+currstep+"'>"+k+"</a>");
						}
						int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
						out.println("<a href='album_view.jsp?album_name="+URLEncoder.encode(album_name,"UTF-8")
								+"&page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
					}else{
						if(countpage>1)
						{
							for(int k=1;k<=countpage;k++)
							{
								out.println("<a href='album_view.jsp?album_name="
											+URLEncoder.encode(album_name,"UTF-8")+"&page="+k+"'>"+k+"</a>");
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