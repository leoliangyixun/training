package com.yangkai.myblog.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.Album;
import com.yangkai.myblog.domain.Photo;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class DeleteAlbum extends HttpServlet {


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
			String loginuser=(String)session.getAttribute("loginuser");
			Map<String,List<Photo>> album_map=(Map<String, List<Photo>>) session.getAttribute("album_map");
			List<String> album_map_key=(List<String>) session.getAttribute("album_map_key");
			List<Album> albums=(List<Album>) session.getAttribute("albums");
			String album_name=URLDecoder.decode(request.getParameter("album_name"), "UTF-8");
			String currpage=request.getParameter("page");
			String currstep=request.getParameter("step");
			
			/*********************************更新各个集合*********************************/
			album_map.remove(album_name);
			album_map_key.remove(album_name);
			for(int i=0;i<albums.size();i++)
			{
				if(albums.get(i).getAlbumname().equals(album_name))
				{
					albums.remove(i);
					break;
				}
			}
			/*********************************更新磁盘文件*********************************/
			File albumDir=new File(this.getServletContext().getRealPath("/")
					+"upload"+File.separator+"相册"+File.separator+loginuser+File.separator+album_name);
			//System.out.println(albumDir.exists());
			File[] albumDirs=albumDir.listFiles();
			for(int i=0;i<albumDirs.length;i++)
			{
				albumDirs[i].delete();
			}
			boolean isDelete=albumDir.delete();
			/*********************************更新数据库*********************************/
			if(isDelete==true)
			{
				MyBlogService service=ServiceFactory.getMyBlogService(request);
				int count=service.deleteAlbum(loginuser, album_name);
				if(count>0)
				{
					response.sendRedirect("user_album.jsp?page="+currpage+"&step="+currstep);
				}else{
					response.sendRedirect("include/error.jsp");
				}
			}else{
				response.sendRedirect("include/error.jsp");
			}
	}

	public void init() throws ServletException {
		
	}

}
