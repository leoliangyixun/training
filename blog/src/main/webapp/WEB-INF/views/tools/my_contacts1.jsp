<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.domain.Contacts"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	Integer index=null;
	Integer pointer=null;
	String temptory_index=request.getParameter("index");
	String temptory_pointer=request.getParameter("pointer");
	if(temptory_index!=null && !temptory_index.equals("null")){
		index=Integer.parseInt(temptory_index);
	}
	if(temptory_pointer!=null && !temptory_pointer.equals("null")){
		pointer=Integer.parseInt(temptory_pointer);
	}else{
		pointer=0;
	}

	String curr_page=request.getParameter("page");
	String curr_step=request.getParameter("step");
	List<String> user_contacts_class=(List<String>)session.getAttribute("user_contacts_class");
	List<Contacts> user_contacts=(List<Contacts>)session.getAttribute("user_contacts");

	
	if(user_contacts_class!=null && user_contacts!=null){
		out.println("<div>");
		out.println("<table width='490' border='1' style='margin:10px auto;border-collapse: collapse;'>");
		out.println("<tr align='center'><td width='90'>姓名</td><td width='125'>手机</td><td width='90'>QQ</td><td width='125'>E-mail</td><td width='60'><a >操作</a></td></tr>"); 
		Integer currpage=null;
		Integer countpage=null;
		int pagesize=3;
		Integer countstep=null;
		Integer currstep=null;
		int pagestep=5;
		if(index!=null){	
	String contacts_class=user_contacts_class.get(index);
	int count=0;
	int num=0;
	for(int m=0;m<user_contacts.size();m++){
		if(user_contacts.get(m).getContactsclass().equals(contacts_class)){
			count++;
		}
	}
	countpage=PagingUtil.getCountpage(count, pagesize);
	countstep=PagingUtil.getCountstep(count, pagesize, pagestep);
	currpage=PagingUtil.getCurrentpage(curr_page, countpage);
	currstep=PagingUtil.getCurrentstep(curr_step, countstep);   
	for(int j=pointer;j<user_contacts.size();j++){
		if(num<pagesize){
			if(user_contacts.get(j).getContactsclass().equals(contacts_class)){
				num++;
				out.println("<tr align='center'>");
				out.println("<td>"+user_contacts.get(j).getName()+"</td>");
				out.println("<td>"+user_contacts.get(j).getTelephone()+"</td>");
				out.println("<td>"+user_contacts.get(j).getQq()+"</td>");
				out.println("<td>"+user_contacts.get(j).getMail()+"</td>");
				out.println("<td><a href=''>修改</a>|<a href=''>删除</a></td>");
				out.println("</tr>");
			}
		}
		pointer++;
	}
		}else{
	countpage=PagingUtil.getCountpage(user_contacts.size(), pagesize);
	countstep=PagingUtil.getCountstep(user_contacts.size(), pagesize, pagestep);
	currpage=PagingUtil.getCurrentpage(curr_page, countpage);
	currstep=PagingUtil.getCurrentstep(curr_step, countstep);   
	for(int k=(currpage-1)*pagesize;k<currpage*pagesize;k++){
	if(k<user_contacts.size()){
			out.println("<tr align='center'>");
			out.println("<td>"+user_contacts.get(k).getName()+"</td>");
			out.println("<td>"+user_contacts.get(k).getTelephone()+"</td>");
			out.println("<td>"+user_contacts.get(k).getQq()+"</td>");
			out.println("<td>"+user_contacts.get(k).getMail()+"</td>");
			out.println("<td><a href=''>修改</a>|<a href=''>删除</a></td>");
			out.println("</tr>");
		}
	}
		}
		out.println("</table>");
		out.println("</div>");
		out.println("<div align='center' id='pages' >");
		if(countpage>pagestep)
		{   	
	out.println("<a href='javascript:showContacts("+index+","+pointer+","+(pagestep*(currstep-1))+","+(currstep-1)+")'>&laquo;</a>");
	for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
	{	
		out.println("<a href='javascript:showContacts("+index+","+pointer+","+k+","+currstep+")'>"+k+"</a>");
	}
	int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
	out.println("<a href='javascript:showContacts("+index+","+pointer+","+max+","+(currstep+1)+")'>&raquo;</a>");	
		}else{
	if(countpage>1)
	{
		for(int k=1;k<=countpage;k++)
		{
			out.println("<a href='javascript:showContacts("+index+","+pointer+","+k+","+null+")'>"+k+"</a>");
		}
	}
		}
		out.println("</div>");	
		
		
	}else{
		out.println("<div>");
		out.println("<table width='490' border='1' style='margin:10px auto;border-collapse: collapse;'>");
		out.println("<tr align='center'><td width='90'>姓名</td><td width='125'>手机</td><td width='90'>QQ</td><td width='125'>E-mail</td><td width='60'><a >操作</a></td></tr>"); 
		out.println("<tr><td colspan='5' align='center'>你的通讯录为空！！！</td></tr>");
		out.println("</table>");
		out.println("</div>");
	}
%>

