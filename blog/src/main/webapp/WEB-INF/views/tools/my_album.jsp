<%@page import="com.yangkai.myblog.tools.PagingUtil"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.yangkai.myblog.domain.Photo"%>
<%@page import="com.yangkai.myblog.domain.Album"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String loginuser=(String)session.getAttribute("loginuser");
	String curr_page=request.getParameter("page");
	String curr_step=request.getParameter("step");
	Map<String, List<Photo>> album_map=(Map<String, List<Photo>>)session.getAttribute("album_map");
	List<String> album_map_key=(List<String>)session.getAttribute("album_map_key");
	List<Album> user_album=(List<Album>)session.getAttribute("user_album");
	out.println("<div style='margin-left:7px; border:#E97B56 solid 1px;margin-top:20px;padding-bottom:20px'>");
	if(album_map!=null && album_map.size()>0)	
	{
		Integer currpage=null;
		Integer countpage=null;
		int rowpagesize=2;
		int colpagesize=3;
		int pagesize=rowpagesize * colpagesize;
		int pagestep=5;
		Integer currstep=null;
		Integer countstep=null;
		countpage=PagingUtil.getCountpage(album_map_key.size(), pagesize);
		countstep=PagingUtil.getCountstep(album_map_key.size(), pagesize, pagestep);
		currpage=PagingUtil.getCurrentpage(curr_page, countpage);
		currstep=PagingUtil.getCurrentstep(curr_step, countstep);
		
		int i,j;
		int count=0;
		out.println("<div align='left'>");
		out.println("<table border='0' bordercolor='#438945' cellspacing='0' cellpadding='10' "
						+"style='border-collapse:collapse'>");
		for(i=0;i<rowpagesize;i++)
		{
			out.println("<tr align='center'>");	
			for(j=0;j<colpagesize;j++)
			{
				count=pagesize*(currpage-1)+3*i+j;
				if(count<album_map_key.size())
				{	
					String album_name=album_map_key.get(count);
					if(album_map.get(album_name)!=null && album_map.get(album_name).size()>0)
					{
						List<Photo> photos=album_map.get(album_name);
						int photo_num=photos.size();
						Photo photo=photos.get(0); 

						String photo_name=photo.getPhotoname();
						out.println("<td align='left'><a href='album_view.jsp?album_name="
								    +URLEncoder.encode(album_name, "UTF-8")+"&photo_num="+photo_num+"'>"
									+"<img src='upload/相册/"+loginuser+"/"+album_name+"/" +photo_name
									+"' width='160' height='220'/></a></br>"
									+"相册名：<a href='album_view.jsp?album_name="
									+URLEncoder.encode(album_name, "UTF-8")+"&photo_num="+photo_num+"'>"+album_name+"</a></br>"
									+"<a>"+photo_num+"张</a></br><a href='#' style='margin-right:10px'>编辑</a><a href='DeleteAlbum?album_name="
									+URLEncoder.encode(album_name, "UTF-8")+"&page="+currpage+"&step="+currpage+"'>删除</a></td>");
					}else{
						 out.println("<td align='left'><img src='upload/error.jpg' width='160' height='220'/></br>相册名：<a>"
									+album_name+"</a></br><a href='photo_upload.jsp?album_name="+URLEncoder.encode(album_name, "UTF-8")
									+"'>上传照片</a></br><a href='#' style='margin-right:10px'>编辑</a><a href='DeleteAlbum?album_name="
									+URLEncoder.encode(album_name, "UTF-8")+"&page="+currpage+"&step="+currpage+"'>删除</a></td>");
					}					
				}else{
					break;
				}
			}
			out.println("</tr>");	
		}
		out.println("</table>");
		out.println("</div>");
		
	    out.println("<div align='center' id='pages'>");
	    if(countpage>pagestep)
	    {   	
	    	out.println("<a href='javascript:getMyAlbum("+(pagestep*(currstep-1))+","+(currstep-1)+")'>&laquo;</a>");
	    	for(int k=(currstep-1)*pagestep+1;k<=currstep*pagestep && k<=countpage;k++)
	    	{	
	    		out.println("<a href='javascript:getMyAlbum("+k+","+currstep+")'>"+k+"</a>");
	    	}
	    	int max=(pagestep*currstep+1)<=countpage?(pagestep*currstep+1):countpage;
	    	out.println("<a href='javascript:getMyAlbum("+max+","+(currstep+1)+")'>&raquo;</a>");	
	    }else{
	    	if(countpage>1)
	    	{
	    		for(int k=1;k<=countpage;k++)
	    		{
	    			out.println("<a href='javascript:getMyAlbum("+k+","+null+")'>"+k+"</a>");
	    		}
	    	}
	    }
	    out.println("</div>");	
	}else{
		 out.println("<div  align='center'><a class='success_style'>你还没有相册!!!</a><a href='album_create.jsp' class='continue_style'>创建相册<a></div>");
	}
	out.println("</div>");
	%>