<%@page import="java.net.URLDecoder"%>
<%@page import="com.yangkai.myblog.domain.Album"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<title>�ϴ���Ƭ</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/myblog.js"></script>
	</head>

	<body>
	<%
	List<String> album_map_key=(ArrayList<String>)session.getAttribute("album_map_key");//��album_map��key��ɵļ��ϡ�
	%>
	<div>
	<div id="nav"><jsp:include page="include/top.jsp" flush="true"></jsp:include></div>
	<div id="content">
	<div id="menu_up">
	<div id="menu">
	<div>
	<ul>	
	<li><a href="user_album.jsp">�ҵ����</a></li>	
	<li class="current_page_item"><a href="photo_upload.jsp">�ϴ���Ƭ</a></li>
	<li><a href="album_create.jsp">�������</a></li>	
	<li><a href="album_home.jsp">�������</a></li>			

	</ul>
	</div>
	</div>
	</div>
	<div id="list">
	<div id="list_top">
		<div id="list_middle">
			<div id="list_bottom">
				<div id="blog_list">
				  <%if(session.getAttribute("album_map")!=null){%>
				  <div align="center">
				  <fieldset id="field">
				    <legend id="area">��Ƭ�ϴ�</legend>
				    <div align="center" id="pic_upload_area">
				    <form name="photouploadForm" method="post" enctype="multipart/form-data" >
				    <table>
				    <%if(request.getParameter("album_name")==null){%>
				    <tr>
				    <td>ѡ����᣺</td>
				    <td>
				    	<select name="album_name" onchange="clearAlbumSelectedText()">
				    	<option value="default">==��ѡ�����==</option>
				    	<%
				    	for(int i=0;i<album_map_key.size();i++) 
				    	{
				    		String album_name=album_map_key.get(i);
				    		
				    		out.println("<option value='"+album_name+"'>"+album_name+"</option>");
				    	}
				    	%>
				    	 </select><label id="albumselected"></label>
				    </td>
				    </tr>
				    <%}else{ 
				    	String album_name=URLDecoder.decode(request.getParameter("album_name"), "UTF-8");
				    %>
				     <input type='hidden' name='album_name' value='<%=album_name%>'>       
				    <%}%>
				      <tr><td colspan="2"><label id="photoupload">&nbsp</td></tr>
				      <tr>
				        <td>��Ƭ1��</td>
				        <td><input name="pic1" type="file" size="30" id="pic1"/></td>  
				      </tr>
				      <tr>
				        <td>��Ƭ������</td>
				        <td><textarea name="desc1"  cols="40" rows="5"></textarea></td>
				      </tr>
				      <tr>
				        <td>��Ƭ2��</td>
				        <td><input name="pic2" type="file"  size="30" id="pic2"/> </td>
				      </tr>
				      <tr>
				        <td>��Ƭ������</td>
				        <td><textarea name="desc2"  cols="40" rows="5"></textarea></td>
				      </tr>
				      <tr>
				        <td>��Ƭ3��</td>
				        <td><input name="pic3" type="file"  size="30" id="pic3"/></td> 
				      </tr>
				      <tr>
				        <td>��Ƭ������</td>
				        <td><textarea name="desc3"  cols="40" rows="5"></textarea></td>
				      </tr>   
				        <td></label><input type='hidden' name='upload_date' value='<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%>'></td>
				        <td align="center">
				          <input type="button" value="�ύ" onclick="checkAlbumSelected()"/>
				          <input type="reset" value="����" />
				        </td>
				      </tr>
				    </table>
				  </form>
				  </div>
				  </fieldset>
				  </div>
				  <%
				  }else{
					  out.println("<div  align='center'><a class='success_style'>�㻹û�����!!!</a><a href='album_create.jsp' class='continue_style'>�������<a></div>");
				  }
				  %>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div style="clear:both"></div>
	</div>
	<jsp:include page="include/bottom.html" flush="true"></jsp:include>
	</div>
	</body>
</html>

