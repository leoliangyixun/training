package com.yangkai.myblog.factory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.dao.MyBlogDao;
import com.yangkai.myblog.dao.impl.MyBlogDaoJdbcImpl;

public class DaoFactory {

	public DaoFactory() {
		
	}
	//InstanceFactory����Servlet API��������ϡ�
	public static MyBlogDao getMyBlogDao(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		MyBlogDao dao=null;
		Object obj=session.getAttribute("dao");
		if(obj==null)
		{
			dao=new MyBlogDaoJdbcImpl();
			session.setAttribute("dao", dao);
		}else{
			 dao=(MyBlogDao)obj;
		}
		return dao;
	}
}
