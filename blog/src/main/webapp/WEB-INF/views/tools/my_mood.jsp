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
		String loginuser=(String)session.getAttribute("loginuser");
		List<Mood> user_mood=(List<Mood>)session.getAttribute("user_mood");
		//List<Mood> friends_mood=(List<Mood>)session.getAttribute("friends_mood");
		List<MoodComment> mood_comment=(List<MoodComment>)session.getAttribute("mood_comment");
		List<MoodCommentReply> mood_comment_reply=(List<MoodCommentReply>)session.getAttribute("mood_comment_reply");
		out.println("<div style='margin-left:7px; border:#E97B56 solid 1px;margin-top:20px;'>");
		if(user_mood!=null && user_mood.size()>0)
		{
			Integer currpage=null;
			Integer countpage=null;
			int pagesize=2;
			Integer countstep=null;
			Integer currstep=null;
			int pagestep=5;
			countpage=PagingUtil.getCountpage(user_mood.size(), pagesize);
			countstep=PagingUtil.getCountstep(user_mood.size(), pagesize, pagestep);
			currpage=PagingUtil.getCurrentpage(curr_page, countpage);
			currstep=PagingUtil.getCurrentstep(curr_step, countstep); 
			
			for(int i=(currpage-1)*pagesize;i<currpage*pagesize; i++)
			{
				if(i<user_mood.size())
				{
					Mood mood=user_mood.get(i);
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
			    	out.println("<td align='left'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(mood.getMooddate())+"</td>");  
			    	out.println("<td align='left' width='50'><a href='DeleteMood?mood_id="+mood_id+"&page="+currpage+"&step="+currstep+"'>删除</a></td>"); 
			    	out.println("</tr>"); 
			    	out.println("</table>");
			    	if(mood_comment!=null && mood_comment.size()>0)
			    	{
			    		for(int j=0;j<mood_comment.size();j++)
			    		{
			    			if(mood_comment.get(j).getMoodid()==mood_id)
			    			{
			    				MoodComment moodcomment=mood_comment.get(j);
			    				int mood_comment_id=moodcomment.getMoodcommentid();
			    				out.println("<table width='505' border='0'  bordercolor='#E7DF98' style='margin-left:65px;margin-bottom:5px;border-collapse: collapse;'>");
			    				out.println("<tr>");
								out.println("<td rowspan='2' width='40' align='center' valign='top'>"); 
						    	out.println("<img src='upload/相册/"+moodcomment.getGuest()+"/image/me.jpg' width='40' height='40''/><br>");
						    	out.println("</td>");
						    	out.println("<td><a style='color:red'>"+moodcomment.getGuest()+"：</a>"+moodcomment.getMoodcommentcontent()+"</td>"); 
			    				out.println("</tr>");
						    	out.println("<tr>");
						    	out.println("<td align='left'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(moodcomment.getMoodcommentdate())
						    			+"<a href='javascript:showMoodCommentReplyTextarea("+mood_comment_id+","+currpage+","+currstep+")' style='margin-left:15px'>回复</a>"
						    			+"<a href='DeleteMoodComment?mood_comment_id="+mood_comment_id
						    			+"&page="+currpage+"&step="+currstep+"' style='margin-left:10px'>删除</a></td>");  
						    	out.println("</tr>"); 
			    				out.println("</table>");
			    				if(mood_comment_reply!=null && mood_comment_reply.size()>0)
			    				{
			    					for(int z=0;z<mood_comment_reply.size();z++)
			    					{
			    						if(mood_comment_reply.get(z).getMoodcommentid()==mood_comment_id)
			    						{
			    							MoodCommentReply moodcommetreply=mood_comment_reply.get(z);
			    							int mood_comment_reply_id=moodcommetreply.getMoodcommentreplyid();
						    				out.println("<table width='465' border='0'  bordercolor='#E7DF98' style='margin-left:105px;margin-bottom:5px;border-collapse: collapse;'>");
						    				out.println("<tr>");
											out.println("<td rowspan='2' width='40' align='center' valign='top'>"); 
									    	out.println("<img src='upload/相册/"+mood.getUsername()+"/image/me.jpg' width='40' height='40''/><br>");
									    	out.println("</td>");
									    	out.println("<td><a style='color:red'>"+mood.getUsername()+"：</a>"+moodcommetreply.getMoodcommentreplycontent()+"</td>"); 
						    				out.println("</tr>");
									    	out.println("<tr>");
									    	out.println("<td align='left'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(moodcommetreply.getMoodcommentreplydate())
									    			+"<a href='DeleteMoodCommentReply?mood_comment_reply_id="+mood_comment_reply_id
									    			+"&page="+currpage+"&step="+currstep+"' style='margin-left:10px'>删除</a></td>");  
									    	out.println("</tr>"); 
						    				out.println("</table>");
			    						}
			    					}
			    				}
								out.println("<div id='mood_comment_reply_textarea"+mood_comment_id+"'></div>");
			    			}
			    		}
			    		
			    	}
			    	out.println("</div>");
				}else{
					break;
				}
			}
			
		    out.println("<div align='center' id='pages'>");
		    if(countpage>pagestep)
		    {   	
		    	out.println("<a href='javascript:getMyMood("+(pagestep*(currstep-1))+","+(currstep-1)+")'>&laquo;</a>");
		    	for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
		    	{	
		    		out.println("<a href='javascript:getMyMood("+k+","+currstep+")'>"+k+"</a>");
		    	}
		    	int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
		    	out.println("<a href='javascript:getMyMood("+max+","+(currstep+1)+")'>&raquo;</a>");	
		    }else{
		    	if(countpage>1)
		    	{
		    		for(int k=1;k<=countpage;k++)
		    		{
		    			out.println("<a href='javascript:getMyMood("+k+","+null+")'>"+k+"</a>");
		    		}
		    	}
		    }
		    out.println("</div>");		
		}else{
			out.println("<div align='center'><a class='success_style'>你还没有发表说说!!!</a></div>");
		}
		out.println("</div>");
%>
			