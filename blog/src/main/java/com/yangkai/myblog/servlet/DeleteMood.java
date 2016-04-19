package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.Mood;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class DeleteMood extends HttpServlet {

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
			int mood_id=Integer.parseInt(request.getParameter("mood_id"));
			MyBlogService service=ServiceFactory.getMyBlogService(request);
			int count=service.deleteMood(mood_id);
			if(count>0){
				List<Mood> user_mood=(List<Mood>) session.getAttribute("user_mood");
				for(int i=0;i<user_mood.size();i++){
					if(user_mood.get(i).getMoodid()==mood_id){
						user_mood.remove(i);
						break;
					}
				}
				response.sendRedirect("user_mood.jsp?page="+currpage+"&step="+currstep);
				System.out.println("删除说说成功！！！");
			}else{
				response.sendRedirect("include/error.jsp");
				System.out.println("删除说说失败！！！");
			}
	}

	public void init() throws ServletException {
		
	}

}
