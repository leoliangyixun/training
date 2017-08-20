package com.yangkai.myblog.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
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
import com.yangkai.myblog.tools.EncodingTool;

public class CreateAlbum extends HttpServlet {

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
			String album_name=request.getParameter("album_name");
			String album_desc=request.getParameter("album_desc");
			String create_date=request.getParameter("create_date");
			if(album_name!=null)
			{
				album_name=EncodingTool.setCharacterEncoding(album_name);
				File albumDir=new File(this.getServletContext().getRealPath("/")
						+"upload"+File.separator+"相册"+File.separator+loginuser+File.separator+album_name);
				if(!albumDir.exists())
				{
					albumDir.mkdir();
					Album album=new Album();
					album.setUsername(loginuser);
					album.setAlbumname(album_name);
					if(!album_desc.equals(""))
					{
						album_desc=EncodingTool.setCharacterEncoding(album_desc);
					}
					album.setAlbumdesc(album_desc);
					try {
						album.setCreatedate(DateFormat.getDateTimeInstance().parse(create_date));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					MyBlogService service=ServiceFactory.getMyBlogService(request);
					System.out.println("CreateAlbum:"+service);
					int count=service.createAlbum(album);
					if(count>0)
					{
						List<Album> albums=service.getAlbum(loginuser);
						Map<String,List<Photo>> album_map=service.getAlbumMap(loginuser);
						List<String> album_map_key=service.getAlbumMapKey(album_map);
						session.setAttribute("album_map", album_map);
						session.setAttribute("album_map_key", album_map_key);
						session.setAttribute("albums", albums);
						/*
						 * 测试相册是否创建成功。
						 */
						/*
						System.out.println(loginuser);
						System.out.println(album_name);
						System.out.println(album_desc);
						System.out.println(create_date);
						*/
						response.sendRedirect("create_success.jsp?album_name="+URLEncoder.encode(album_name,"UTF-8"));
					}
				}
			}	
	}

	public void init() throws ServletException {
		//ServletConfig config=this.getServletConfig();
		
	}

}
