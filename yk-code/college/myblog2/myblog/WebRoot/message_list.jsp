<%@page import="java.text.DateFormat"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.domain.MessageReply"%>
<%@page import="com.yangkai.myblog.tools.PagingParamTool"%>
<%@page import="com.yangkai.myblog.domain.Message"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>留言管理</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/myblog.js"></script>
	</head>

<body>
<div>
<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
<div id="content">
<div id="menu_up">
<div id="menu">
<div>
	<ul>
		<li><a href="user_message.jsp">我的留言</a></li>
		<li class="current_page_item"><a href="message_manage.jsp">留言管理</a></li>
	</ul>
</div>
</div>
</div>
<div id="list">
<div id="list_top">
	<div id="list_middle">
		<div id="list_bottom">
			<div id="blog_list">
				<!--div id="message_style">
					<a href="my_leave_message.jsp">我写的留言</a>
					<a href="message_to_friends.jsp">给好友留言</a>
					<a href="message_list.jsp" style="background-color:#E97B56;">留言列表</a>
				</div-->
			<%
		    String curr_page=request.getParameter("page");
		    String curr_step=request.getParameter("step");
			List<Message> user_message=(List<Message>)session.getAttribute("user_message");	
			if(user_message!=null && user_message.size()>0)
			{
			    Integer currpage=null;
			    Integer countpage=null;
			    int pagesize=3;
			    Integer countstep=null;
			    Integer currstep=null;
			    int pagestep=5;
			    countpage=PagingParamTool.getCountpage(user_message.size(), pagesize);
			    countstep=PagingParamTool.getCountstep(user_message.size(), pagesize, pagestep);
			    currpage=PagingParamTool.getCurrentpage(curr_page, countpage);
			    currstep=PagingParamTool.getCurrentstep(curr_step, countstep); 
				out.println("<div id='subject_list'>");
				out.println("<table border='0' width='580' cellspacing='0' cellpadding='4' align='center' style='font-size:12px;'>");
				out.println("<tr><td width='100'>留言人</td><td>留言摘要</td><td width='130' align='center'>留言时间</td><td width='120' align='center'>留言操作</td></tr>");
				for (int i = (currpage-1)*pagesize;i < currpage*pagesize; i++) 
				{
					if(i<user_message.size())
					{
						int message_id=user_message.get(i).getMessageid();
						String guest=user_message.get(i).getGuest();
						Date message_date=user_message.get(i).getMessagedate();
						out.println("<tr><td align='left'><a>"+guest+"</a></td>"
								   +"<td align='left'>this is the main content</td>"
								   +"<td align='center'><a>"+DateFormat.getDateTimeInstance().format(message_date)+"</a></td>"
						           +"<td align='center'><span ><a href='message_view.jsp?message_id="+message_id+"' style='font-size:12px;'>查看</a><a href='#' style='font-size:12px;'>回复</a><a href='DeleteMessage?message_id="+message_id
					 	 		   +"&page="+currpage+"&step="+currstep+"&message_mark="+Constants.DELETE_MESSAGE_FROM_MESSAGE_LIST+"' style='font-size:12px;'>删除</a></span></td></tr>");
					}
				}
				out.println("</table>");
				out.println("</div>");
				out.println("<div align='center' id='pages'>");
				if(countpage>=pagestep)
				{
					out.println("<a href='my_message_list.jsp?page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
					for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
					{
						out.println("<a href='my_message_list.jsp?page="+k+"&step="+currstep+"'>"+k+"</a>");
					}
					int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
					out.println("<a href='my_message_list.jsp?page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
				}else{
					if(countpage>1)
					{
						for(int k=1;k<=countpage;k++)
						{
							out.println("<a href='my_message_list.jsp?page="+k+"'>"+k+"</a>");
						}
					}
				}
				out.println("</div>");
			}else{
				out.println("<div align='center'><a  class='success_style'>留言板为空!!!</a></div>");
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