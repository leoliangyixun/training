<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.domain.MessageReply"%>
<%@page import="com.yangkai.myblog.tools.PagingParamTool"%>
<%@page import="com.yangkai.myblog.domain.Message"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
	<title>����</title>
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
		<li><a href="user_message.jsp">�ҵ�����</a></li>
		<li class="current_page_item"><a href="my_message.jsp">���Թ���</a></li>
	</ul>
</div>
</div>
</div>
<div id="list">
<div id="list_top">
	<div id="list_middle">
		<div id="list_bottom">
			<div id="blog_list">
				<div id="message_style">
					<a href="my_message.jsp">���ҵ�����</a>
					<a href="my_leave_message.jsp">��д������</a>
					<a href="my_leave_message_friends.jsp">����������</a>
					<a href="my_message_list.jsp" style="background-color:#E97B56;">���Թ���</a>
				</div>
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
					out.println("<table border='0' width='580' cellspacing='0' cellpadding='4' align='center'>");
					out.println("<tr style='font-size:14px;'><td width='150'>������</td ><td>����ժҪ</td><td width='80' align='center'>����ʱ��</td><td width='120' align='center'>���Բ���</td></tr>");
					for (int i = (currpage-1)*pagesize;i < currpage*pagesize; i++) 
					{
						if(i<user_message.size())
						{
							int message_id=user_message.get(i).getMessageid();
							String guest=user_message.get(i).getGuest();
							String message_date=user_message.get(i).getMessagedate();
							out.println("<tr><td><a>"+guest+"</a></td>"
									   +"<td></td>"
									   +"<td align='center'><a>"+message_date+"</a></td>"
							           +"<td><span><a href='message_view.jsp?message_id="+message_id+"'>�鿴</a><a href='#'>�ظ�</a><a href='javascript:deleteMessageBox("+message_id+","+currpage+","+currstep+")'>ɾ��</a></span></td></tr>");
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
					out.println("<div align='center'><a  class='success_style'>���԰�Ϊ��!!!</a></div>");
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