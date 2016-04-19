package com.yangkai.myblog.servlet;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import com.yangkai.myblog.tools.FileUploadUtil;
import com.yangkai.myblog.tools.EncoderUtil;
public class UploadPhoto extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		/**
		 * ʹ���ϴ������ദ�� ��
		 */
		String loginuser=(String)session.getAttribute("loginuser");
		FileUploadUtil tool=new FileUploadUtil(request);
		String album_name=EncoderUtil.encode(tool.getParameter("album_name"));
		String savePath=this.getServletContext().getRealPath("/");
		savePath=savePath+"upload"+File.separator+"���"+File.separator+loginuser+File.separator+album_name;
		Map<String,String> fileNamesMap=tool.saveAll(savePath);
		List<Photo> photos=new ArrayList<Photo>();
		String desc1=tool.getParameter("desc1");
		String desc2=tool.getParameter("desc2");
		String desc3=tool.getParameter("desc3");
		Date upload_date = null;
		try {
			upload_date = DateFormat.getDateTimeInstance().parse(tool.getParameter("upload_date"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		/*
		System.out.println(loginuser);
		System.out.println(album_name);
		System.out.println(desc1);
		System.out.println(desc2);
		System.out.println(desc3);
		System.out.println(upload_date);
		*/
		/*
		 * ��ȡ�ϴ�����album_id
		 */
		MyBlogService service=ServiceFactory.getMyBlogService(request);
		/*
		 *��ȡ���ID��Ϊ��ȷ���ϴ�����Ƭ�����û����ĸ����
		 */
		int album_id = service.getAlbumId(loginuser,album_name);
		//System.out.println(album_id);
		Photo photo1=new Photo();
		Photo photo2=new Photo();
		Photo photo3=new Photo();
		if(fileNamesMap.containsKey("pic1"))
		{
			if(desc1!=null)
			{
				desc1=EncoderUtil.encode(desc1);
				photo1.setPhotodesc(desc1);
			}
			String fileName=fileNamesMap.get("pic1");
			photo1.setAlbumid(album_id);
			photo1.setPhotoname(fileName);
			photo1.setPhotouploaddate(upload_date);
			photos.add(photo1);
		}
		if(fileNamesMap.containsKey("pic2"))
		{
			if(desc1!=null)
			{
				desc2=EncoderUtil.encode(desc2);
				photo2.setPhotodesc(desc2);
			}
			String fileName=fileNamesMap.get("pic2");
			photo2.setAlbumid(album_id);
			photo2.setPhotoname(fileName);
			photo2.setPhotouploaddate(upload_date);
			photos.add(photo2);
		}
		if(fileNamesMap.containsKey("pic3"))
		{
			if(desc3!=null)
			{
				desc3=EncoderUtil.encode(desc3);
				photo3.setPhotodesc(desc3);
			}
			String fileName=fileNamesMap.get("pic3");
			photo3.setAlbumid(album_id);
			photo3.setPhotoname(fileName);
			photo3.setPhotouploaddate(upload_date);
			photos.add(photo3);
		}
		/*
		 * ���ϴ�����Ƭ��Ϣ���浽���ݿ⡣
		 */
		int count=service.uploadPhoto(photos);
		if(count>0)
		{
			/*
			 * ��Ƭ�ϴ��ɹ��󣬴����ݿ������»�ȡ���º����ᡣ
			 */
			Map<String,List<Photo>> album_map=service.getAlbumMap(loginuser);
			List<String> album_map_key=service.getAlbumMapKey(album_map);
			session.setAttribute("album_map", album_map);
			session.setAttribute("album_map_key", album_map_key);
			
			response.sendRedirect("include/upload_success.jsp?album_name="+URLEncoder.encode(album_name,"UTF-8"));
		}
		else{
			response.sendRedirect("include/error.jsp");
		}
		/*
		 * ���Դ���
		 */
		/*
		for(int i=0;i<photos.size();i++)
		{
			//int album_id=photos.get(i).getAlbumId();
			String photo_name=photos.get(i).getPhotoName();
			String photo_desc=photos.get(i).getPhotoDesc();
			String photo_date=photos.get(i).getUploadDate();
			//System.out.println("���ID��"+ album_id);
			System.out.println("�ļ�����"+photo_name);
			System.out.println("�ļ�������"+photo_desc);
			System.out.println("�ϴ�ʱ�䣺"+photo_date);
		}
		*/
		/*
		List<String> fileNameLists=tool.getUploadFileLists(savePath);
		for(int i=0;i<fileNameLists.size();i++)
		{
			System.out.println(fileNameLists.get(i));
		}
		String[] fileNames=tool.getUploadFileNames(savePath);
		for(int i=0;i<fileNames.length;i++)
		{
			System.out.println(fileNames[i]);
		}
		*/
	}

}
