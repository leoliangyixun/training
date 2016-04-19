package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.MoodCommentReply;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncoderUtil;

public class AddMoodCommentReply extends HttpServlet {


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
		String username=(String) session.getAttribute("loginuser");
		int mood_comment_id=Integer.parseInt(request.getParameter("mood_comment_id"));
		String mood_comment_reply_content=EncoderUtil.encode(request.getParameter("mood_comment_reply_content"));
		String mood_comment_reply_date=request.getParameter("mood_comment_reply_date");
		MoodCommentReply moodcommentreply=new MoodCommentReply();
		moodcommentreply.setMoodcommentid(mood_comment_id);
		moodcommentreply.setUsername(username);
		moodcommentreply.setMoodcommentreplycontent(mood_comment_reply_content);
		try {
			moodcommentreply.setMoodcommentreplydate(DateFormat.getDateTimeInstance().parse(mood_comment_reply_date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		int count=service.addMoodCommentReply(moodcommentreply);
		if(count>0)
		{
			List<MoodCommentReply> mood_comment_reply=service.getMoodCommentReply(username);
			session.setAttribute("mood_comment_reply",mood_comment_reply);
			response.sendRedirect("user_mood.jsp?page="+currpage+"&step="+currstep);
		}
		
		
	}

	public void init() throws ServletException {
		
	}

}
