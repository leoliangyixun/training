<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@page import="com.yangkai.myblog.domain.UserResponse"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div style="border:#E97B56 solid 1px;" align="center">
<%
	String curr_page=request.getParameter("page");
	String curr_step=request.getParameter("step");
	String loginuser=(String)session.getAttribute("loginuser");
	List<UserResponse> user_response = (List<UserResponse>) session.getAttribute("user_response");
	if(user_response!=null && user_response.size()>0)
		{
			Integer currpage=null;
			Integer countpage=null;
			int pagesize=3;
			Integer countstep=null;
			Integer currstep=null;
			int pagestep=5;
			countpage=PagingUtil.getCountpage(user_response.size(), pagesize);
			countstep=PagingUtil.getCountstep(user_response.size(), pagesize, pagestep);
			currpage=PagingUtil.getCurrentpage(curr_page, countpage);
			currstep=PagingUtil.getCurrentstep(curr_step, countstep);   
			for (int i = (currpage-1)*pagesize;i < currpage*pagesize; i++) 
			{
				if(i<user_response.size())
				{
					UserResponse userresponse= user_response.get(i);
					 int response_id = userresponse.getResponseid();
					 String username=userresponse.getUsername();
					 String guest=userresponse.getGuest();
					 String response_content = userresponse.getResponsecontent();
					 Date response_date = userresponse.getResponsedate();
					 out.println("<div id='single_list'>");
					 out.println("<div class=''>");
					 out.println("<div>");
					 out.println("<table border='0' width='570' bgcolor='#FFFF80' bordercolor='#FFFFFF' style='margin:10px auto;border-collapse: collapse;'>");
					 out.println("<tr>");
					 out.println("<td rowspan='2' width='90' align='center' valign='top' style='font-size:16px'>");
					 out.println("<img src='upload/相册/"+username+"/image/me.jpg' width='90' height='90''/></br>");
					 out.println("<a style='font-size:12px;color:red'>"+username+"</a></br></td>");
					 out.println("<td colspan='2' align='left' valign='top'>"+response_content+"</td>");
					 out.println("</tr>");
					
					 out.println("<td width='260' align='left' height='20'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(response_date)+"</td>");
					 out.println("<td align='right' width='200' height='20'><a href='javascript:showUserRequestTextarea("+response_id+","
									 +currpage+","+currstep+")'  >再次添加好友</a><a href='AcceptRequest?request_id="+response_id
									 +"&page="+currpage+"&step="+currstep+"' style='margin-left:10px'>删除</a></td>");

					 out.println("</table>");
					 out.println("</div>");
					
					
					 out.println("<div id='request_textarea"+response_id+"'></div>");
					 
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
				out.println("<a href='user_response.jsp?page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
				for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
				{
					out.println("<a href='user_response.jsp?page="+k+"&step="+currstep+"'>"+k+"</a>");
				}
				int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
				out.println("<a href='user_response.jsp?page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
			}else{
				if(countpage>1)
				{
					for(int k=1;k<=countpage;k++)
					{
						out.println("<a href='user_response.jsp?page="+k+"'>"+k+"</a>");
					}
				}
			}
			out.println("</div>");	
		}else{
			out.println("<div align='center'><a class='success_style'>没有请求回复!!!</a></div>");
		}
%>
			</div>
