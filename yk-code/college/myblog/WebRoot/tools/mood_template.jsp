<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="com.yangkai.myblog.domain.MoodCommentReply"%>
<%@page import="com.yangkai.myblog.domain.MoodComment"%>
<%@page import="com.yangkai.myblog.domain.Mood"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>好友说说</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>

<div>
<div id="nav"><jsp:include page="../include/top.jsp" flush="true"></jsp:include></div>
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
				    <a href="friends_blog.jsp" >好友博文</a>
				    <a href="friends_album.jsp">好友相册</a>
				    <a href="friends_mood.jsp" style="background-color:#E97B56;">好友说说</a>
				    <a href="friends_message.jsp">好友留言</a>   
		    	</div>
				<%
						String curr_page=request.getParameter("page");
						String curr_step=request.getParameter("step");
						String loginuser=(String)session.getAttribute("loginuser");
						List<Mood> user_mood=(List<Mood>)session.getAttribute("user_mood");
						//List<Mood> friends_mood=(List<Mood>)session.getAttribute("friends_mood");
						List<MoodComment> mood_comment=(List<MoodComment>)session.getAttribute("mood_comment");
						List<MoodCommentReply> mood_comment_reply=(List<MoodCommentReply>)session.getAttribute("mood_comment_reply");
						if(user_mood!=null && user_mood.size()>0)
						{
							Integer currpage=null;
							Integer countpage=null;
							int pagesize=2;
							Integer countstep=null;
							Integer currstep=null;
							int pagestep=5;
							countpage=PagingUtil.getCountpage(user_mood.size(), pagesize);
							countstep=PagingUtil.getCountstep(user_mood.size(), pagesize, pagestep);
							currpage=PagingUtil.getCurrentpage(curr_page, countpage);
							currstep=PagingUtil.getCurrentstep(curr_step, countstep); 
							for(int i=(currpage-1)*pagesize;i<currpage*pagesize; i++)
							{
								if(i<user_mood.size())
								{
									Mood mood=user_mood.get(i);
									int mood_id=mood.getMoodid();
									out.println("<div style='margin-top:10px;margin-left:4px;'>");
									out.println("<table width='570' border='0'  bordercolor='#E7DF98' style='margin-bottom:5px;border-collapse: collapse;''>");
									out.println("<tr>"); 
									out.println("<td rowspan='2' width='65' align='center' valign='top'>"); 
									out.println("<img src='upload/相册/"+mood.getUsername()+"/image/me.jpg' width='65' height='65''/><br>");
									out.println("</td>");
									out.println("<td colspan='2'><a style='color:red'>"+mood.getUsername()+"：</a>"+mood.getMoodcontent()+"</td>"); 
									out.println("</tr>"); 
									out.println("<tr>");
									out.println("<td align='left'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mood.getMooddate())+"</td>");  
									out.println("<td align='right'><a href='DeleteMood?mood_id="+mood_id+"&page="+currpage+"&step="+currstep+"'>删除</a></td>"); 
									out.println("</tr>"); 
									out.println("</table>");
									if(mood_comment!=null && mood_comment.size()>0)
									{
										for(int j=0;j<mood_comment.size();j++)
										{
											if(mood_comment.get(j).getMoodid()==mood_id)
											{
												MoodComment moodcomment=mood_comment.get(j);
												int mood_comment_id=moodcomment.getMoodcommentid();
												out.println("<table width='505' border='0'  bordercolor='#E7DF98' style='margin-left:65px;margin-bottom:5px;border-collapse: collapse;'>");
												out.println("<tr>");
												out.println("<td rowspan='2' width='40' align='center' valign='top'>"); 
												out.println("<img src='upload/相册/"+moodcomment.getGuest()+"/image/me.jpg' width='40' height='40''/><br>");
												out.println("</td>");
												out.println("<td><a style='color:red'>"+moodcomment.getGuest()+"：</a>"+moodcomment.getMoodcommentcontent()+"</td>"); 
												out.println("</tr>");
												out.println("<tr>");
												out.println("<td align='left'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(moodcomment.getMoodcommentdate())
														+"<a href='javascript:showMoodCommentReplyTextarea("+mood_comment_id+","+currpage+","+currstep+")' style='margin-left:15px'>回复</a>"
														+"<a href='DeleteMoodComment?mood_comment_id="+mood_comment_id
														+"&page="+currpage+"&step="+currstep+"' style='margin-left:10px'>删除</a></td>");  
												out.println("</tr>"); 
												out.println("</table>");
												if(mood_comment_reply!=null && mood_comment_reply.size()>0)
												{
													for(int z=0;z<mood_comment_reply.size();z++)
													{
														if(mood_comment_reply.get(z).getMoodcommentid()==mood_comment_id)
														{
															MoodCommentReply moodcommetreply=mood_comment_reply.get(z);
															int mood_comment_reply_id=moodcommetreply.getMoodcommentreplyid();
															out.println("<table width='465' border='0'  bordercolor='#E7DF98' style='margin-left:105px;margin-bottom:5px;border-collapse: collapse;'>");
															out.println("<tr>");
															out.println("<td rowspan='2' width='40' align='center' valign='top'>"); 
															out.println("<img src='upload/相册/"+mood.getUsername()+"/image/me.jpg' width='40' height='40''/><br>");
															out.println("</td>");
															out.println("<td><a style='color:red'>"+mood.getUsername()+"：</a>"+moodcommetreply.getMoodcommentreplycontent()+"</td>"); 
															out.println("</tr>");
															out.println("<tr>");
															out.println("<td align='left'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(moodcommetreply.getMoodcommentreplydate())
																	+"<a href='DeleteMoodCommentReply?mood_comment_reply_id="+mood_comment_reply_id
																	+"&page="+currpage+"&step="+currstep+"' style='margin-left:10px'>删除</a></td>");  
															out.println("</tr>"); 
															out.println("</table>");
														}
													}
												}
												out.println("<div id='mood_comment_reply_textarea"+mood_comment_id+"'></div>");
											}
										}
									}
									out.println("</div>");
								}else{
									break;
								}
							}
							
							out.println("<div align='center' id='pages'>");
							if(countpage>pagestep)
							{
								out.println("<a href='user_mood.jsp?page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
								for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
								{
									out.println("<a href='user_mood.jsp?page="+k+"&step="+currstep+"'>"+k+"</a>");
								}
								int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
								out.println("<a href='user_mood.jsp?page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
							}else{
								if(countpage>1)
								{
									for(int k=1;k<=countpage;k++)
									{
										out.println("<a href='user_mood.jsp?page="+k+"'>"+k+"</a>");
									}
								}
							}
							out.println("</div>");	
						}else{
							out.println("<div align='center'><a class='success_style'>你还没有发表说说!!!</a></div>");
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

