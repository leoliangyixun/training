<%@page import="java.text.DateFormat"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.domain.BlogCommentReply"%>
<%@ page import="com.yangkai.myblog.constants.Constants"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.yangkai.myblog.domain.Blog"%>
<%@ page import="com.yangkai.myblog.domain.BlogComment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<title>博文浏览</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
</head>

<body>
<%
	String loginuser=(String)session.getAttribute("loginuser");
	int blog_id=Integer.parseInt(request.getParameter("blog_id"));
	int mark=Integer.parseInt(request.getParameter("mark"));
    //boolean isFriend=false;
	//isFriend=Boolean.parseBoolean(request.getParameter("isFriend"));
	String blog_link_mark=request.getParameter("blog_link_mark");
	//out.println(loginuser);
%>
<div>
<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
		<ul>
			<li><a href="index.jsp">最新博客</a></li>			
			<li><a href="friends_blog.jsp">好友动态</a></li>
			<li class="current_page_item"><a href="user_blog.jsp">博客</a></li>
			<li><a href="user_album.jsp">相册</a></li>
			<li><a href="user_mood.jsp">说说</a></li>
			<li><a href="user_msg.jsp">留言</a></li>
			<li><a href="user_home.jsp">我的主页</a></li>
			</ul>
		</div>
</div>
<div id="list">
	<div id="list_top">
		<div id="list_middle">
			<div id="list_bottom">
				<div id="blog_list">
				<%
				List<BlogComment> blog_comment = (ArrayList<BlogComment>) session.getAttribute("blog_comment");
				Map<Integer,List<BlogCommentReply>> blog_comment_reply_map=(HashMap<Integer,List<BlogCommentReply>>)session.getAttribute("blog_comment_reply_map");
				List current_blog=null;
				if(mark==Constants.BLOG_FOR_LATEST)
				{
					current_blog=(ArrayList<Blog>) session.getAttribute("latest_blog");
				}
				if(mark==Constants.BLOG_FOR_FRIENDS)
				{
					current_blog=(ArrayList<Blog>) session.getAttribute("friends_blog");
				}
				if(mark==Constants.BLOG_FOR_USER)
				{
					current_blog=(ArrayList<Blog>) session.getAttribute("user_blog");
				}
				if(mark==Constants.BLOG_FOR_DRAFT)
				{
					current_blog=(ArrayList<Blog>) session.getAttribute("draft_blog");
				}
				if(mark==Constants.BLOG_FOR_PRIVATE)
				{
					current_blog=(ArrayList<Blog>) session.getAttribute("private_blog");
				}
				for (int i = 0; i < current_blog.size(); i++) 
				{
					Blog blog = (Blog) current_blog.get(i);
					if(blog_id==blog.getBlogid())
					{
						String username = blog.getUsername();
						String blog_subject = blog.getBlogsubject();
						String blog_class = blog.getBlogclass();
						Date blog_date = blog.getBlogdate();
						String blog_content = blog.getBlogcontent();
						out.println("<div id='single_list'>");
						String str="";
						if(blog_link_mark!=null && !blog_link_mark.equals("null"))
						{
							if(Integer.parseInt(blog_link_mark)==Constants.BLOG_LINK_MARK_DRAFT)
							{
								str="<a href='blog_draft.jsp'>草稿箱<a>>><a style='font-style:normal'>浏览草稿博文</a>";
							}
							if(Integer.parseInt(blog_link_mark)==Constants.BLOG_LINK_MARK_PRIVATE)
							{
								str="<a href='blog_private.jsp'>私密日志<a>>><a style='font-style:normal'>浏览私密日志</a>";
							}
							out.println("<div align='left' class='back'>当前位置：<a href='user_blog.jsp'>博客</a>>>"+str+"</div>");
						}else{
							out.println("<div align='left' class='back'>当前位置：<a href='user_blog.jsp'>博客</a>>>"+str+"<a style='font-style:normal'>浏览博文</a></div>");
						}
						/*****************************这里显示文章的全文信息*****************************/					
					    out.println("<div class='title'><a>文章标题：" + blog_subject + "</a></div>");
						if(!username.equals(loginuser))
						{
								out.println("<div class='link_style'><a>作者：" + username+ "</a><span style='margin-left:300px;'>"
						                +"<a href='message_add.jsp?username="+URLEncoder.encode(username, "UTF-8")
						                +"'>[添加留言]</a><a href='FriendCheck?username="+URLEncoder.encode(username, "UTF-8")+"'>[加为好友]</a></span></div>");
						}
						out.println("<div><a>文章类型：" + blog_class+ "</a></div>");
						String edit_str="<div class='link_style2'><a>发表时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(blog_date) + "</a>";
						if(loginuser!=null && loginuser.equals(username))
						{
							edit_str=edit_str+"<span style='margin-left:250px;'><a href='blog_edit.jsp?blog_id="
						+blog_id+"&mark="+mark+"&blog_link_mark="+blog_link_mark+"'>编辑</a><a href='DeleteBlog?blog_id="+blog_id+"&mark="+mark+"'>删除</a></span>";					
						}
						edit_str=edit_str+"</div>";
						out.println(edit_str);				
						out.println("<div><textarea class='content'  cols='60' rows='20' readonly='readonly'>"+ blog_content + "</textarea></div>");
						if(mark==Constants.BLOG_FOR_DRAFT)
						{
							out.println("<div><input type='button' value='发表' onclick='javascript:alterBlogState("+blog_id+")' "
						               +"style='font-size: 24px;width: 110px;height:40px;margin-top:20px'/></div>");
						}else{
							/************************************显示该文章的评论************************************/
							if(blog_comment==null)
							{
								if(mark!=Constants.BLOG_FOR_PRIVATE && mark!=Constants.BLOG_FOR_DRAFT)
								{
									out.println("<div><a>该博文还没有评论。</a></div>");
								}
							}
							else{
								for (int j = 0; j < blog_comment.size(); j++) 
								{
									BlogComment blogcomment = blog_comment.get(j);		
									int blog_comment_id=blogcomment.getBlogcommentid();
									String guest = blogcomment.getGuest();
									String blog_comment_content = blogcomment.getBlogcommentcontent();		
									Date blog_comment_date = blogcomment.getBlogcommentdate();
									out.println("<div class='border'>");
									/*
									out.println("<table border='0' width='570'>");
									out.println("<tr>");
									out.println("<td width='70' align='center' style='font-size:12px'>");
									out.println("<a>"+guest+"</a><br>");
									out.println("<img src='upload/error.jpg' width='60' height='60''/><br>");
									out.println("<a>"+blog_comment_date+"</a><br>");
									out.println("</td>");
									out.println("<td valign='center' align='left'>");
									out.println(blog_comment_content);
									out.println("</td>");
									out.println("</tr>");
									out.println("</table>");
									*/
									 out.println("<table border='0' width='550' bgcolor='#FFFF80' style='margin:10px auto ;border-collapse: collapse;'>");
									 out.println("<tr>");
									 out.println("<td rowspan='2' width='70' align='left' valign='top' style='font-size:12px'>");
									 out.println("<img src='upload/相册/"+guest+"/image/me.jpg' width='60' height='60''/>");
									 out.println("</td>");
									 out.println("<td align='left' width='150'><a style='font-size:12px;color:red'>"+guest+"</a>发表评论：</td>");
									 out.println("<td align='right' width='350'><a style='font-size:12px;'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(blog_comment_date)+"</a></td>");
									 out.println("</tr>");
									 out.println("<tr>");
									 out.println("<td colspan='2' align='left' >"+blog_comment_content+"</td>");
									 out.println("</tr>");
								 	 out.println("</table>");
									/*************************************显示评论的回复*************************************/
									if(blog_comment_reply_map!=null)
									{
										if(blog_comment_reply_map.containsKey(blog_comment_id))
										{
											List<BlogCommentReply> blog_comment_reply=blog_comment_reply_map.get(blog_comment_id);
											for(int k=0;k<blog_comment_reply.size();k++)
											{
												BlogCommentReply blogcommentreply=blog_comment_reply.get(k);
												int blog_comment_reply_id=blogcommentreply.getBlogcommentreplyid();
												String blog_comment_reply_content=blogcommentreply.getBlogcommentreplycontent();
												Date blog_comment_reply_date=blogcommentreply.getBlogcommentreplydate();
												out.println("<div class='link_style3'>");
												out.println("<table border='0' width='478' bgcolor='#FFFF80' style='margin-left:85px;border-collapse: collapse;'>");
												out.println("<tr>");
												out.println("<td width='70' align='center' style='font-size:12px'>");
												//out.println("<a>"+loginuser+"</a><br>");
												out.println("<img src='upload/相册/"+loginuser+"/image/me.jpg' width='60' height='60''/><br>");
												out.println("<a>"+DateFormat.getDateTimeInstance().format(blog_comment_reply_date)+"</a><br>");
												out.println("</td>");
												out.println("<td valign='center' align='left'>");
												out.println(blog_comment_reply_content);
												out.println("</td>");
												out.println("</tr>");
												out.println("</table>");
												//out.println("<div><a>回复内容：" +blogcommentreply_content+ "</a></div>");
												//out.println("<div><a>回复时间："+blogcommentreply_date+"</a></div>");
												out.println("</div>");
												if(loginuser!=null && loginuser.equals(username))//loginuser.equals(username)要建立在loginuser!=null的前提下。
												{
													out.println("<div style='margin-left:85px;'><a href='DeleteBlogCommentReply?blog_comment_reply_id="
																+blog_comment_reply_id+"&blog_id="+blog_id+"&mark="+mark+"'>删除回复</a></div>");
												}
											}
										}
									}
									if(loginuser!=null && loginuser.equals(username))
									{
										out.println("<div class='link_style4' ><span><a href='javascript:showBlogCommentReplyTextarea("
												+blog_comment_id+","+blog_id+","+mark+")'>回复评论</a><a href='DeleteBlogComment?blog_comment_id="
														+blog_comment_id+"&blog_id="+blog_id+"&mark="+mark+"'>删除评论</a></span></div>");
										out.println("<div id='blog_comment_reply_textarea"+blog_comment_id+"'></div>");			   							
									}
									out.println("</div>");//end of .border
								}//end of for (int j = 0; j < bc.size(); j++) 		
							}//end of else
							/*****************************************在这里发表评论****************************************/
							if(loginuser==null)
							{
								out.println("<div><a>你还没有登录，如果要发表评论，请先登录。</a></div>");
							}else{
								if(!loginuser.equals(username))
								{
									out.println("<a>发表评论：</a>");
									out.println("<div>");
									out.println("<form action='AddBlogComment' method='post'>");
									out.println("<input type='hidden' name='mark' value='"+mark+"' />");
									out.println("<input type='hidden' name='blog_id' value='"+blog_id+"' />");
									out.println("<input type='hidden' name='guest' value='"+loginuser+"' />");
									out.println("<input type='hidden' name='blog_comment_date' value='"
													+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date())+"' />");
									out.println("<textarea class='textarea' name='blog_comment_content' cols='60' rows='5'></textarea>");
									out.println("<div align='center' class='button_style'><input type='submit' value='发表评论'/>"
													+"<input type='reset' value='取消'/></div>");
									out.println("</form>");
									out.println("</div>");	
								}			 
							}
		                }
					out.println("</div>");//end of single_list
					break;
					}							
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