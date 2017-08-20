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
    请输入要查询的书名:<input type="text" name="bookname"/><input type="submit" value="查询">
   </form>
      <%
		    String name=p.setCharacterEncoding(request.getParameter("bookname"));
		    if(name==null||name.equals("")) 
		    	out.print("请输入必要信息！！！<a href=queryindex.jsp>返回</a>");
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
						   out.print("<br/>没有相关书籍!");		   
		else{
					   rs.last();
					   int MaxPage=rs.getRow();
					   if(MaxPage%PageSize!=0)
					   CountPage=MaxPage/PageSize+1;
					   else
						   CountPage=MaxPage/PageSize;
					    if(CurrPage>CountPage)
					    {
					    	out.println("你输入的页码有误!!!");
					    	out.close();
					    }
					    else{
							   int n=PageSize*(CurrPage-1)+1;
							   rs.absolute(n);   
							   out.print("<table border=1>");
							   out.print("<tr align=center>");
							   out.print("<td>编号</td><td>ISBN</td><td>图书名称</td><td>出版社</td><td>图书单价</td><td>库存量</td><td>详细信息</td>");
							   out.print("</tr>");
							   for(int i=0, j=n;i<PageSize;i++,j++)
							   {  
										   {
										  		 out.print("<form method=post action=querydetails.jsp>");
												
											     out.print("<tr>");
											     out.print("<td>"+j+"</td><td>"+rs.getString(1)+
											    		 "</td>"+"<td>"+rs.getString(2)+"</td>"+"<td>"+rs.getString(3)+
											    		 "</td>"+"<td>"+rs.getInt(4)+"</td><td>"+rs.getInt(5)+"</td><td><input type=submit name=details value=单击查看详细信息><input type=hidden name=ISBN value="+rs.getString(1)+"></td>");
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
    <a>【当前页：第<%=CurrPage %>页】【共<%=CountPage %>页】【共<%=MaxPage %>条记录】</a>
    <a>请输入页码:<input type="text" name="page" size="3" /><input type="hidden" name="bookname" value="<%=name%>"/><input type="submit" value="go" /></a>
   </form>
<%if(CountPage>1) {%>
			   <% if(CurrPage<2){%>
			   <a href="querydisplayonepage.jsp?page=<%=CurrPage+1%>&bookname=<%=name %>">下一页</a>
			   <a href="querydisplayonepage.jsp?page=<%=CountPage%>&bookname=<%=name %>">最末页</a>
			   <%} else if(CurrPage>=2&&CurrPage<CountPage){%>
			   <a href="querydisplayonepage.jsp?page=1&bookname=<%=name %>" >第一页</a>
			   <a href="querydisplayonepage.jsp?page=<%=CurrPage-1%>&bookname=<%=name %>">上一页</a>
			   <a href="querydisplayonepage.jsp?page=<%=CurrPage+1%>&bookname=<%=name %>">下一页</a>
			   <a href="querydisplayonepage.jsp?page=<%=CountPage%>&bookname=<%=name %>">最末页</a>
			   <%}else {%>
			  <a href="querydisplayonepage.jsp?page=1&bookname=<%=name %>" >第一页</a>
			  <a href="querydisplayonepage.jsp?page=<%=CurrPage-1%>&bookname=<%=name %>">上一页</a>
						<%}
					}%>
		<%}%>
  <%}%>
<%  out.print("<br>"+name);%>//证明有乱码
   </div>
  </body>
</html>