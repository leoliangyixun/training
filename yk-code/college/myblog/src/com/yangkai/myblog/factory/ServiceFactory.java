package com.yangkai.myblog.factory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.dao.MyBlogDAO;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.service.impl.MyBlogServiceImpl;

public class ServiceFactory {

	public ServiceFactory() {
		
	}
	public static MyBlogService getMyBlogService(HttpServletRequest request)
	{
     	HttpSession session=request.getSession();
		MyBlogDAO dao=null;
     	MyBlogService service=null;
//		Object objDao=session.getAttribute("dao");
//		if(objDao==null)
//		{
//			dao=DaoFactory.getMyBlogDAO(request);
//			session.setAttribute("dao", dao);
//		}else{
//			 dao=(MyBlogDAO)objDao;
//		}
		dao=DaoFactory.getMyBlogDao(request);
		Object objService=session.getAttribute("service");
		if(objService==null)
		{
			service=new MyBlogServiceImpl(dao);
			session.setAttribute("service", service);
		}else{
			service=(MyBlogService)objService;
		}
		return service;
	}
}
