<%@page import="java.net.URLDecoder"%>
<%@page import="com.yangkai.myblog.tools.com.yangkai.myblog.tools.PageApart"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.domain.Photo"%>
<%@page import="com.yangkai.myblog.domain.Album"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>�������</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>
<%
	String username=URLDecoder.decode(request.getParameter("username"), "UTF-8");
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
		<li><a href="index.jsp">���²���</a></li>
		<li class="current_page_item"><a href="friends_blog.jsp">���Ѷ�̬</a></li>
		<li><a href="user_blog.jsp">����</a></li>			
		<li><a href="user_album.jsp">���</a></li>
		<li><a href="user_mood.jsp">˵˵</a></li>
		<li><a href="user_msg.jsp">����</a></li>
		<li><a href="user_home.jsp">��������</a></li>
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
				    <a href="friends_blog.jsp">���Ѳ���</a>
				    <a href="friends_album.jsp" style="background-color:#E97B56;">�������</a>
					<a href="friends_mood.jsp">����˵˵</a>
					<a href="friends_message.jsp">��������</a>
		        </div>
					<%
						Map<String,Map<String,List<Photo>>> friends_album_map=(Map<String,Map<String,List<Photo>>>)session.getAttribute("friends_album_map");
							    Map<String,List<String>> friends_album_map_key=(Map<String,List<String>>)session.getAttribute("friends_album_map_key");//��album_map��key��ɵļ��ϡ�
							    Integer currpage=null;
								Integer countpage=null;
								int pagesize=6;
								int pagestep=5;
								Integer currstep=null;
								Integer countstep=null;
								//countpage=PagingParamTool.getCountpage(friends_album_map.get(username).size(), pagesize);
								//countstep=PagingParamTool.getCountstep(friends_album_map.get(username).size(), pagesize, pagestep);
								countpage=PageApart.getCountpage(friends_album_map_key.get(username).size(), pagesize);
								countstep=PageApart.getCountstep(friends_album_map_key.get(username).size(), pagesize, pagestep);
								currpage=PageApart.getCurrentpage(curr_page, countpage);
								currstep=PageApart.getCurrentstep(curr_step, countstep);
								
								
								
								
								out.println("<div align='left' >");
								/************************************����û���Ϣ************************************/
								out.println("<table border='0' width='548' bordercolor='#438945' cellspacing='0' cellpadding='10' >");
								out.println("<tr><td style='font-size:12px' width='200'><img src='upload/���/"+username+"/image/me.jpg' width='60' height='60'/></br>"
										   +"���ѣ�<a style='color:red;'>"+username+"</a></br></td></tr>");
								out.println("</table>");
								
								
								/************************************�����ʾ************************************/
								Map<String,List<Photo>> album_map=friends_album_map.get(username);//��ȡָ�����ѵ�������ᡣ
								List<String> album_map_key=friends_album_map_key.get(username);//��ȡָ�����ѵ����������
								int count=0;
								out.println("<table border='0' bordercolor='#438945' cellspacing='0' cellpadding='10' style='border-collapse:collapse'>");	
								for(int i=0;i<2;i++)
								{
									out.println("<tr align='center'>");	
								    for(int j=0;j<3;j++)
								    {
										count=pagesize*(currpage-1)+3*i+j;//��ȡָ�����ѵ�ǰ�����(��0��ʼ)��
										if(count<album_map_key.size())
										{	
											String album_name=album_map_key.get(count);
											if(album_map.get(album_name)!=null)
											{
												List<Photo> photos=album_map.get(album_name);//���ĳһ����µ�������Ƭ�Ķ���(Photo)��
												int photo_num=photos.size();//���ĳһ����µ���Ƭ����(Photo)��������
												Photo photo=photos.get(0);//����ȡָ������µĵ�һ����Ƭ����Photo���󣬸ö����װ����Ƭ����ϸ��Ϣ����	 

												String photo_name=photo.getPhotoname();//������������Ƭ��ơ�
												out.println("<td align='left'><img src='upload/���/"+username+"/"+album_name+"/" +photo_name
															+"' width='160' height='220'/></br>"
															+"�����<a href='friends_album_view.jsp?username="
															+URLEncoder.encode(username, "UTF-8")+"&album_name="
															+URLEncoder.encode(album_name, "UTF-8")+"&photo_num="+photo_num+"'>"+album_name+"</a></br>"
															+"<a>"+photo_num+"��</a></td>");
											}else{
												 out.println("<td align='left'><img src='upload/error.jpg' width='160' height='220'/></br>�����<a>"
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
								
								
								/************************************��ҳ************************************/
								
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