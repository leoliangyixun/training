<%@ page language="java" import="java.util.*" pageEncoding="gb2312" contentType="text/html; charset=gb2312"%>
<%@page import="java.sql.ResultSet"%>
<jsp:useBean id="p" class="com.yangkai.bean.PagesApartSQLServer" scope="session"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
  <div align="center">
   <form action="QueryResultSetDisplay.jsp"  method="post" >
    ������Ҫ��ѯ������:<input type="text" name="bookname"/><input type="submit" value="��ѯ">
   </form>
      <%
		    String name=p.setCharacterEncoding(request.getParameter("bookname"));
		    if(name==null||name.equals("")) 
		    	out.print("�������Ҫ��Ϣ������<a href=queryindex.jsp>����</a>");
		  else{ 
					   int CountPage;
					   int PageSize=3;
					   ResultSet rs;
					   int CurrPage;
					   String Curr_Page=request.getParameter("page");
					   if(Curr_Page==null)
						   CurrPage=1;
					   else
						   CurrPage=Integer.parseInt(Curr_Page);
					
						   String str="SELECT * FROM bookinfo WHERE name LIKE '%"+name+"%' ";	   
						   rs=p.execute(str);
		if(rs.isAfterLast()==rs.isBeforeFirst())
						   out.print("<br/>û������鼮!");		   
		else{
					   rs.last();
					   int MaxPage=rs.getRow();
					   if(MaxPage%PageSize!=0)
					   CountPage=MaxPage/PageSize+1;
					   else
						   CountPage=MaxPage/PageSize;
					    if(CurrPage>CountPage)
					    {
					    	out.println("�������ҳ������!!!");
					    	out.close();
					    }
					    else{
							   int n=PageSize*(CurrPage-1)+1;
							   rs.absolute(n);   
							   out.print("<table border=1>");
							   out.print("<tr align=center>");
							   out.print("<td>���</td><td>ISBN</td><td>ͼ������</td><td>������</td><td>ͼ�鵥��</td><td>�����</td><td>��ϸ��Ϣ</td>");
							   out.print("</tr>");
							   for(int i=0, j=n;i<PageSize;i++,j++)
							   {  
										   {
										  		 out.print("<form method=post action=querydetails.jsp>");
												
											     out.print("<tr>");
											     out.print("<td>"+j+"</td><td>"+rs.getString(1)+
											    		 "</td>"+"<td>"+rs.getString(2)+"</td>"+"<td>"+rs.getString(3)+
											    		 "</td>"+"<td>"+rs.getInt(4)+"</td><td>"+rs.getInt(5)+"</td><td><input type=submit name=details value=�����鿴��ϸ��Ϣ><input type=hidden name=ISBN value="+rs.getString(1)+"></td>");
											     out.print("</tr>");
												 out.print("</form>");
										  } 
							  rs.next();
							  if(rs.isAfterLast())
							  break;
							   }
							   out.print("</table>");   
							   p.closeConnection();	  
					    }
   %> 
  <form action="querydisplay.jsp" method="get">
    <a>����ǰҳ����<%=CurrPage %>ҳ������<%=CountPage %>ҳ������<%=MaxPage %>����¼��</a>
    <a>������ҳ��:<input type="text" name="page" size="3" /><input type="hidden" name="bookname" value="<%=name%>"/><input type="submit" value="go" /></a>
   </form>
<%if(CountPage>1) {%>
			   <% if(CurrPage<2){%>
			   <a href="querydisplayonepage.jsp?page=<%=CurrPage+1%>&bookname=<%=name %>">��һҳ</a>
			   <a href="querydisplayonepage.jsp?page=<%=CountPage%>&bookname=<%=name %>">��ĩҳ</a>
			   <%} else if(CurrPage>=2&&CurrPage<CountPage){%>
			   <a href="querydisplayonepage.jsp?page=1&bookname=<%=name %>" >��һҳ</a>
			   <a href="querydisplayonepage.jsp?page=<%=CurrPage-1%>&bookname=<%=name %>">��һҳ</a>
			   <a href="querydisplayonepage.jsp?page=<%=CurrPage+1%>&bookname=<%=name %>">��һҳ</a>
			   <a href="querydisplayonepage.jsp?page=<%=CountPage%>&bookname=<%=name %>">��ĩҳ</a>
			   <%}else {%>
			  <a href="querydisplayonepage.jsp?page=1&bookname=<%=name %>" >��һҳ</a>
			  <a href="querydisplayonepage.jsp?page=<%=CurrPage-1%>&bookname=<%=name %>">��һҳ</a>
						<%}
					}%>
		<%}%>
  <%}%>
<%  out.print("<br>"+name);%>//֤��������
   </div>
  </body>
</html>