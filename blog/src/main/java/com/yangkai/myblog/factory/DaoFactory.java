package com.yangkai.myblog.factory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.dao.MyBlogDAO;
import com.yangkai.myblog.dao.impl.MyBlogDAOJdbcImpl;

public class DaoFactory {

	public DaoFactory() {
		
	}
	//DaoFactory类与Servlet API出现了耦合。
	public static MyBlogDAO getMyBlogDao(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		MyBlogDAO dao=null;
		Object objDao=session.getAttribute("dao");
		if(objDao==null)
		{
			dao=new MyBlogDAOJdbcImpl();
			session.setAttribute("dao", dao);
		}else{
			 dao=(MyBlogDAO)objDao;
		}
		return dao;
	}
}
