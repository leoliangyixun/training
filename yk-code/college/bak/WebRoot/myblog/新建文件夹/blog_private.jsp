<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@page import="com.yangkai.myblog.tools.com.yangkai.myblog.tools.PageApart"%>
<%@page import="com.yangkai.myblog.domain.Blog"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>My JSP 'index.jsp' starting page</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>
   <%
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
		 <li><a href="user_blog.jsp">�ҵĲ���</a></li>
		 <li><a href="blog_add.jsp">д����</a></li>
		 <li class="current_page_item"><a href="blog_private.jsp">˽����־</a></li>	
		 <li><a href="blog_draft.jsp">�ݸ���</a></li>	
		 <li><a href="blog_list.jsp">���Ĺ���</a></li>	
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
			     	List<Blog> private_blog = (ArrayList<Blog>) session.getAttribute("private_blog");
			     		    if(private_blog!=null && private_blog.size()>0)
			     		    {
			     			    Integer currpage=null;
			     				Integer countpage=null;
			     				int pagesize=5;
			     				Integer countstep=null;
			     				Integer currstep=null;
			     				int pagestep=5;
			     				countpage=PageApart.getCountpage(private_blog.size(), pagesize);
			     				countstep=PageApart.getCountstep(private_blog.size(), pagesize, pagestep);
			     				currpage=PageApart.getCurrentpage(curr_page, countpage);
			     				currstep=PageApart.getCurrentstep(curr_step, countstep);
			     		    	for (int i = (currpage-1)*pagesize; i < currpage*pagesize; i++) 
			     		    	{
			     			    	if(i<private_blog.size())
			     			    	{
			     				         Blog blog = (Blog) private_blog.get(i);
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
			     				         out.println("<div class='title'><a>���±��⣺" + blog_subject
			     				                   + "</a></div><div class='tag'><a>�������ͣ�" 
			     				         		   + blog_class + "</a></div>");
			     				         out.println("<div>" + blog_content + "</div>");
			     				         out.println("<div align='left' class='link_style'><a>����ʱ�䣺"
			     				                 	 + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(blog_date) + "</a><span style='margin-left:158px;'><a href='ViewBlog?blog_id="+blog_id+"&mark="
			     				         			 +Constants.BLOG_FOR_PRIVATE+"&blog_link_mark="+Constants.BLOG_LINK_MARK_PRIVATE+"'>�鿴</a><a href='blog_edit.jsp?blog_id="+blog_id+"&mark="
			     						         			 +Constants.BLOG_FOR_PRIVATE+"&blog_link_mark="+Constants.BLOG_LINK_MARK_PRIVATE+"'>�༭</a>"
			     				                     + "<a href='DeleteBlog?blog_id="+blog_id+"&mark="+Constants.BLOG_FOR_PRIVATE+"'>ɾ��</a><a href='AlterBlogState?blog_id="+blog_id+"&type="+Constants.BLOG_FOR_PRIVATE+"'>תΪ��ͨ��־</a></span>");
			     				         out.println("</div>");
			     			    	}
			     			    	else{//for if(ub_list!=null)
			     			    		break;
			     		    		}
			     		   		}//end of for
			     			    out.println("<div align='center' id='pages'>");
			     			    if(countpage>=pagestep)
			     			    {
			     			    	out.println("<a href='blog_private.jsp?page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
			     			    	for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
			     			    	{
			     			    		out.println("<a href='blog_private.jsp?page="+k+"&step="+currstep+"'>"+k+"</a>");
			     			    	}
			     			    	int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
			     			    	out.println("<a href='blog_private.jsp?page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
			     			    }else{
			     			    	if(countpage>1)
			     			    	{
			     			    		for(int k=1;k<=countpage;k++)
			     			    		{
			     			    			out.println("<a href='blog_private.jsp?page="+k+"'>"+k+"</a>");
			     			    		}
			     			    	}
			     			    }
			     			    out.println("</div>");	
			     		    }
			     		    else{
			     		    	   out.println("<div align='center'><a class='success_style'>��û��д˽����־!!!</a></div>");
			     		    	   
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

