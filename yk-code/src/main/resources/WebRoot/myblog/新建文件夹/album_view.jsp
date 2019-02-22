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
		/*********************************ҳ�����*********************************/
			String loginuser = (String) session.getAttribute("loginuser");
			String curr_page=request.getParameter("page");
			String curr_step=request.getParameter("step");
			String album_name=URLDecoder.decode(request.getParameter("album_name"), "UTF-8");//û�����롣
			int photo_num=Integer.parseInt(request.getParameter("photo_num"));
			//String albumName=request.getParameter("albumName");//û�����롣
			//String albumName=GBKEncodingTool.setCharacterEncoding(request.getParameter("albumName"));//�����롣
			//out.println(albumName);
			Map<String, List<Photo>> album_map=(HashMap<String,List<Photo>>)session.getAttribute("album_map");
			List<Album> albums=(ArrayList<Album>)session.getAttribute("albums");
			List<Photo> photos=album_map.get(album_name);// ���ָ������µ�������Ƭ�ļ��ϡ�
			/*****************************����ҳ����*****************************/
			Integer currpage=null;//��ǰҳ��
			Integer countpage=null;//�����ҳ��
			int pagesize=6;//ÿҳ��ʾ�������
			Integer countstep=null;//�ܵĲ���
			Integer currstep=null;//��ǰ����
			int pagestep=5;//������
			countpage=PageApart.getCountpage(photos.size(), pagesize);
			countstep=PageApart.getCountstep(photos.size(), pagesize, pagestep);
			currpage=PageApart.getCurrentpage(curr_page, countpage);
			currstep=PageApart.getCurrentstep(curr_step, countstep);
	%>
	<div>
	<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
	<div id="content">
	<div id="menu_up">
	<div id="menu">
	<div>
		<ul>
			<li class="current_page_item"><a href="user_album.jsp">�ҵ����</a></li>			
			<li><a href="photo_upload.jsp">�ϴ���Ƭ</a></li>
			<li><a href="album_create.jsp">�������</a></li>	
			<li><a href="album_home.jsp">�������</a></li>	
		</ul>
	</div>
	<!--div id="album_info"-->
    <%
    String album_desc=null;
    Date create_date=null;
    for(int k=0;k<albums.size();k++)
    {
    	if(albums.get(k).getAlbumname().equals(album_name))
    	{
    		album_desc=albums.get(k).getAlbumdesc();
    		create_date=albums.get(k).getAlbumcreatedate();
    		break;
    	}
    	
    	//out.println(albums.get(k).getAlbumName()+"<br/>");
    }
    %>
    <div style="margin-left:10px;margin-top: 20px;font-size: 22px;color: #093;">�����Ϣ</div>
    <div style="border: #093 solid 1px;width:270px;margin-left:10px">
    <div  class="info">
	<table border="0" width="280" cellpadding="4" cellspacing="4" style="border-collapse: collapse;">
	  <tr>
	    <td width="80">�����</td>
	    <td><%=album_name%></td>
	  </tr>
	  <tr>
	    <td>��Ƭ��</td>
	    <td><%=photo_num%>��</td>
	  </tr>
	  <tr>
	    <td>���������</td>
	    <td><!--textarea name="album_desc" cols="20" rows="5"--><%=(album_desc==null?"":album_desc)%><!--/textarea--></td>
	  </tr>
	  <tr>
	    <td>����ʱ�䣺</td>
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
					/*******************************************�ϴ���Ƭ*******************************************/
					out.println("<div align='left' id='pic_upload'><a href='photo_upload.jsp?album_name="
					+URLEncoder.encode(album_name,"UTF-8")+"'>�ϴ���Ƭ</a></div>");
					/*******************************************��ʾ������***************************************/
					int count=0;
					out.println("<div align='left'>"
								+"<table border='0' bordercolor='#438945' cellspacing='0' cellpadding='10' "
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
											 +loginuser+"/"
											 +album_name+"/"
											 + photo.getPhotoname()+"' width='160' height='220'/><br>"
											 +"<div align='center' class='pic_link'>"
											 +"<a href='photo_show.jsp?username="
											 +URLEncoder.encode(loginuser,"UTF-8")+"&album_name="
								 			 +URLEncoder.encode(album_name,"UTF-8")+"&photo_name="
											 + photo.getPhotoname()+"'>���</a>"
											 +"<a></a><!--a href='#'>��ϸ��Ϣ</a--><a href='#'>ɾ��</a>"
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
					/*******************************************��ҳ��ʾ*******************************************/
					out.println("<div align='center' id='pages'>");
					if(countpage>=pagestep)
					{
						out.println("<a href='album_view.jsp?album_name="+URLEncoder.encode(album_name,"UTF-8")
								+"&photo_num="+photo_num+"&page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
						for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
						{
							out.println("<a href='album_view.jsp?album_name="+URLEncoder.encode(album_name,"UTF-8")+"&photo_num="+photo_num+"&page="+k+"&step="+currstep+"'>"+k+"</a>");
						}
						int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
						out.println("<a href='album_view.jsp?album_name="+URLEncoder.encode(album_name,"UTF-8")
								+"&photo_num="+photo_num+"&page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
					}else{
						if(countpage>1)
						{
							for(int k=1;k<=countpage;k++)
							{
								out.println("<a href='album_view.jsp?album_name="
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