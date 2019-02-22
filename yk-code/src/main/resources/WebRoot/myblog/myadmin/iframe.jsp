<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@page import="com.yangkai.myblog.domain.Blog"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>My JSP 'index.jsp' starting page</title>
	<!--link href="css/style.css" rel="stylesheet" type="text/css" /-->
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>
 <%
        String curr_page=request.getParameter("page");                   
	    List<Blog> user_blog = (ArrayList<Blog>) session.getAttribute("user_blog");
	    if(user_blog!=null)
	    {
	    Integer currpage=null;
		Integer countpage=null;
		int pagesize=5;
		if(user_blog.size()%pagesize==0)
		{
			countpage=user_blog.size()/pagesize;
		}
		else{
			countpage=user_blog.size()/pagesize+1;
		}
		if(curr_page==null)
		{
			currpage=1;
		}
		else{
			currpage=Integer.parseInt(curr_page);
		}
		if(currpage<1)
		{
			currpage=1;
		}
		if(currpage>countpage)
		{
			currpage=countpage;
		}
	    	for (int i = (currpage-1)*pagesize; i < currpage*pagesize; i++) 
	    	{
		    	if(i<user_blog.size())
		    	{
			         Blog blog_info = (Blog) user_blog.get(i);
			         int blog_id = blog_info.getBlogid();
			         String blog_subject =blog_info.getBlogsubject();
			         String blog_class = blog_info.getBlogclass();
			         String blog_date = blog_info.getBlogdate();
			         String blog_content = blog_info.getBlogcontent();
			         if(blog_content.length()>100)
			         {
			        	 blog_content=blog_content.substring(0, 100)+"......";
			         }
			         out.println("<div id='single_list'>");
			         out.println("<div class='title'><a>���±��⣺" + blog_subject
			                   + "</a></div><div class='tag'><a>�������ͣ�" 
			         		   + blog_class + "</a></div>");
			         out.println("<div>" + blog_content + "</div>");
			         out.println("<div align='left' class='link_style'><a>����ʱ�䣺"
			                 	 + blog_date + "</a><a href='ViewBlog?blog_id="+blog_id+"&mark="+Constants.BLOG_FOR_USER+"'>�Ķ�</a>"
			                     + "<a href='DeleteBlog?blog_id="+blog_id+"'>ɾ��</a>"
			                 	 +"<a href='javascript:sendBlogCommentRequest("+blog_id+")'>�鿴����</a></div>");						
			         out.println("<div id='blogcomment"+blog_id+"'></div></div>");
		    	}
		    	else{//for if(ub_list!=null)
		    		break;
	    		}
	    }//end of for
    	out.println("<div align='center' id='page'>");
	    out.println("<a href='iframe.jsp?page=1'>��ҳ</a>");
	    out.println("<a href='iframe.jsp?page="+(currpage-1)+"'>��һҳ</a>");
	    out.println("<a href='iframe.jsp?page="+(currpage+1)+"'>��һҳ</a>");
        out.println("<a href='iframe.jsp?page="+countpage+"'>βҳ</a>");
    	out.println("</div>");
	    }
	    else{
	    	   out.println("<div><a>�㻹û�з�����!!!</a></div>");
	    }
	%>                 		
</body>
</html>

