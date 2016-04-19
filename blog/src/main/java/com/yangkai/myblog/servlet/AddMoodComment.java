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
import com.yangkai.myblog.domain.MoodComment;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncoderUtil;

public class AddMoodComment extends HttpServlet {

	public void destroy() {
		super.destroy(); 
	
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);

	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		HttpSession session=request.getSession(true);
		String currpage=request.getParameter("page");
		String currstep=request.getParameter("step");
		String guest=(String) session.getAttribute("loginuser");
		List<String> friends=(List<String>)session.getAttribute("friends");
		int mood_id=Integer.parseInt(request.getParameter("mood_id"));
		String mood_comment_content=EncoderUtil.encode(request.getParameter("mood_comment_content"));
		String mood_comment_date=request.getParameter("mood_comment_date");
		String username=null;
		List<Mood> all_friends_mood=(List<Mood>) session.getAttribute("all_friends_mood");
		for(int i=0;i<all_friends_mood.size();i++){
			if(all_friends_mood.get(i).getMoodid()==mood_id){
				username=all_friends_mood.get(i).getUsername();
				break;
			}
		}
		MoodComment moodcomment=new MoodComment();
		moodcomment.setMoodid(mood_id);
		moodcomment.setUsername(username);
		moodcomment.setGuest(guest);
		moodcomment.setMoodcommentcontent(mood_comment_content);
		try {
			moodcomment.setMoodcommentdate(DateFormat.getDateTimeInstance().parse(mood_comment_date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		int count=service.addMoodComment(moodcomment);
		if(count>0)
		{
			List<MoodComment> friends_mood_comment=service.getFriendsMoodComment(friends);//这种方式的效率及其低下。
			session.setAttribute("friends_mood_comment",friends_mood_comment);
			response.sendRedirect("friends_mood.jsp?page="+currpage+"&step="+currstep);
			System.out.println("添加说说回复成功！！！");
		}else{
			response.sendRedirect("include/error.jsp");
			System.out.println("添加说说回复失败！！！");
		}
	}

	public void init() throws ServletException {
		
	}

}
