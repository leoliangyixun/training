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
<div>
<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
		<ul>
				 <li class="current_page_item"><a href="user_blog.jsp">我的博客</a></li>
				 <li><a href="blog_add.jsp">写博客</a></li>
				 <li><a href="blog_private.jsp">私密日志</a></li>	
				 <li><a href="blog_draft.jsp">草稿箱</a></li>	
				 <li><a href="blog_list.jsp">博客管理</a></li>	
			</ul>
		</div>
</div>
<div id="list">
	<div id="list_top">
		<div id="list_middle">
			<div id="list_bottom">
				<div id="blog_list">
				<%
					String loginuser=(String)session.getAttribute("loginuser");
					int blog_id=Integer.parseInt(request.getParameter("blog_id"));
					int mark=Integer.parseInt(request.getParameter("mark"));
				    //boolean isFriend=false;
					//isFriend=Boolean.parseBoolean(request.getParameter("isFriend"));
					String blog_link_mark=request.getParameter("blog_link_mark");
					//out.println(loginuser);
					List<BlogComment> blog_comment = (ArrayList<BlogComment>) session.getAttribute("blog_comment");
					Map<Integer,List<BlogCommentReply>> blog_comment_reply_map=(HashMap<Integer,List<BlogCommentReply>>)session.getAttribute("blog_comment_reply_map");
					List<Blog> current_blog=null;
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
							if(blog_link_mark!=null && !blog_link_mark.equals("null"))
							{
								if(Integer.parseInt(blog_link_mark)==Constants.BLOG_LINK_MARK_LATEST)
								{
									out.println("<div align='left' class='back'>当前位置：<a href='index.jsp'>最新博客<a>>><a style='font-style:normal'>浏览最新博文</a></div>");
								}
								else if(Integer.parseInt(blog_link_mark)==Constants.BLOG_LINK_MARK_DRAFT)
								{
									out.println("<div align='left' class='back'>当前位置：<a href='user_blog.jsp'>博客</a>>><a href='blog_draft.jsp'>草稿箱<a>>><a style='font-style:normal'>浏览草稿博文</a></div>");
								}
								else if(Integer.parseInt(blog_link_mark)==Constants.BLOG_LINK_MARK_PRIVATE)
								{
									out.println("<div align='left' class='back'>当前位置：<a href='user_blog.jsp'>博客</a>>><a href='blog_private.jsp'>私密日志<a>>><a style='font-style:normal'>浏览私密日志</a></div>");
								}
								else if(Integer.parseInt(blog_link_mark)==Constants.BLOG_LINK_MARK_FRIENDS)
								{
									out.println("<div align='left' class='back'>当前位置：<a href='friends_blog.jsp'>好友博客</a>>><a style='font-style:normal'>浏览好友博文</a></div>");
								}
							}else{
								out.println("<div align='left' class='back'>当前位置：<a href='user_blog.jsp'>博客</a>>><a style='font-style:normal'>浏览博文</a></div>");
							}
							/*****************************这里显示文章的全文信息*****************************/					
							out.println("<div class='title'>文章标题：<a>" + blog_subject + "</a></div>");

							out.println("<div class='link_style'><a>作者：" + username+ "</a>");
							if(loginuser==null)
							{
								out.println("<span style='margin-left:385px;'><a href='message_add.jsp?username="+URLEncoder.encode(username, "UTF-8")+"'>[添加留言]</a>");		
							}else{
								if(!username.equals(loginuser))
								{			
									out.println("<span style='margin-left:305px;'>"
											+"<a href='message_add.jsp?username="+URLEncoder.encode(username, "UTF-8")+"'>[添加留言]</a>"
											+"<a href='FriendCheck?username="+URLEncoder.encode(username, "UTF-8")+"'>[加为好友]</a>");
								}
							}
							out.println("</span></div>");
							
							out.println("<div><a>文章类型：" + blog_class+ "</a></div>");
							String edit_str="<div class='link_style2'><a>发表时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(blog_date) + "</a>";

							if(loginuser!=null && loginuser.equals(username))
							{
								if(mark==Constants.BLOG_FOR_PRIVATE)
								{
									edit_str=edit_str+"<span style='margin-left:180px;'><a href='AlterBlogState?blog_id="
											+ blog_id
											+ "&type="
											+ Constants.BLOG_FOR_PRIVATE
											+ "'>公开日志</a><a href='blog_edit.jsp?blog_id="
													+blog_id+"&mark="+mark+"&blog_link_mark="+blog_link_mark+"'>编辑</a><a href='DeleteBlog?blog_id="+blog_id+"&mark="+mark+"'>删除</a></span>";
								}else{
								edit_str=edit_str+"<span style='margin-left:260px;'><a href='blog_edit.jsp?blog_id="
												 +blog_id+"&mark="+mark+"&blog_link_mark="+blog_link_mark+"'>编辑</a><a href='DeleteBlog?blog_id="+blog_id+"&mark="+mark+"'>删除</a></span>";		
								}
							}
							edit_str=edit_str+"</div>";
							out.println(edit_str);				
							out.println("<div><textarea id='textarea_border'  cols='80' rows='20' readonly='readonly'>"+ blog_content + "</textarea></div>");
							if(mark==Constants.BLOG_FOR_DRAFT)
							{
								out.println("<div><input type='button' value='发表' onclick='javascript:alterBlogState("+blog_id+","+Constants.BLOG_FOR_DRAFT+")' "
										   +"style='font-size: 24px;width: 110px;height:40px;margin-top:20px;padding:0 10px;margin-right:10px;font-weight: bold;font-family:微软雅黑;'/></div>");
							}else{
								/************************************显示该文章的评论************************************/
								if(blog_comment==null || blog_comment.size()==0)
								{
									if(mark!=Constants.BLOG_FOR_PRIVATE && mark!=Constants.BLOG_FOR_DRAFT)
									{
										out.println("<div style='margin-top:10px;font-size:18px;'>该博文还没有评论。</div>");
									}
								}
								else{
									out.println("<div style='margin-top:5px;font-size:18px;'>用户评论：</div>");
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
										 out.println("<table border='0' width='575' bgcolor='#FFFF80' style='margin:10px auto ;border-collapse: collapse;'>");
										 out.println("<tr>");
										 out.println("<td rowspan='2' width='60' align='left' valign='top' style='font-size:12px'>");
										 out.println("<img src='upload/相册/"+guest+"/image/me.jpg' width='60' height='60''/>");
										 out.println("</td>");
										 out.println("<td colspan='2' align='left' ><a style='font-size:12px;color:red'>"+guest+"</a>："+blog_comment_content+"</td>");
										 out.println("</tr>");
										 out.println("<tr>");
										 
										 out.println("<td align='left' width='165' height='20'><a style='font-size:12px;'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(blog_comment_date)+"</a></td>");
										 out.println("<td align='right' width='350' height='20'>");
											if(loginuser!=null && loginuser.equals(username))
											{
												out.println("<a href='javascript:showBlogCommentReplyTextarea("
														+blog_comment_id+","+blog_id+","+mark+")' style='margin:0 10px 0 0'>回复评论</a><a href='DeleteBlogComment?blog_comment_id="
																+blog_comment_id+"&blog_id="+blog_id+"&mark="+mark+"'>删除评论</a>");
															   							
											}
										 out.println("</td>");
										 out.println("</tr>");
										 out.println("</table>");
										/*************************************显示评论的回复*************************************/
										if(blog_comment_reply_map!=null )
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
													out.println("<table border='0' width='510' bgcolor='#FFFF80' style='margin:0 0 10px 67px;border-collapse: collapse;'>");
													out.println("<tr>");
													out.println("<td rowspan='2' width='60' align='center' style='font-size:12px'>");
													//out.println("<a>"+loginuser+"</a><br>");
													out.println("<img src='upload/相册/"+loginuser+"/image/me.jpg' width='60' height='60''/><br>");
													out.println("<a>"+DateFormat.getDateTimeInstance().format(blog_comment_reply_date)+"</a><br>");
													out.println("</td>");
													out.println("<td valign='top' align='left'>"+blog_comment_reply_content+"</td>");
													out.println("</tr>");
													out.println("<tr>");
													out.println("<td valign='top' align='left' height='20'>");
													if(loginuser!=null && loginuser.equals(username))//loginuser.equals(username)要建立在loginuser!=null的前提下。
													{
														out.println("<div align='right'><a href='DeleteBlogCommentReply?blog_comment_reply_id="
																	+blog_comment_reply_id+"&blog_comment_id="+blog_comment_id+"&blog_id="+blog_id+"&mark="+mark+"'>删除回复</a></div>");
													}
													out.println("</td>");
													out.println("</tr>");
													out.println("</table>");
													//out.println("<div><a>回复内容：" +blogcommentreply_content+ "</a></div>");
													//out.println("<div><a>回复时间："+blogcommentreply_date+"</a></div>");
													out.println("</div>");
												
												}
											}
										}
										out.println("<div id='blog_comment_reply_textarea"+blog_comment_id+"'></div>");
										out.println("</div>");//end of .border
									}//end of for (int j = 0; j < bc.size(); j++) 		
								}//end of else
								/*****************************************在这里发表评论****************************************/
								if(loginuser==null)
								{
									out.println("<div style='margin-top:10px;font-size:18px;'>你还没有登录，如果要发表评论，请先登录。</div>");
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
										out.println("<textarea id='textarea_border' name='blog_comment_content' cols='80' rows='5'></textarea>");
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