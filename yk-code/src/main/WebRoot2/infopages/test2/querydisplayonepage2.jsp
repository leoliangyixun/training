<%@page import="java.io.UnsupportedEncodingException"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312" contentType="text/html; charset=gb2312"%>
<%@page import="java.sql.ResultSet"%>
<jsp:useBean id="p" class="com.yangkai.bean.PagesApartSQLServer" scope="session"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
  <div align="center">
   <form action="querydisplayonepage2.jsp" method="get" >
   <input type="text" name="bookname" value="������ͼ����Ϣ"/>
   <input type="submit" value="��ѯ"/>
   </form>
     <%
	
    //request.setCharacterEncoding("gb2312");
      String name=request.getParameter("bookname");
    //out.println(name);//nameû�б��룬���Ի�������롣
    //out.print(p.setCharacterEncoding(name)); //��name�����в�ͨ����������
    //String name=p.setCharacterEncoding(request.getParameter("bookname"));
      if(name==null||name.equals("")||p.setCharacterEncoding(name).equals("������ͼ����Ϣ"))
      {
    		out.println("");
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
				   rs=p.execute(str);
 if(rs.isAfterLast()==rs.isBeforeFirst())
				   out.print("<br>û������鼮!");
 else{
				   rs.last();
			   int MaxPage=rs.getRow();
			   if(MaxPage%PageSize!=0)
			   CountPage=MaxPage/PageSize+1;
			   else
				   CountPage=MaxPage/PageSize;
			   int n=PageSize*(CurrPage-1)+1;
			   rs.absolute(n);
			   out.print("<table border=1>");
	out.print("<tr align=center>");
	out.print("<td>���</td><td>ISBN</td><td>ͼ������</td><td>������</td><td>ͼ�鵥��</td><td>�����</td>");
	   out.print("</tr>");
	   for(int i=0, j=n;i<PageSize;i++,j++)
	   {  
				   {
				  		
						
					     out.print("<tr>");
					     out.print("<td>"+j+"</td><td>"+rs.getString(1)+
					    		 "</td>"+"<td>"+rs.getString(2)+"</td>"+"<td>"+rs.getString(3)+
					    		 "</td>"+"<td>"+rs.getInt(4)+"</td><td>"+rs.getInt(5));
					     out.print("</tr>");
						
				  } 
	  rs.next();
	  if(rs.isAfterLast())
	  break;
	  }
			   out.print("</table>");
			   p.closeConnection();
			 
   %>
   <a>����ǰҳ����<%=CurrPage %>ҳ������<%=CountPage %>ҳ������<%=MaxPage %>����¼��</a><br>
<%if(CountPage>1) {%>
			   <% if(CurrPage<2){%>
			   <a href="querydisplayonepage2.jsp?page=<%=CurrPage+1%>&bookname=<%=name %>">��һҳ</a>
			   <a href="querydisplayonepage2.jsp?page=<%=CountPage%>&bookname=<%=name %>">��ĩҳ</a>
			   <%} else if(CurrPage>=2&&CurrPage<CountPage){%>
			   <a href="querydisplayonepage2.jsp?page=1&bookname=<%=name %>" >��һҳ</a>
			   <a href="querydisplayonepage2.jsp?page=<%=CurrPage-1%>&bookname=<%=name %>">��һҳ</a>
			   <a href="querydisplayonepage2.jsp?page=<%=CurrPage+1%>&bookname=<%=name %>">��һҳ</a>
			   <a href="querydisplayonepage2.jsp?page=<%=CountPage%>&bookname=<%=name %>">��ĩҳ</a>
			   <%}else {%>
			  <a href="querydisplayonepage2.jsp?page=1&bookname=<%=name %>" >��һҳ</a>
			  <a href="querydisplayonepage2.jsp?page=<%=CurrPage-1%>&bookname=<%=name %>">��һҳ</a>
						<%}
					}%>
		<%}%>
  <%}%>
   </div>
  </body>
</html>