<%@page import="com.yangkai.myblog.tools.com.yangkai.myblog.tools.PageApart"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.domain.Photo"%>
<%@page import="com.yangkai.myblog.domain.Album"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>���</title>
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
</div>
</div>
<div id="list">
<div id="list_top">
	<div id="list_middle">
		<div id="list_bottom">
			<div id="blog_list">
			<%
				Map<String, List<Photo>> album_map=(Map<String, List<Photo>>)session.getAttribute("album_map");
					List<String> album_map_key=(List<String>)session.getAttribute("album_map_key");//��album_map��key��ɵļ��ϡ�
					List<Album> albums=(List<Album>)session.getAttribute("albums");
					//System.out.println(album_map.size()+";"+album_map_key);
					if(album_map!=null && album_map.size()>0)	
					{
					    /*****************************����ҳ*****************************/
						Integer currpage=null;//��ǰҳ��
						Integer countpage=null;//�����ҳ��
						int pagesize=6;//ÿҳ��ʾ�������
						int pagestep=5;
						Integer currstep=null;
						Integer countstep=null;
						countpage=PageApart.getCountpage(album_map_key.size(), pagesize);
						countstep=PageApart.getCountstep(album_map_key.size(), pagesize, pagestep);
						currpage=PageApart.getCurrentpage(curr_page, countpage);
						currstep=PageApart.getCurrentstep(curr_step, countstep);
						/************************************�����ʾ************************************/
						int i,j;
						int count=0;//������¼��ǰ����š�
						out.println("<div align='left'>");
						out.println("<table border='0' bordercolor='#438945' cellspacing='0' cellpadding='10' "
										+"style='border-collapse:collapse'>");
						for(i=0;i<2;i++)
						{
							out.println("<tr align='center'>");	
						    for(j=0;j<3;j++)
						    {
								count=pagesize*(currpage-1)+3*i+j;//��ݷ�ҳ�����ȡ��ǰ�����(��0��ʼ)��
								if(count<album_map_key.size())
								{	
									String album_name=album_map_key.get(count);
									if(album_map.get(album_name)!=null)
									{
										List<Photo> photos=album_map.get(album_name);//���ĳһ����µ�������Ƭ�Ķ���(Photo)��
										int photo_num=photos.size();//���ĳһ����µ���Ƭ����(Photo)��������
										Photo photo=photos.get(0);//����ȡָ������µĵ�һ����Ƭ����Photo���󣬸ö����װ����Ƭ����ϸ��Ϣ����	 
										//int album_id=album.getAlbumId();//������id��
										//String albumName=albums.get(count).getAlbumName();//��������
										//albumName=URLEncoder.encode(albumName, "UTF-8");
										//String album_date=albums.get(count).getCreateDate();//������Ĵ������ڡ�
										String photo_name=photo.getPhotoname();//������������Ƭ��ơ�
										out.println("<td align='left'><img src='upload/���/"+loginuser+"/"+album_name+"/" +photo_name
													+"' width='160' height='220'/></br>"
													+"�����<a href='album_view.jsp?album_name="
													+URLEncoder.encode(album_name, "UTF-8")+"&photo_num="+photo_num+"'>"+album_name+"</a></br>"
													+"<a>"+photo_num+"��</a></br><a href='#' style='margin-right:10px'>�༭</a><a href='DeleteAlbum?album_name="
													+URLEncoder.encode(album_name, "UTF-8")+"&page="+currpage+"&step="+currpage+"'>ɾ��</a></td>");
									}else{
										 out.println("<td align='left'><img src='upload/error.jpg' width='160' height='220'/></br>�����<a>"
													+album_name+"</a></br><a href='photo_upload.jsp?album_name="+URLEncoder.encode(album_name, "UTF-8")
													+"'>�ϴ���Ƭ</a></td>");
									}					
								}else{
									break;
								}
							}
							out.println("</tr>");	
						}
						out.println("</table>");
						out.println("</div>");
						/************************************��ҳ************************************/
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
						 out.println("<div  align='center'><a class='success_style'>�㻹û�����!!!</a><a href='album_create.jsp' class='continue_style'>�������<a></div>");
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