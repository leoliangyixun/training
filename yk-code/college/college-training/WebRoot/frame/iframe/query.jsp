<%@ page language="java" import="java.util.*" pageEncoding="gb2312" contentType="text/html; charset=gb2312"%>
<%@page import="java.sql.ResultSet"%>
<jsp:useBean id="p" class="com.yangkai.bean.AjaxDBConection" scope="session"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
  <div align="center">
   <form action="query.jsp" method="post" >
   <input type="text" name="bookname" value="������ͼ����Ϣ"/>
   <input type="submit" value="��ѯ" />
   </form>
      <%
		    String name=request.getParameter("bookname");
      	    if(name==null)
      	    {
      		 out.println();
      	    }
      	    else if(name.equals("")||p.setCharacterEncoding(name).equals("������ͼ����Ϣ")) 
		    {
		    	 out.println("<script language='javascript'>alert('�������Ҫ����Ϣ')</script");
		    }
		    else{ 
			  		   name=p.setCharacterEncoding(name);
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
						   rs=p.myexecuteQuery(str);
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
							   out.print("<table border=\"1\" bordercolor=\"#000000\" cellspacing=\"0\" cellpadding=\"5\" style=\"border-collapse: collapse;font-size:12px\">");
							   out.print("<tr align=\"center\">");
							   out.print("<td>���</td><td>ISBN</td><td>ͼ������</td><td>������</td><td>ͼ�鵥��</td><td>�����</td>");
							   out.print("</tr>");
							   for(int i=0, j=n;i<PageSize;i++,j++)
							   {  
										   {
										  		
												
											     out.print("<tr>");
											     out.print("<td>"+j+"</td><td>"+rs.getString(1)+
											    		 "</td>"+"<td>"+rs.getString(2)+"</td>"+"<td>"+rs.getString(3)+
											    		 "</td>"+"<td>"+rs.getFloat(4)+"</td><td>"+rs.getInt(5));
											     out.print("</tr>");
												
										  } 
							  rs.next();
							  if(rs.isAfterLast())
							  break;
							   }
							   out.print("</table>");   
					    
   %> 
  <form action="query.jsp" method="get">
    <a>����ǰҳ����<%=CurrPage %>ҳ������<%=CountPage %>ҳ������<%=MaxPage %>����¼��</a>
    <a>������ҳ��:
	<input type="text" name="page" size="3" />
	<input type="hidden" name="bookname" value="<%=name%>"/>
	<input type="submit" value="go" /></a>
   </form>
<%if(CountPage>1) {%>
			   <% if(CurrPage<2){%>
			   <a href="query.jsp?page=<%=CurrPage+1%>&bookname=<%=name %>">��һҳ</a>
			   <a href="query.jsp?page=<%=CountPage%>&bookname=<%=name %>">��ĩҳ</a>
			   <%} else if(CurrPage>=2&&CurrPage<CountPage){%>
			   <a href="query.jsp?page=1&bookname=<%=name %>" >��һҳ</a>
			   <a href="query.jsp?page=<%=CurrPage-1%>&bookname=<%=name %>">��һҳ</a>
			   <a href="query.jsp?page=<%=CurrPage+1%>&bookname=<%=name %>">��һҳ</a>
			   <a href="query.jsp?page=<%=CountPage%>&bookname=<%=name %>">��ĩҳ</a>
			   <%}else {%>
			  <a href="query.jsp?page=1&bookname=<%=name %>" >��һҳ</a>
			  <a href="query.jsp?page=<%=CurrPage-1%>&bookname=<%=name %>">��һҳ</a>
						<%}
					}%>
		<%}%>
  <%
  	}
  }
  %>
   </div>
  </body>
</html>