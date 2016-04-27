<%@page import="com.yangkai.myblog.domain.Contacts"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--
	out.println("<form>");
	out.println("<table width='490' border='0'>");	
	out.println("<tr>");
	out.println("<td width='90'>姓名：</td><td width='200'><input type='text' name='name'/></td><td width='200'>通讯录类别：");
	out.println("<select>");
	out.println();
	out.println("</select>");
	out.println("</td>");
	out.println("<tr>");
	out.println("</table>");	
	out.println("<form>");
--%>
	<%
		List<String> user_contacts_class=(List<String>)session.getAttribute("user_contacts_class");
		List<Contacts> user_contacts=(List<Contacts>)session.getAttribute("user_contacts");
	%>
	<form  method="post" action="AddContacts">
	  <table width="490" border="0" style="margin:10px auto;border-collapse: collapse;">
	    <tr>
	      <td width="50">姓名：</td>
	      <td width="200"><input type="text" name="name" id="text_border" /></td>
	      <td width="240">通讯录类别：
	        <select name="contacts_class" id="text_border">
	          <option value="" selected="selected">==选择通讯录类别==</option>
	          <%
	          	if(user_contacts_class!=null && user_contacts_class.size()>0){
	          		for(int i=0;i<user_contacts_class.size();i++){
	          			out.println("<option value='"+user_contacts_class.get(i)+"'>"+user_contacts_class.get(i)+"</option>");
	          		}
	          	}else{
	          		out.println("<option value=''>你的通讯录列表为空</option>");
	          	}
	          %>
	      </select></td>
	    </tr>
	    <tr>
	      <td>手机：</td>
	      <td colspan="2"><input type="text" name="telephone" id="text_border" /></td>
	    </tr>
	    <tr>
	      <td>QQ：</td>
	      <td colspan="2"><input type="text" name="qq" id="text_border" /></td>
	    </tr>
	    <tr>
	      <td>E-mail:</td>
	      <td colspan="2"><input type="text" name="mail" id="text_border" /></td>
	    </tr>
	    <tr>
	      <td>&nbsp;</td>
	      <td colspan="2" align="left">
	      <input type="submit" name="button" id="button" value="添加" />
	      <input type="reset" name="button2" id="button2" value="取消" /></td>
	    </tr>
	  </table>
	</form>