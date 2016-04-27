<%@page import="com.yangkai.myblog.domain.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="com.yangkai.myblog.domain.UserRequest"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div style="border:#E97B56 solid 1px;" align="center">
<%
	String curr_page=request.getParameter("page");
	String curr_step=request.getParameter("step");
	List<String> friends=(List<String>)session.getAttribute("friends");
	Map<String,User> friends_info=(Map<String,User>)session.getAttribute("friends_info");
	if(friends!=null && friends.size()>0) 
	{
		Integer currpage=null;
		Integer countpage=null;
		int pagesize=3;
		Integer countstep=null;
		Integer currstep=null;
		int pagestep=5;
		countpage=PagingUtil.getCountpage(friends.size(), pagesize);
		countstep=PagingUtil.getCountstep(friends.size(), pagesize, pagestep);
		currpage=PagingUtil.getCurrentpage(curr_page, countpage);
		currstep=PagingUtil.getCurrentstep(curr_step, countstep);   

		for (int i = (currpage-1)*pagesize;i < currpage*pagesize; i++) 
		{
			if(i<friends.size())
			{
				User friend=friends_info.get(friends.get(i));
%>
 <div style="margin-bottom:10px">
	 <table width="560" border="0" height="100">
	  <tr align="left">
		<td rowspan="2" width="90" height="90"><a href="#"><img src="upload/相册/<%=friend.getUsername()%>/image/me.jpg" width="90" height="90"/></a></td>
		<td height="40" class="user_about"><a href="#"><%=friend.getUsername() %></a></td>
	  </tr>
	  <tr align="left">
		<td class="user_info">					      	
			<a><%=friend.getSex() %></a>
			<a><%=friend.getAge() %></a>
			<a><%=friend.getAddress() %></a><br/>
			<a>兴趣爱好：<%=friend.getInterest() %></a><br/>
			<a>注册时间：<%=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(friend.getRegisttime()) %></a><br/>
			<!--a href="" style="font-style:italic;font-size:18px;">进入主页</a-->
			<!--a href="javascript:showFriendDetails(<%=i%>,<%=currpage%>,<%=currstep %>)">查看详细信息</a-->
		</td>
	  </tr>
	  <tr>
		 <td>&nbsp;</td>
		 <td class="user_info"><!--div id="details<%=i%>" ></div--></td>
	  </tr>
	</table>
</div>
<%}else{
		break;
	 }
	 }
	    out.println("<div align='center' id='pages'>");
	    if(countpage>pagestep)
	    {   	
	    	out.println("<a href='javascript:showUserFriendsArea("+(pagestep*(currstep-1))+","+(currstep-1)+")'>&laquo;</a>");
	    	for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
	    	{	
	    		out.println("<a href='javascript:showUserFriendsArea("+k+","+currstep+")'>"+k+"</a>");
	    	}
	    	int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
	    	out.println("<a href='javascript:showUserFriendsArea("+max+","+(currstep+1)+")'>&raquo;</a>");	
	    }else{
	    	if(countpage>1)
	    	{
	    		for(int k=1;k<=countpage;k++)
	    		{
	    			out.println("<a href='javascript:showUserFriendsArea("+k+","+null+")'>"+k+"</a>");
	    		}
	    	}
	    }
	    out.println("</div>");	
	}else{
		out.println("<div align='center'><a class='success_style'>你还没有好友</a></div>");
	}
%>
</div>


