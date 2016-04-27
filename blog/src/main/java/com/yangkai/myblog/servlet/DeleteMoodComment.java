package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.MoodComment;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class DeleteMoodComment extends HttpServlet {


	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		HttpSession session=request.getSession();
		String currpage=request.getParameter("page");
		String currstep=request.getParameter("step");
		int mood_comment_id=Integer.parseInt(request.getParameter("mood_comment_id"));
		/******************************************更新数据库******************************************/
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		int count=service.deleteMoodComment(mood_comment_id);
		/******************************************更新集合********************************************/
		if(count>0){
			System.out.println("删除说说评论成功！！！");
			List<MoodComment> mood_comment=(List<MoodComment>) session.getAttribute("mood_comment");
			for(int i=0;i<mood_comment.size();i++)
			{
				MoodComment blogcomment=mood_comment.get(i);
				if(blogcomment.getMoodcommentid()==mood_comment_id)
				{
					mood_comment.remove(blogcomment);
					break;
				}
			}
			response.sendRedirect("user_mood.jsp?page="+currpage+"&step="+currstep);
		}else{
			System.out.println("删除说说评论失败！！！");
		}
	}


	public void init() throws ServletException {
		
	}

}
