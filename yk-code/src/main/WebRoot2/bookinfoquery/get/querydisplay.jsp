<%@page import="java.sql.ResultSet"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<jsp:useBean id="p" class="com.yangkai.bean.PagesApartMSSQLServer" scope="session"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ͼ���ѯ</title>
  </head>
  
  <body>
  <div align="center">
 <%
    String name=request.getParameter("bookname");
    byte b[]=name.getBytes("ISO-8859-1");
    name=new String(b);	
    if(name==null||name.equals("")) 
    {
    	out.print("");
    }
  	else{ 
		int CountPage;
		int PageSize=3;
		ResultSet rs;
		int CurrPage;
		String Curr_Page=request.getParameter("page");
		if(Curr_Page==null)
		{
			CurrPage=1;
		}
		else{
			CurrPage=Integer.parseInt(Curr_Page);
		}
		String str="SELECT * FROM bookinfo WHERE name LIKE '%"+name+"%' ";	   
		rs=p.execute(str);
		if(rs.isAfterLast()==rs.isBeforeFirst())
		{
			 out.print("<br/>û������鼮!");
		}			   
		else{
			rs.last();
			int MaxPage=rs.getRow();
		    if(MaxPage%PageSize!=0)
			{
			 	CountPage=MaxPage/PageSize+1;
			}
			else{
				CountPage=MaxPage/PageSize;
			}
			int n=PageSize*(CurrPage-1)+1;
			rs.absolute(n);   
			out.print("<table border=1>");
			out.print("<tr align=center>");
			out.print("<td>���</td><td>ͼ������</td><td>������</td><td>ͼ�鵥��</td><td>�����</td><td>��ϸ��Ϣ</td>");
			out.print("</tr>");
			for(int i=0, j=n;i<PageSize;i++,j++)
			{  
				out.print("<form method=post action=querydetails.jsp>");
				int num;
				num=rs.getInt(1);
				out.print("<tr>");
				out.print("<td>"+j+"</td><td>"+rs.getString(2)+
						"</td>"+"<td>"+rs.getString(3)+"</td>"+"<td>"+rs.getString(4)+
						"</td>"+"<td>"+rs.getString(5)+"</td><td><input type=submit name=details value=�����鿴��ϸ��Ϣ><input type=hidden name=bookid value="+num+"></td>");
				out.print("</tr>");
			    out.print("</form>");				
				rs.next();
				if(rs.isAfterLast())
				{
					break;
				}
			}
			out.print("</table>");  
			p.closeConnection();		   
   %>
   <a>����ǰҳ����<%=CurrPage %>ҳ������<%=CountPage %>ҳ������<%=MaxPage %>����¼��</a><br>
	<%if(CountPage>1) {%>
			   <% if(CurrPage<2){%>
			   <a href="querydisplay.jsp?page=<%=CurrPage+1%>&bookname=<%=name %>">��һҳ</a>
			   <a href="querydisplay.jsp?page=<%=CountPage%>&bookname=<%=name %>">��ĩҳ</a>
			   <%} else if(CurrPage>=2&&CurrPage<CountPage){%>
			   <a href="querydisplay.jsp?page=1&bookname=<%=name %>" >��һҳ</a>
			   <a href="querydisplay.jsp?page=<%=CurrPage-1%>&bookname=<%=name %>">��һҳ</a>
			   <a href="querydisplay.jsp?page=<%=CurrPage+1%>&bookname=<%=name %>">��һҳ</a>
			   <a href="querydisplay.jsp?page=<%=CountPage%>&bookname=<%=name %>">��ĩҳ</a>
			   <%}else {%>
			  <a href="querydisplay.jsp?page=1&bookname=<%=name %>" >��һҳ</a>
			  <a href="querydisplay.jsp?page=<%=CurrPage-1%>&bookname=<%=name %>">��һҳ</a>
			  <%}}%>
 <%}%>
 <%}%>
 </div>
  </body>
</html>