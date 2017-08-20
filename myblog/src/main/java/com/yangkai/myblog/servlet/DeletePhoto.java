package com.yangkai.myblog.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.Photo;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncoderUtil;

public class DeletePhoto extends HttpServlet {

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

		HttpSession session = request.getSession(true);
		String loginuser = (String) session.getAttribute("loginuser");
		String currpage = request.getParameter("page");
		String currstep = request.getParameter("step");
		String album_name = URLDecoder.decode(request.getParameter("album_name"), "UTF-8");
		int photo_id = Integer.parseInt(request.getParameter("photo_id"));
		MyBlogService service = ServiceFactory.getMyBlogService(request);
		/************************************************更新数据库************************************************/
		int count = service.deletePhoto(photo_id);
		if (count > 0) {
			Map<String, List<Photo>> album_map = (Map<String, List<Photo>>) session.getAttribute("album_map");			
			List<Photo> photos = album_map.get(album_name);
			for (int i = 0; i < photos.size(); i++) {
				if (photos.get(i).getPhotoid() == photo_id) {
					/********************************************更新集合********************************************/
					Photo photo=photos.remove(i);//获取要删除的照片对象。
					String photo_name = photo.getPhotoname();
					/*******************************************更新文件系统*****************************************/
					File photoDir = new File(this.getServletContext().getRealPath("/")
							+ "upload"+ File.separator+ "相册"+ File.separator+ loginuser
							+ File.separator+ album_name+ File.separator+ photo_name);
					if(photoDir.exists() && photoDir.isFile()){
						boolean deletePhotoSuccess=photoDir.delete();
						if(deletePhotoSuccess){
							response.sendRedirect("album_view.jsp?album_name="+URLEncoder.encode(album_name,"UTF-8")+"&page="+currpage+"&step="+currstep+"");
							System.out.println("删除照片"+photo_name+"成功！！！");
						}else{
							response.sendRedirect("include/error.jsp");
							System.out.println("删除照片"+photo_name+"失败！！！");
						}
					}
					break;
				}
			}
		}
		
	}

	public void init() throws ServletException {

	}

}
