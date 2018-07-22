<%@page import="java.sql.ResultSet"%>
<%@page import="com.yangkai.bean.ajax.AjaxDBConection"%>
<%@page language="java" import="java.util.*" pageEncoding="gb2312" contentType="text/html; charset=gb2312"%>
<%@page import="java.net.URLDecoder"%>
<%
    response.setContentType("text/xml;charset=UTF-8");
	AjaxDBConection ac=new AjaxDBConection();
	ResultSet rs=null;
	String str=null;
	String name=null;
	String curr_page=null;
	int countpage;
	int pagesize=3;
	int currpage;
    name=request.getParameter("name");
    out.print("<bookinfo>");
    if(name==null)
    {
    	out.println("<error-record>");
		out.print("<error>非法操作!</error>");
		out.println("</error-record>");
		out.print("</bookinfo>");
		out.close();
    }
    else if(name.equals("")||URLDecoder.decode(name,"UTF-8").equals("请输入图书信息"))
    {
    	str="SELECT * FROM bookinfo";	   
    }
    else
    {
    	name=URLDecoder.decode(name,"UTF-8");   
    	str="SELECT * FROM bookinfo WHERE name LIKE '%"+name+"%' ";	
    }
    curr_page=request.getParameter("page");
	if(curr_page==null)
    {
    	currpage=1;
    }
	else
	{
		currpage=Integer.parseInt(curr_page);
	} 
	
	rs=ac.myexecuteQuery(str);
	if(rs.isAfterLast()==rs.isBeforeFirst())
	{	
		out.println("<no-record>");
		out.print("<no-book>没有相关书籍!</no-book>");	
		out.println("</no-record>");
	}
	else{
			rs.last();
			int maxpage=rs.getRow();
			if(maxpage%pagesize!=0)
			{
				countpage=maxpage/pagesize+1;
			}
			else
			{
				countpage=maxpage/pagesize;     
			}
			out.println("<page-record>");
			out.println("<countpage>"+countpage+"</countpage>");
			out.println("</page-record>");
			int n=pagesize*(currpage-1)+1;
			rs.absolute(n);  
			for(int i=0, j=n;i<pagesize;i++,j++)
			{  
				{	
					out.print("<display>");
					out.print("<ID>"+j+"</ID>"+
					          "<ISBN>"+rs.getString("ISBN")+"</ISBN>"+
							  "<name>"+rs.getString("name")+"</name>"+
					          "<publisher>"+rs.getString("publisher")+"</publisher>"+
							  "<price>"+rs.getFloat("price")+"</price>"+
					          "<num>"+rs.getInt("num")+"</num>"); 		   
					out.print("</display>");	
				} 
				rs.next();
				if(rs.isAfterLast())
				break;
			}
		 }
	out.print("</bookinfo>");
%>
