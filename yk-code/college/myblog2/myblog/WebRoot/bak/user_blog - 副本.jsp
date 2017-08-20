<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.tools.PagingParamTool"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
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
   List<Blog> user_blog = (ArrayList<Blog>) session.getAttribute("user_blog");
   String link_blog_class=null;
   if(request.getParameter("size")==null || Integer.parseInt(request.getParameter("size"))==0)
   {
	   size=user_blog.size();
   }else{   
	   //link_blog_class=request.getParameter("link_blog_class");
	   size=Integer.parseInt(request.getParameter("size"));
   }
   link_blog_class=request.getParameter("link_blog_class");
   %>	
   <div>
   <div id="nav"><jsp:include page="../top.jsp" flush="true"></jsp:include></div>
   <div id="content">
   <div id="menu_up">
   <div id="menu">
	 <div>
		 <ul>
		 <li class="current_page_item"><a href="user_blog.jsp">我的博客</a></li>
		 <li><a href="blog_add.jsp">写博客</a></li>
		 <li><a href="blog_manage.jsp">博客管理</a></li>		
		 </ul>
	 </div>
	 </div><!--end of menu-->
	 <div style="margin-left:10px;margin-top: 20px;font-size: 22px;color: #093;">日志分类</div>
	 <%
	 List<String> user_blog_class=(ArrayList<String>)session.getAttribute("user_blog_class");
 	 if(user_blog_class!=null)
 	 {
		for(int i=0;i<user_blog_class.size();i++)
		{
			
			out.println("<div class='list'><a href='user_blog.jsp?link_blog_class="+URLEncoder.encode(user_blog_class.get(i), "UTF-8")+"'>"+user_blog_class.get(i)+"</a></div>");
		}
 	 }
	 %>
	 <!--div style="margin-left:10px;margin-top: 10px;font-size: 14px;">
	 	<table border="1" width="270"  bordercolor="#000000" style="border-collapse: collapse">
		  <tr>
		    <td width="80">相册名：</td>
		   
		  </tr>
		  <tr>
		    <td>照片数：</td>
		  </tr>
		  <tr>
		    <td>相册描述：</td>
		
		  </tr>
		  <tr>
		    <td>创建时间：</td>
		  </tr>
		</table>
	 </div-->
     </div>

     <div id="list">
     <div id="list_top">
     <div id="list_middle">
     <div id="list_bottom">
     <div id="blog_list">
     <%     
        if(user_blog!=null)
        {
	        Integer currpage=null;
	  	    Integer countpage=null;
	  	    int pagesize=5;
	  	    Integer countstep=null;
	  	    Integer currstep=null;
	  	    int pagestep=5;
	  	    countpage=PagingParamTool.getCountpage(size, pagesize);
	  	    countstep=PagingParamTool.getCountstep(size, pagesize, pagestep);
	  	    currpage=PagingParamTool.getCurrentpage(curr_page, countpage);
	  	    currstep=PagingParamTool.getCurrentstep(curr_step, countstep);
	 	    for (int i = (currpage-1)*pagesize; i < currpage*pagesize; i++) //要从新设计。
	    	{
		    	if(i<user_blog.size())
		    	{
		    		Blog blog=null;
		    		if(link_blog_class!=null)
		    		{
		    			if(user_blog.get(i).getBlogclass().equals(link_blog_class))
		    			{
					          blog= user_blog.get(i);

		    			}else{
		    				continue;
		    			}
		    			
		    		}//end of if(link_blog_class!=null)
		    		else{
				         blog= user_blog.get(i);
				    }
				         int blog_id = blog.getBlogid();
				         String blog_subject =blog.getBlogsubject();
				         String blog_class = blog.getBlogclass();
				         String blog_date = blog.getBlogdate();
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
				                 	 + blog_date + "</a><a href='ViewBlog?blog_id="+blog_id+"&mark="
				         			 +Constants.BLOG_FOR_USER+"'>阅读</a>"
				                     + "<a href='DeleteBlog?blog_id="+blog_id+"&mark="+Constants.BLOG_FOR_USER+"'>删除</a>"
				                 	 +"<a href='javascript:sendBlogCommentRequest("+blog_id+")'>查看评论</a></div>");						
				         out.println("<div id='blogcomment"+blog_id+"'></div>");
				         out.println("</div>");
		    	}
		    	else{//for if(ub_list!=null)
		    		break;
	    		}
	    }//end of for
	    out.println("<div align='center' id='pages'>");
	    if(countpage>=pagestep)
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
	    	if(countpage>1)
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
	}
	else{
	  	out.println("<div><a>你还没有发表博客!!!</a></div>");
	}
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
