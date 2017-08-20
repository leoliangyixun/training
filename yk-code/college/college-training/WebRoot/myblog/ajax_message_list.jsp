<%@page import="com.yangkai.myblog.tools.com.yangkai.myblog.tools.PageApart"%>
<%@page import="com.yangkai.myblog.domain.Message"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@page import="com.yangkai.myblog.domain.Blog"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
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
	    countpage=PageApart.getCountpage(user_message.size(), pagesize);
	    countstep=PageApart.getCountstep(user_message.size(), pagesize, pagestep);
	    currpage=PageApart.getCurrentpage(curr_page, countpage);
	    currstep=PageApart.getCurrentstep(curr_step, countstep); 
		out.println("<div id='subject_list'>");
		out.println("<table border='0' width='580' cellspacing='0' cellpadding='4' align='center'>");
		out.println("<tr style='font-size:14px;'><td width='150'>ÁôÑÔÈË</td ><td>ÁôÑÔÕªÒª</td><td width='80' align='center'>ÁôÑÔÊ±¼ä</td><td width='80'>ÁôÑÔ²Ù×÷</td></tr>");
		for (int i = (currpage-1)*pagesize;i < currpage*pagesize; i++) 
		{
	if(i<user_message.size())
	{
		int message_id=user_message.get(i).getMessageid();
		String guest=user_message.get(i).getGuest();
		String message_date=user_message.get(i).getMessagedate();
		out.println("<tr><td><a href=''>"+guest+"</a></td>"
				   +"<td></td>"
				   +"<td align='center'><a>"+message_date+"</a></td>"
		           +"<td><span><a href='message_view.jsp?message_id="+message_id+"'>²é¿´</a><a href='javascript:deleteMessageBox("+message_id+","+currpage+","+currstep+")'>É¾³ı</a></span></td></tr>");
	}
		}
		out.println("</table>");
		out.println("</div>");
	
	    out.println("<div align='center' id='pages'>");
	    if(countpage>pagestep)
	    {   	
	    	out.println("<a href='javascript:manageMessageBox("+(pagestep*(currstep-1))+","+(currstep-1)+")'>&laquo;</a>");
	    	for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
	    	{	
	    		out.println("<a href='javascript:manageMessageBox("+k+","+currstep+")'>"+k+"</a>");
	    	}
	    	int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
	    	out.println("<a href='javascript:manageMessageBox("+max+","+(currstep+1)+")'>&raquo;</a>");	
	    }else{
	    	if(countpage>1)
	    	{
	    		for(int k=1;k<=countpage;k++)
	    		{
	    			out.println("<a href='javascript:manageMessageBox("+k+","+null+")'>"+k+"</a>");
	    		}
	    	}
	    }
	    out.println("</div>");	
	}else{
		out.println("<div align='center'><a  class='success_style'>ÁôÑÔ°åÎª¿Õ!!!</a></div>");
	}
%>
