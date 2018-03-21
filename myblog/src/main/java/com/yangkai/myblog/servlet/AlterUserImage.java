package com.yangkai.myblog.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.Photo;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncoderUtil;
import com.yangkai.myblog.tools.FileUploadUtil;

public class AlterUserImage extends HttpServlet {


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
			String loginuser=(String)session.getAttribute("loginuser");
			FileUploadUtil tool=new FileUploadUtil(request);
			String savePath=this.getServletContext().getRealPath("/");
			savePath=savePath+"upload"+File.separator+"Ïà²á"+File.separator+loginuser+File.separator+"image";
			Map<String,String> imageMap=tool.saveImage(savePath);
			String image=null;
			Set<Map.Entry<String, String>> imageSet=imageMap.entrySet();
			for(Entry<String, String> entry:imageSet){
				image=entry.getValue();
			}
			if(image!=null){
				response.sendRedirect("user_setting.jsp");
				System.out.println("ÐÞ¸ÄÍ¼Ïñ³É¹¦£¡£¡£¡");
			}else{
				response.sendRedirect("include/error.jsp");
				System.out.println("ÐÞ¸ÄÍ¼ÏñÊ§°Ü£¡£¡£¡");
			}
		
	}

	
	public void init() throws ServletException {
		
	}

}
