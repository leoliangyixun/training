package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.MoodCommentReply;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class DeleteMoodCommentReply extends HttpServlet {


	public void destroy() {
		super.destroy(); 
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			HttpSession session=request.getSession(true);
			String currpage=request.getParameter("page");
			String currstep=request.getParameter("step");
			int mood_comment_reply_id=Integer.parseInt(request.getParameter("mood_comment_reply_id"));
			List<MoodCommentReply> mood_comment_reply=(List<MoodCommentReply>) session.getAttribute("mood_comment_reply");
			/***********************************更新数据库***********************************/
			MyBlogService service=ServiceFactory.getMyBlogService(request);
			int count=service.deleteMoodCommentReply(mood_comment_reply_id);
			/***********************************更新集合***********************************/
			if(count>0){
				System.out.println("删除说说评论回复成功！！！");
				for(int i=0;i<mood_comment_reply.size();i++)
				{
					if(mood_comment_reply.get(i).getMoodcommentreplyid()==mood_comment_reply_id)
					{
						mood_comment_reply.remove(i);
						break;
					}
				}
				response.sendRedirect("user_mood.jsp?page="+currpage+"&step="+currstep);
			}else{
				System.out.println("删除说说评论回复失败！！！");
			}
	}


	public void init() throws ServletException {
	
	}

}
