<%@page import="java.net.URLEncoder"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="com.yangkai.myblog.domain.UserRequest"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div style="border:#E97B56 solid 1px;" align="center">
<%
	String curr_page=request.getParameter("page");
	String curr_step=request.getParameter("step");
	String loginuser=(String)session.getAttribute("loginuser");
	List<UserRequest> user_request = (List<UserRequest>) session.getAttribute("user_request");
	if(user_request!=null && user_request.size()>0)
	{
		Integer currpage=null;
		Integer countpage=null;
		int pagesize=3;
		Integer countstep=null;
		Integer currstep=null;
		int pagestep=5;
		countpage=PagingUtil.getCountpage(user_request.size(), pagesize);
		countstep=PagingUtil.getCountstep(user_request.size(), pagesize, pagestep);
		currpage=PagingUtil.getCurrentpage(curr_page, countpage);
		currstep=PagingUtil.getCurrentstep(curr_step, countstep);   
		for (int i = (currpage-1)*pagesize;i < currpage*pagesize; i++) 
		{
			if(i<user_request.size())
			{
				 UserRequest userrequest= user_request.get(i);
				 int request_id = userrequest.getRequestid();
				 String guest=userrequest.getGuest();
				 String request_content = userrequest.getRequestcontent();
				 Date request_date = userrequest.getRequestdate();
				 out.println("<div id='single_list'>");
				 out.println("<div class=''>");
				 out.println("<div>");
				 out.println("<table border='0' width='570' bgcolor='#FFFF80' bordercolor='#FFFFFF' style='margin:10px auto;border-collapse: collapse;'>");
				 out.println("<tr>");
				 out.println("<td rowspan='2' width='90' align='center' valign='top' style='font-size:16px'>");
				 out.println("<img src='upload/相册/"+guest+"/image/me.jpg' width='90' height='90''/></br>");
				 out.println("<a style='font-size:12px;color:red'>"+guest+"</a></br></td>");
				 out.println("<td colspan='2' align='left' valign='top'>"+request_content+"</td>");
				 out.println("</tr>");
				
				 out.println("<td width='260' align='left' height='20'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(request_date)+"</td>");
				 out.println("<td align='right' width='200' height='20'><a href='AcceptUserRequest?request_id="+request_id
								 +"&friend_name="+URLEncoder.encode(guest, "UTF-8")+"&page="+currpage+"&step="+currstep+"' >接受</a><a href='javascript:showUserResponseReplyTextarea("+request_id+","
								 +currpage+","+currstep+")'  style='margin-left:10px'>拒绝</a></td>");

				 out.println("</table>");
				 out.println("</div>");
				
				
				 out.println("<div id='response_textarea"+request_id+"'></div>");
				 
				 out.println("</div>");//end of border
				 out.println("</div>");//end of single_list
				
			}
			else{
				break;
			}
		}//end of for
		
	
				
		out.println("<div align='center' id='pages'>");
		if(countpage>pagestep)
		{
			out.println("<a href='user_request.jsp?page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
			for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
			{
				out.println("<a href='user_request.jsp?page="+k+"&step="+currstep+"'>"+k+"</a>");
			}
			int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
			out.println("<a href='user_request.jsp?page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
		}else{
			if(countpage>1)
			{
				for(int k=1;k<=countpage;k++)
				{
					out.println("<a href='user_request.jsp?page="+k+"'>"+k+"</a>");
				}
			}
		}
		out.println("</div>");	
	}else{
		out.println("<div align='center'><a class='success_style'>没有好友请求!!!</a></div>");
	}
%>	
</div>
