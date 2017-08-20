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

import com.yangkai.myblog.domain.Mood;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncoderUtil;

public class AddMood extends HttpServlet {

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
			String username=(String)session.getAttribute("loginuser");
			String mood_content=EncoderUtil.encode(request.getParameter("mood_content"));
			String mood_date=request.getParameter("mood_date");
			Mood mood=new Mood();
			mood.setUsername(username);
			mood.setMoodcontent(mood_content);
			try {
				mood.setMooddate(DateFormat.getDateTimeInstance().parse(mood_date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			MyBlogService service=ServiceFactory.getMyBlogService(request);
			int count=service.addMood(mood);
			if(count>0)
			{
				List<Mood> user_mood=service.getMood(username);
				session.setAttribute("user_mood", user_mood);
				response.sendRedirect("user_mood.jsp");
			}else{
				response.sendRedirect("include/error.jsp");
			}
	}

	public void init() throws ServletException {
		
	}

}
