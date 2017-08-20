<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.tools.com.yangkai.myblog.tools.PageApart"%>
<%@page import="com.yangkai.myblog.domain.Album"%>
<%@page import="com.yangkai.myblog.domain.Photo"%>
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
				<div id="link_list_style">
				    <a href="user_album.jsp">�ҵ����</a>
				    <a href="my_friends_album.jsp" style="background-color:#E97B56;">�������</a>
		        </div>
				<%
					Map<String,Map<String,List<Photo>>> friends_album_map=(Map<String,Map<String,List<Photo>>>)session.getAttribute("friends_album_map");
						    Map<String,List<String>> friends_album_map_key=(Map<String,List<String>>)session.getAttribute("friends_album_map_key");//��album_map��key��ɵļ��ϡ�
							List<String> friends=(List<String>)session.getAttribute("friends");
							//System.out.println(friends);
							//System.out.println(friends_album_map);
							//System.out.println(friends_album_map_key);
							if(friends!=null && friends.size()>0)	
							{
								/*****************************����ҳ*****************************/
								Integer currpage=null;//��ǰҳ
								Integer countpage=null;//�����ҳ��
								int pagesize=2;//ÿҳ��ʾ�������
								int pagestep=3;
								Integer currstep=null;
								Integer countstep=null;
								countpage=PageApart.getCountpage(friends.size(), pagesize);
								countstep=PageApart.getCountstep(friends.size(), pagesize, pagestep);
								currpage=PageApart.getCurrentpage(curr_page, countpage);
								currstep=PageApart.getCurrentstep(curr_step, countstep);
								//System.out.println(countpage+":"+pagestep);
								/************************************�����ʾ************************************/
								for(int i=0;i<2;i++)
								{	
									int index=pagesize*(currpage-1)+i;
									if(index<friends.size())
									{
										String username=friends.get(index);
										out.println("<div align='left'>");
										/*
										out.println("<table border='1' width='548' bordercolor='#438945' cellspacing='0' cellpadding='10' ><tr>");
										out.println("<td style='font-size:12px' width='120'><img src='upload/���/"+username+"/image/me.jpg' width='60' height='60'/></br>"
												   +"���ѣ�<a style='color:red;'>"+username+"</a></br></td>");
										out.println("<td><a id='message_textarea'></a></td>");
										out.println("<td align='right' valign='top' width='60'><a href='javascript:showMessageTextarea("+currpage+","+currstep+")'>�������</a></td>");
										out.println("</tr></table>");
										*/
										/************************************����û���Ϣ************************************/
										out.println("<table border='0' width='548' bordercolor='#438945' cellspacing='0' cellpadding='10' >");
										out.println("<tr><td style='font-size:12px' width='250'><img src='upload/���/"+username+"/image/me.jpg' width='60' height='60'/></br>"
												   +"���ѣ�<a style='color:red;'>"+username+"</a></br></td>");
										if(friends_album_map==null || friends_album_map.get(username)==null)
										{
											out.println("<td algin='left'>�ú��ѻ�û����ᣡ����</td>");
											out.println("</tr></table>");
											continue;
										}
										out.println("</tr></table>");
										Map<String,List<Photo>> album_map=friends_album_map.get(username);//��ȡĳһ���ѵ�������ᡣ
										if(album_map!=null && album_map.size()>0)
										{
											int count=album_map.size();//��ȡĳһ���ѵ��������������
											out.println("<table border='0' width='548' bordercolor='#438945' cellspacing='0' cellpadding='10'>");
											out.println("<tr>");	
											for(int j=0;j<3;j++)
											{
												if(j<count)
												{
													String album_name=friends_album_map_key.get(username).get(j);
													if(album_map.get(album_name)!=null)
													{
														List<Photo> photos=album_map.get(album_name);//���ĳһ����µ�������Ƭ�Ķ���(Photo)��
														int photo_num=photos.size();//���ĳһ����µ���Ƭ����(Photo)��������
														Photo photo=photos.get(0);//����ȡָ������µĵ�һ����Ƭ����Photo���󣬸ö����װ����Ƭ����ϸ��Ϣ����
														String photo_name=photo.getPhotoname();//������������Ƭ��ơ�
														out.println("<td align='left'><img src='upload/���/"+username+"/"+album_name+"/"+photo_name
																	+"' width='160' height='220'/><br>"
																	+"�����<a href='friends_album_view.jsp?username="
																	+URLEncoder.encode(username, "UTF-8")+"&album_name="
																	+URLEncoder.encode(album_name, "UTF-8")+"&photo_num="+photo_num+"'>"+album_name+"</a></br>"
																	+"<a>"+photo_num+"��</a></td>");
													}else{
														 out.println("<td align='left'><img src='upload/error.jpg' width='160' height='220'/>�����<a>"+album_name+"</a></td>");									
													}		
												}else{
													break;
												}
											}
											out.println("</tr>");	
											if(count>3)
											{
												out.println("<tr><td colspan='3'><a href='friends_more_album.jsp?username="+URLEncoder.encode(username, "UTF-8")+"' style='font-style:italic;font-size:16px'>�����������>></a></td></tr>");
											}
											out.println("</table>");
										}
										
										out.println("</div>");
									}else{
										break;
									}
								}
							
							/************************************��ҳ************************************/
							
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
								 out.println("<div align='center'><a class='success_style'>�㻹û�к���!!!</a></div>");
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

