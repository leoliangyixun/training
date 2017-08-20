<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.yangkai.myblog.domain.User"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.tools.PagingParamTool"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.yangkai.myblog.domain.Blog"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <title>博客</title>
    <link href="css/style.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="js/myblog.js"></script>
  </head>
  
  <body>
   <%
   int size=0;
   String curr_page=request.getParameter("page");
   String curr_step=request.getParameter("step");
   User bloger=(User)session.getAttribute("bloger");
   List<Blog> user_blog = (List<Blog>) session.getAttribute("user_blog");
   String link_blog_class=null;
   if(request.getParameter("size")==null)
   {
	   if(user_blog!=null && user_blog.size()>0)
	   {
			size=user_blog.size();
	   }
   }else{   
	   link_blog_class=request.getParameter("link_blog_class");
	   size=Integer.parseInt(request.getParameter("size"));
   }
   
   %>	
   <div>
   <div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
   <div id="content">
   <div id="menu_up">
   <div id="menu">
	 <div>
		 <ul>
		 <li class="current_page_item"><a href="user_blog.jsp">我的博客</a></li>
		 <li><a href="blog_add.jsp">写博客</a></li>
		 <li><a href="blog_private.jsp">私密日志</a></li>	
		 <li><a href="blog_draft.jsp">草稿箱</a></li>	
		 <li><a href="blog_list.jsp">博客管理</a></li>		
		 </ul>
	 </div>
	 </div><!--end of menu-->
	 
	  <div style="margin-left:10px;margin-top: 20px;font-size: 22px;color: #093;">博主信息</div>
	  <div style="border: #093 solid 1px;width:270px;margin-left:10px">
	  <div class="info">
	  <table border="0" width="280" cellpadding="0" cellspacing="4" style="border-collapse: collapse;">
	  <tr>
	    <td colspan="2" align="center"><img src="upload/相册/<%=bloger.getUsername() %>/image/me.jpg"  width="200" height="200"/></td>
	  </tr>
	  <tr>
	    <td colspan="2" align="center"><%=bloger.getUsername() %>，<%=bloger.getSex()%></td> 
	  </tr>

	  <tr>
	    <td width="80">文章数：</td>
	    <td><%=bloger.getBlognum()%></td>
	  </tr>
	  <tr>
	    <td >所在地区：</td>
	    <td><%=bloger.getAddress()%></td>
	  </tr>
	  <tr>
	    <td>注册时间：</td>
	    <td><%=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(bloger.getRegisttime())%></td>
	  </tr>
	</table>
	</div><!-- end of list -->
	</div>
	 
	 <div style="margin-left:10px;margin-top: 20px;font-size: 22px;color: #093;">博文分类</div>
	 <div style="border: #093 solid 1px;width:270px;margin-left:10px">
	 <div class="list"><a href="user_blog.jsp">所有博文</a></div>
	 <% 
	   List<String> user_blog_class=(List<String>)session.getAttribute("user_blog_class");
 	   if(user_blog_class!=null && user_blog_class.size()>0)
       {
 		  if(user_blog_class.size()<5)
 		  {
 			  for(int i=0;i<user_blog_class.size();i++)
 			  {	
 				  out.println("<div class='list'><a href='SearchBlogByClass?link_blog_class="
 			                 +URLEncoder.encode(user_blog_class.get(i), "UTF-8")+"'>"
 						     +user_blog_class.get(i)+"</a></div>");
 			  }
 		  }else{
			  for(int i=0;i<5;i++)
			  {	
				  out.println("<div class='list'><a href='SearchBlogByClass?link_blog_class="
			                 +URLEncoder.encode(user_blog_class.get(i), "UTF-8")+"'>"
						     +user_blog_class.get(i)+"</a></div>");
			  }
			  out.println("<form action='SearchBlogByClass' method='get' name='moreBlogClassItems'>");
			  out.println("<div class='list'><a href='javascript:showMoreBlogClassItems()' style='font-style:italic;'>更多分类>></a><a id='more_blogclass_items'></a></div>");
			  out.println("<form>");
 		  }
 	   }
	 %>
	 </div>

     </div>

     <div id="list">
     <div id="list_top">
     <div id="list_middle">
     <div id="list_bottom">
     <div id="blog_list">
     <% 
        
        if(user_blog!=null && user_blog.size()>0)
        {
	        Integer currpage=null;
	  	    Integer countpage=null;
	  	    int pagesize=10;
	  	    Integer countstep=null;
	  	    Integer currstep=null;
	  	    int pagestep=5;
	  	    if(size>0)
	  	    {
				countpage=PagingParamTool.getCountpage(size, pagesize);
				countstep=PagingParamTool.getCountstep(size, pagesize, pagestep);
				currpage=PagingParamTool.getCurrentpage(curr_page, countpage);
				currstep=PagingParamTool.getCurrentstep(curr_step, countstep);   
				if(link_blog_class!=null)
				{
					for (int i = (currpage-1)*pagesize;; i++)
					{
						int m=1;
						if(i<user_blog.size())
						{
							if(user_blog.get(i).getBlogclass().equals(link_blog_class))
							{
								 m++;
								 Blog blog= user_blog.get(i);
								 int blog_id = blog.getBlogid();
								 String blog_subject =blog.getBlogsubject();
								 String blog_class = blog.getBlogclass();
								 Date blog_date = blog.getBlogdate();
								 String blog_content = blog.getBlogcontent();
								 if(blog_content.length()>100)
								 {
									 blog_content=blog_content.substring(0, 100)+"......";
								 }
								 out.println("<div id='single_list'>");
								 out.println("<div class='title'><a>文章标题：" + blog_subject
										   + "</a></div><div class='tag'><a>文章类型：" 
										   + blog_class + "</a></div>");
								 out.println("<div>" + blog_content + "</div>");
								 out.println("<div align='left' class='link_style'><a>发表时间："
											 + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(blog_date) + "</a><span style='margin-left:80px;'><a href='ViewBlog?blog_id="+blog_id+"&mark="
											 + Constants.BLOG_FOR_USER+"'>阅读</a><a href='blog_edit.jsp?blog_id="+blog_id+"&mark="+Constants.BLOG_FOR_USER+"'>编辑</a>"
											 + "<a href='DeleteBlog?blog_id="+blog_id+"&mark="+Constants.BLOG_FOR_USER+"'>删除</a>"
											 + "<a href='javascript:sendBlogCommentRequest("+blog_id+")'>查看评论</a><a href='AlterBlogState?blog_id="+blog_id+"&type="+Constants.BLOG_FOR_USER+"'>转为私密日志</a></span></div>");						
								 out.println("<div id='blog_comment"+blog_id+"'></div>");
								 out.println("</div>");
								 if(m>=5){
									 break;
								}
							}else{
								continue;
							}
						}
						else{//for if(ub_list!=null)
							break;
						}
					}//end of for
				}else{
					for (int i = (currpage-1)*pagesize;i < currpage*pagesize; i++) 
					{
						if(i<user_blog.size())
						{
								 Blog blog= user_blog.get(i);
								 int blog_id = blog.getBlogid();
								 String blog_subject =blog.getBlogsubject();
								 String blog_class = blog.getBlogclass();
								 Date blog_date = blog.getBlogdate();
								 String blog_content = blog.getBlogcontent();
								 if(blog_content.length()>100)
								 {
									 blog_content=blog_content.substring(0, 100)+"......";
								 }
								 out.println("<div id='single_list'>");
								 out.println("<div class='title'><a>文章标题：" + blog_subject
										   + "</a></div><div class='tag'><a>文章类型：" 
										   + blog_class + "</a></div>");
								 out.println("<div>" + blog_content + "</div>");
								 out.println("<div align='left' class='link_style'><a>发表时间："+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(blog_date) + "</a>"
											+"<span style='margin-left:80px;'>" 
											+"<a href='ViewBlog?blog_id="+blog_id+"&mark="+Constants.BLOG_FOR_USER+"'>阅读</a>"
											+"<a href='blog_edit.jsp?blog_id="+blog_id+"&mark="+Constants.BLOG_FOR_USER+"'>编辑</a>"
											+ "<a href='DeleteBlog?blog_id="+blog_id+"&mark="+Constants.BLOG_FOR_USER+"'>删除</a>"
											+"<a href='javascript:sendBlogCommentRequest("+blog_id+")'>查看评论</a><a href='AlterBlogState?blog_id="+blog_id+"&type="+Constants.BLOG_FOR_USER+"'>转为私密日志</a></span></div>");						
								 out.println("<div id='blog_comment"+blog_id+"'></div>");
								 out.println("</div>");
						}
						else{//for if(i<user_blog.size())
							break;
						}
					}//end of for
			    }
				out.println("<div align='center' id='pages'>");
				if(countpage>pagestep)
				{
					if(link_blog_class!=null)
					{
						out.println("<a href='user_blog.jsp?link_blog_class="+URLEncoder.encode(link_blog_class, "UTF-8")+"&page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
					}else{
						out.println("<a href='user_blog.jsp?page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
					}
					for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
					{
						if(link_blog_class!=null)
						{
							out.println("<a href='user_blog.jsp?link_blog_class="+URLEncoder.encode(link_blog_class, "UTF-8")+"&page="+k+"&step="+currstep+"'>"+k+"</a>");
						}else{
							out.println("<a href='user_blog.jsp?page="+k+"&step="+currstep+"'>"+k+"</a>");
						}
					}
					int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
					if(link_blog_class!=null)
					{
						out.println("<a href='user_blog.jsp?link_blog_class="+URLEncoder.encode(link_blog_class, "UTF-8")+"&page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
					}else{
						out.println("<a href='user_blog.jsp?page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
					}
				}else{
					if(countpage>1)//==1就不显示分页角标。
					{
						for(int k=1;k<=countpage;k++)
						{
							if(link_blog_class!=null)
							{
								out.println("<a href='user_blog.jsp?link_blog_class="+URLEncoder.encode(link_blog_class, "UTF-8")+"&page="+k+"'>"+k+"</a>");
							}else{
								out.println("<a href='user_blog.jsp?page="+k+"'>"+k+"</a>");
							}
						}
					}
				}
				out.println("</div>");	
			}else{
					out.println("<div align='center'><a class='success_style'>你还没有发表该类别的博文!!!</a><a href='blog_add.jsp'  class='continue_style'>现在发表</a></div>");
			}
	    }else{
	  	    out.println("<div align='center'><a  class='success_style'>你还没有发表博客!!!</a><a href='blog_add.jsp' class='continue_style'>写博客</a></div>");
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
