<%@page import="com.yangkai.myblog.domain.BlogCommentReply"%>
<%@ page import="com.yangkai.myblog.constants.Constants"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
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
	//out.println(loginuser);
%>
<div>
<div id="nav"><jsp:include page="../top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
	<div>
		<ul>
		<%if(mark==Constants.BLOG_FOR_LATEST){%>
		<li class="current_page_item"><a href="index.jsp">最新博客</a></li>
		<%}else{%>	
		<li><a href="index.jsp">最新博客</a></li>
		<%}%>					
		<%if(mark==Constants.BLOG_FOR_FRIENDS){%>				
		<li class="current_page_item"><a href="friends_blog.jsp">好友动态</a></li>
		<%}else{%>
		<li><a href="friends_blog.jsp">好友动态</a></li>
		<%}%>
		<%if(mark==Constants.BLOG_FOR_USER){%>
		<li class="current_page_item"><a href="user_blog.jsp">博客</a></li>
		<%}else{%>
		<li><a href="user_blog.jsp">博客</a></li>
		<%}%>
		<li><a href="user_album.jsp">相册</a></li>
		<li><a href="user_mood.jsp">说说</a></li>
		<li><a href="user_msg.jsp">留言</a></li>
		<li><a href="user_home.jsp">我的主页</a></li>
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
				List<BlogComment> blog_comment = (ArrayList<BlogComment>) session.getAttribute("blog_comment");
				Map<Integer,List<BlogCommentReply>> blog_comment_reply_map=(HashMap<Integer,List<BlogCommentReply>>)session.getAttribute("blog_comment_reply_map");
				List current_blog=null;//curr blog
				//out.println(bc);	
				//out.println(bcr_map);
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
				for (int i = 0; i < current_blog.size(); i++) 
				{
					Blog blog = (Blog) current_blog.get(i);
					if(blog_id==blog.getBlogid())
					{
						String username = blog.getUsername();
						String blog_subject = blog.getBlogsubject();
						String blog_class = blog.getBlogclass();
						String blog_date = blog.getBlogdate();
						String blog_content = blog.getBlogcontent();
						out.println("<div id='single_list'>");
						/*****************************这里显示文章的全文信息*****************************/					
					    out.println("<div class='title'><a>文章标题：" + blog_subject + "</a></div>");
						if(!username.equals(loginuser))
						{
							out.println("<div><a>作者：" + username+ "</a></div>");
						}
						out.println("<div><a>文章类型：" + blog_class+ "</a></div>");
						String edit_str="<div class='link_style2'><a>发表时间：" + blog_date + "</a>";
						if(loginuser!=null && loginuser.equals(username))
						{
							edit_str=edit_str+"<a href='blog_edit.jsp?blog_id="+blog_id+"&mark="+mark+"'>编辑</a><a href='DeleteBlog?blog_id="+blog_id+"'>删除</a>";					
						}
						edit_str=edit_str+"</div>";
						out.println(edit_str);				
						out.println("<div><textarea class='content'  cols='60' rows='20' readonly='readonly'>"+ blog_content + "</textarea></div>");
						/************************************显示该文章的评论************************************/
						if(blog_comment==null)
						{
							out.println("<div><a>该博文还没有评论。</a></div>");
						}
						else{
							for (int j = 0; j < blog_comment.size(); j++) 
							{
								BlogComment blogcomment = (BlogComment) blog_comment.get(j);		
								int blog_comment_id=blogcomment.getBlogcommentid();
								String guest = blogcomment.getGuest();
								String blog_comment_content = blogcomment.getBlogcommentcontent();		
								String blog_comment_date = blogcomment.getBlogcommentdate();
								out.println("<div class='border'>");
								out.println("<div><a>评论人：" + guest + "</a></div>");
								out.println("<div><a>评论内容：" + blog_comment_content + "</a></div>");
								out.println("<div><a>评论时间："+blog_comment_date+"</a></div>");
								/*************************************显示评论的回复*************************************/
								if(blog_comment_reply_map!=null)
								{
									if(blog_comment_reply_map.containsKey(blog_comment_id))
									{
										List<BlogCommentReply> blog_comment_reply=blog_comment_reply_map.get(blog_comment_id);
										for(int k=0;k<blog_comment_reply.size();k++)
										{
											BlogCommentReply bcr_info=blog_comment_reply.get(k);
											int blogcommentreply_id=bcr_info.getBlogcommentreplyid();
											String blogcommentreply_content=bcr_info.getBlogcommentreplycontent();
											String blogcommentreply_date=bcr_info.getBlogcommentreplydate();
											out.println("<div class='link_style3'>");
											out.println("<div><a>回复内容：" +blogcommentreply_content+ "</a></div>");
											out.println("<div><a>回复时间："+blogcommentreply_date+"</a></div>");
											out.println("</div>");
											if(loginuser!=null && loginuser.equals(username))//loginuser.equals(username)要建立在loginuser!=null的前提下。
											{
												out.println("<div align='left'><a href='DeleteBlogCommentReply?blogcommentreply_id="
																+blogcommentreply_id+"&blog_id="+blog_id+"&mark="+mark+"'>删除回复</a></div>");
											}
										}
									}
								}
								if(loginuser!=null && loginuser.equals(username))
								{
									out.println("<div class='link_style4' align='right'><a href='javascript:showTextarea("
												+blog_comment_id+","+blog_id+","+mark+")'>回复评论</a></div>");
									out.println("<div id='blogcommentreplytextarea"+blog_comment_id+"'></div>");
									out.println("<div class='link_style4' align='right'><a href='DeleteBlogComment?blogcomment_id="
												+blog_comment_id+"&blog_id="+blog_id+"&mark="+mark+"'>删除评论</a></div>");			   							
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
					out.println("</div>");//end of single_list
					break;
					}//end of if(blog_id==lb.getBlogId())							
			    }//end of for (int i = 0; i < cb.size(); i++)
				%>
				</div>
			</div>
		</div>
	</div>
</div>
<div style="clear:both"></div>
</div>
<jsp:include page="../bottom.html" flush="true"></jsp:include>
</div>
</body>
</html>