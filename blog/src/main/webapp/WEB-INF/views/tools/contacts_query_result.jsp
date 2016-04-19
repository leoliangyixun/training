<%@page import="java.net.URLDecoder"%>
<%@page import="com.yangkai.myblog.tools.EncoderUtil"%>
<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="com.yangkai.myblog.domain.Contacts"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String name=request.getParameter("name");
	List<Contacts> contacts_query_result=(List<Contacts>)request.getAttribute("contacts_query_result");
	String curr_page=request.getParameter("page");
	String curr_step=request.getParameter("step");
	
	if(contacts_query_result!=null){
		out.println("<div style='margin-top:20px'>");
		out.println("<div align='center'>查询结果</div>");
		out.println("<table width='490' border='1' style='margin:10px auto;border-collapse: collapse;'>");
		out.println("<tr align='center'>"
				+"<td width='90'>姓名</td>"
				+"<td width='100'>手机</td>"
				+"<td width='90'>QQ</td>"
				+"<td width='130'>E-mail</td>"
				+"<td width='80'><a >操作</a></td>"
				+"</tr>"); 
		Integer currpage=null;
		Integer countpage=null;
		int pagesize=3;
		Integer countstep=null;
		Integer currstep=null;
		int pagestep=5;
		countpage=PagingUtil.getCountpage(contacts_query_result.size(), pagesize);
		countstep=PagingUtil.getCountstep(contacts_query_result.size(), pagesize, pagestep);
		currpage=PagingUtil.getCurrentpage(curr_page, countpage);
		currstep=PagingUtil.getCurrentstep(curr_step, countstep);   
		for(int i=(currpage-1)*pagesize;i<currpage*pagesize;i++)
		{
			if(i<contacts_query_result.size())
			{
				out.println("<tr align='center'>");
				out.println("<td>"+contacts_query_result.get(i).getName()+"</td>");
				out.println("<td>"+(contacts_query_result.get(i).getTelephone()==null ? "" : contacts_query_result.get(i).getTelephone())+"</td>");
				out.println("<td>"+(contacts_query_result.get(i).getQq()==null ? "" : contacts_query_result.get(i).getQq())+"</td>");
				out.println("<td>"+(contacts_query_result.get(i).getMail()==null ? "" : contacts_query_result.get(i).getMail())+"</td>");
				out.println("<td><a href='javascript:showContactsAlterArea("+contacts_query_result.get(i).getContactsid()+")'>修改</a> | <a href=''>删除</a></td>");
				out.println("</tr>");
			}
		}
			
			out.println("</table>");
			out.println("</div>");
		
			out.println("<div align='center' id='pages' >");
			if(countpage>pagestep)
			{   	
				out.println("<a href='javascript:showQueryContacts(document.contactsQueryForm.name.value,"+(pagestep*(currstep-1))+","+(currstep-1)+")'>&laquo;</a>");
				for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
				{	
					out.println("<a href='javascript:showQueryContacts(document.contactsQueryForm.name.value,"+k+","+currstep+")'>"+k+"</a>");
				}
				int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
				out.println("<a href='javascript:showQueryContacts(document.contactsQueryForm.name.value,"+max+","+(currstep+1)+")'>&raquo;</a>");	
			}else{
				if(countpage>1)
				{
					for(int k=1;k<=countpage;k++)
					{
						out.println("<a href='javascript:showQueryContacts(document.contactsQueryForm.name.value,"+k+","+null+")'>"+k+"</a>");
					}
				}
			}
			out.println("</div>");	
	

			
		}else{
			out.println("<div style='margin-top:20px'>");
			out.println("<div align='center'>查询结果</div>");
			out.println("<table width='490' border='1' style='margin:10px auto;border-collapse: collapse;'>");
			out.println("<tr align='center'><td width='90'>姓名</td><td width='125'>手机</td><td width='90'>QQ</td><td width='125'>E-mail</td><td width='60'><a >操作</a></td></tr>"); 
			out.println("<tr><td colspan='5' align='center'>没有查到相关信息！！！</td></tr>");
			out.println("</table>");
			out.println("</div>");
		}
%>
