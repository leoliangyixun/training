<%@page import="java.net.URLDecoder"%>
<%@page import="java.io.UnsupportedEncodingException"%>
<%@page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="java.sql.ResultSet"%>
<jsp:useBean id="p" class="com.yangkai.bean.MySQLDataMultipages" scope="session"></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
     <%
      String name=request.getParameter("bookname");
      if(name==null || name.equals("") || URLDecoder.decode(name,"UTF-8").equals("������ͼ����Ϣ"))
      {
    		out.println("");
      } 
      else{
    	       name=URLDecoder.decode(name,"UTF-8");
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
			   out.print("<table border=1 cellspacing=0 cellpadding=0  bordercolor=#000000 style='border-collapse: collapse'>");
			   out.print("<tr align=center>");
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
      // p.closeConnection();	 
   %>
   <a>����ǰҳ����<%=CurrPage %>ҳ������<%=CountPage %>ҳ������<%=MaxPage %>����¼��</a><br>
<%if(CountPage>1) {%>
			   <% if(CurrPage<2){%>
			   <a href="ajaxmultipagesresult.jsp?page=<%=CurrPage+1%>&bookname=<%=name %>">��һҳ</a>
			   <a href="ajaxmultipagesresult.jsp?page=<%=CountPage%>&bookname=<%=name %>">��ĩҳ</a>
			   <%} else if(CurrPage>=2&&CurrPage<CountPage){%>
			   <a href="ajaxmultipagesresult.jsp?page=1&bookname=<%=name %>" >��һҳ</a>
			   <a href="ajaxmultipagesresult.jsp?page=<%=CurrPage-1%>&bookname=<%=name %>">��һҳ</a>
			   <a href="ajaxmultipagesresult.jsp?page=<%=CurrPage+1%>&bookname=<%=name %>">��һҳ</a>
			   <a href="ajaxmultipagesresult.jsp?page=<%=CountPage%>&bookname=<%=name %>">��ĩҳ</a>
			   <%}else {%>
			  <a href="ajaxmultipagesresult.jsp?page=1&bookname=<%=name %>" >��һҳ</a>
			  <a href="ajaxmultipagesresult.jsp?page=<%=CurrPage-1%>&bookname=<%=name %>">��һҳ</a>
						<%}
					}%>
		<%}%>
  <%}%>
  </body>
</html>
