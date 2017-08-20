<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="com.yangkai.myblog.domain.MoodCommentReply"%>
<%@page import="com.yangkai.myblog.domain.MoodComment"%>
<%@page import="com.yangkai.myblog.domain.Mood"%>
<%@page import="com.yangkai.myblog.constants.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String curr_page=request.getParameter("page");
	String curr_step=request.getParameter("step");
	Map<String,List<Mood>> friends_mood=(Map<String,List<Mood>>)session.getAttribute("friends_mood");
	List<Mood> all_friends_mood=(List<Mood>)session.getAttribute("all_friends_mood");
	List<MoodComment> friends_mood_comment=(List<MoodComment>)session.getAttribute("friends_mood_comment");
	List<MoodCommentReply> friends_mood_comment_reply=(List<MoodCommentReply>)session.getAttribute("friends_mood_comment_reply");
	if(friends_mood!=null && friends_mood.size()>0)
	{
		//int friends_mood_num=0;
		//friends_mood_num=PagingTool.getNum(friends_mood);
		Integer currpage=null;
		Integer countpage=null;
		int pagesize=2;
		Integer countstep=null;
		Integer currstep=null;
		int pagestep=5;
		countpage=PagingUtil.getCountpage(all_friends_mood.size(), pagesize);
		countstep=PagingUtil.getCountstep(all_friends_mood.size(), pagesize, pagestep);
		currpage=PagingUtil.getCurrentpage(curr_page, countpage);
		currstep=PagingUtil.getCurrentstep(curr_step, countstep); 
		
		for(int i=(currpage-1)*pagesize;i<currpage*pagesize; i++)
		{
	if(i<all_friends_mood.size())
	{
		Mood mood=all_friends_mood.get(i);
		int mood_id=mood.getMoodid();
		out.println("<div style='margin-top:10px;margin-left:4px;'>");
		out.println("<table width='570' border='0'  bordercolor='#E7DF98' style='margin-bottom:5px;border-collapse: collapse;''>");
		out.println("<tr>"); 
		out.println("<td rowspan='2' width='65' align='center' valign='top'>"); 
	    	out.println("<img src='upload/相册/"+mood.getUsername()+"/image/me.jpg' width='65' height='65''/><br>");
	    	out.println("</td>");
	    	out.println("<td colspan='2'><a style='color:red'>"+mood.getUsername()+"：</a>"+mood.getMoodcontent()+"</td>"); 
	    	out.println("</tr>"); 
	    	out.println("<tr>");
	    	out.println("<td align='left'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mood.getMooddate())+"</td>");  
	    	out.println("<td align='right'><a href='javascript:showMoodCommentTextarea("+mood_id+","+currpage+","+currstep+")''>评论</a></td>"); 
	    	out.println("</tr>"); 
	    	out.println("</table>");

		    	
		    	
		if(friends_mood_comment!=null && friends_mood_comment.size()>0)
		{
			for(int j=0;j<friends_mood_comment.size();j++)
			{
				if(friends_mood_comment.get(j).getMoodid()==mood_id)
				{
					MoodComment moodcomment=friends_mood_comment.get(j);
					int mood_comment_id=moodcomment.getMoodcommentid();
					out.println("<table width='505' border='0'  bordercolor='#E7DF98' style='margin-left:65px;margin-bottom:5px;border-collapse: collapse;'>");
					out.println("<tr>");
					out.println("<td rowspan='2' width='40' align='center' valign='top'>"); 
					out.println("<img src='upload/相册/"+moodcomment.getGuest()+"/image/me.jpg' width='40' height='40''/><br>");
					out.println("</td>");
					out.println("<td><a style='color:red'>"+moodcomment.getGuest()+"：</a>"+moodcomment.getMoodcommentcontent()+"</td>"); 
					out.println("</tr>");
					out.println("<tr>");
					out.println("<td align='left'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(moodcomment.getMoodcommentdate())+"</td>");  	
					out.println("</tr>"); 
					out.println("</table>");
					if(friends_mood_comment_reply!=null && friends_mood_comment_reply.size()>0)
					{
						for(int z=0;z<friends_mood_comment_reply.size();z++)
						{
							if(friends_mood_comment_reply.get(z).getMoodcommentid()==mood_comment_id)
							{
								MoodCommentReply moodcommetreply=friends_mood_comment_reply.get(z);
								int mood_comment_reply_id=moodcommetreply.getMoodcommentreplyid();
								out.println("<table width='465' border='0'  bordercolor='#E7DF98' style='margin-left:105px;margin-bottom:5px;border-collapse: collapse;'>");
								out.println("<tr>");
								out.println("<td rowspan='2' width='40' align='center' valign='top'>"); 
								out.println("<img src='upload/相册/"+mood.getUsername()+"/image/me.jpg' width='40' height='40''/><br>");
								out.println("</td>");
								out.println("<td><a style='color:red'>"+mood.getUsername()+"：</a>"+moodcommetreply.getMoodcommentreplycontent()+"</td>"); 
								out.println("</tr>");
								out.println("<tr>");
								out.println("<td align='left'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(moodcommetreply.getMoodcommentreplydate())+"</td>");  
								out.println("</tr>"); 
								out.println("</table>");
							}
						}
					}
				}
			}
			
		}
				    		//发表评论。
		    	/*
		out.println("<div align='center' style='margin:5px 0 5px 65px'>");
		out.println("<form name='replyForm' action='AddMoodComment' method='post'>");
		out.println("<input type='hidden' name='page' value='"+currpage+"'>");
		out.println("<input type='hidden' name='step' value='"+currstep+"'>");
		out.println("<input type='hidden' name='mood_id' value='"+mood_id+"'>");
		out.println("<input type='hidden' name='mood_comment_date' value='"
					+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"'>");
		out.println("<div><textarea style='border: #E97B56 solid 1px;font-size: 12px;' name='mood_comment_content' cols='80' rows='1'></textarea></div>");
		out.println("<div align='left'>");
		out.println("<input type='submit' value='发表'  style='margin-left:5px;'>");
		out.println("<input type='reset' value='取消' style='margin-left:5px;'>");
		out.println("</div>");
		out.println("</form>");
		out.println("</div>");
	    */
	    //利用Ajax发表评论。
	    out.println("<div id='mood_comment_textarea"+mood_id+"'></div>");
		
		
		out.println("</div>");
	}else{
		break;
	}
		}
	
		out.println("<div align='center' id='pages'>");
		if(countpage>pagestep)
		{
	out.println("<a href='user_mood.jsp?page="+(pagestep*(currstep-1))+"&step="+(currstep-1)+"'>&laquo;</a>");
	for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
	{
		out.println("<a href='user_mood.jsp?page="+k+"&step="+currstep+"'>"+k+"</a>");
	}
	int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
	out.println("<a href='user_mood.jsp?page="+max+"&step="+(currstep+1)+"'>&raquo;</a>");	
		}else{
	if(countpage>1)
	{
		for(int k=1;k<=countpage;k++)
		{
			out.println("<a href='user_mood.jsp?page="+k+"'>"+k+"</a>");
		}
	}
		}
		out.println("</div>");	
	}else{
		out.println("<div align='center'><a class='success_style'>你还没有发表说说!!!</a></div>");
	}
%>