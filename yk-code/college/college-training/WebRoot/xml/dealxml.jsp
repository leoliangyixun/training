<%@page import="java.sql.ResultSet"%>
<%@page import="com.yangkai.bean.ajax.AjaxDBConection"%>
<%@page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%  	
   	ResultSet rs=null;
   	AjaxDBConection ac=new AjaxDBConection();
   	String str="SELECT * FROM bookinfo";
   	rs=ac.myexecuteQuery(str);
   	response.setContentType("text/xml;charset=UTF-8");//这里指定XML文件的编码。
   	out.println("<book>");
   	while(rs.next())
   	{
   		out.println("<display>");
   		out.println("<ISBN>"+rs.getString("ISBN")+"</ISBN>");
   		out.println("<name>"+rs.getString("name")+"</name>");
   		out.println("<publisher>"+rs.getString("publisher")+"</publisher>");
   		out.println("<price>"+rs.getFloat("price")+"</price>");
   		out.println("<num>"+rs.getInt("num")+"</num>");
   		out.println("</display>");
   	}
   	out.println("</book>");
   	ac.closeConnection();
%>