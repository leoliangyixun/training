<%@page contentType="text/html;charset=GB2312"%> 
<%@include file="jdbc-xsjbxx.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>��ѯѧ��������Ϣ</title>
<!--������ʽ�ļ�-->
<link rel=stylesheet href="xsjbxx.css" type=text/css>
</head>

<body style="text-align: center">
<form method="POST" action="cxxsjbxx.jsp">
	<table border="1" width="95%" cellspacing="1" height="64" bordercolor="#000000">
		<tr>
			<td colspan="3"><a href="index.htm">��ҳ</a>-����ѯѧ��������Ϣ</td>
		</tr>
      <tr>
         <td colspan="3"><p id=s1 align="center">
		   �������ѯ����</p></td>
      </tr>
		<tr>
			<td width="30%">��ѯ��ʽ��<input type="radio" value="xuehao" checked name="fangshi">ѧ��<input type="radio" name="fangshi" value="xingming">����</td>
			<td width="39%">��ѯ������<input type="text" name="tiaojian" size="20"></td>
			<td width="29%"><input type="submit" value="�ύ" name="B1"></td>
		</tr>
	</table>
</form>

<table border="1" width="95%" cellspacing="0" cellpadding="0" height="26" bordercolor="#000000" style="border-collapse: collapse">
	<tr>
			<td colspan="11">
			<div align="center">
				<b>�����ѯ������ѧ��������Ϣ</b></div>
			</td>
		 </tr>
		 
			 <tr>
			 <td align=center>����</td>
			 <td align=center>ѧ��</td>
  <td align=center>�Ա�</td>
  <td align=center>��������</td>
  <td align=center>��������</td>
  <td align=center>Ѫ��</td>
  <td align=center>����</td>
  <td align=center>ϲ�����˶�</td>
  <td align=center>��ע</td>
  <td align=center>�Ƿ��޸�</td>
  <td align=center>�Ƿ�ɾ��</td>
             </tr>
  <!--�������Ӳ�ѯ���ݿ���Ϣ��JSP�ű�-->
<% 
//�������ı��뷽ʽ  
request.setCharacterEncoding("gb2312");

//���ղ�ѯ��ʽ��ֵ
String fangshi=request.getParameter("fangshi");

//���ղ�ѯ������ֵ
String tiaojian=request.getParameter("tiaojian");
 
//ִ�в�ѯSQL���  
String sql = "select * from xsjbxx where "+fangshi+" like '%"+tiaojian+"%'";
ResultSet rs = s.executeQuery(sql);
 String xingming;
 String xuehao;
 String xingbie;
 
 String chushengriqi;
 String youxiang;
 String xuexing;
 
 String aihao;
 String tiyuyundong;
 String beizhu;
 
 //�õ��������������Ĳ�ѯ���
 while(rs.next())
 {
  xingming = rs.getString(1);
  xuehao = rs.getString(2);
  xingbie = rs.getString(3);
  chushengriqi = rs.getString(4);
  youxiang = rs.getString(5);
  xuexing = rs.getString(6);
  aihao = rs.getString(7);
  tiyuyundong = rs.getString(8);
  beizhu = rs.getString(9);
  %>
  
<!--�������ʾ��ѯ�������ݿ��е���Ϣ-->  		
	<tr align="center">
 <td align=center><%=xingming%></td>
  <td align=center><%=xuehao%></td>
   <td align=center><%=xingbie%></td> 
   <td align=center><%=chushengriqi%></td>
    <td align=center><%=youxiang%></td> 
    <td align=center><%=xuexing%></td> 
    <td align=center><%=aihao%></td> 
    <td align=center><%=tiyuyundong%></td> 
    <td align=center><%=beizhu%></td> 
    <td><a href="xgxsjbxx.jsp?xuehao=<%=xuehao%>">�޸�</a></td>
    <td><a href="scxsjbxx.jsp?xuehao=<%=xuehao%> ">ɾ��</a></td>
	</tr>
<!--������ͷ���Դ-->	
     <%
 }
 rs.close();
 s.close();
 c.close();
%>
</table>
</body>

</html>