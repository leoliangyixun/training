package com.yangkai.servlet.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class MyServletContextListener implements ServletContextListener {
	public MyServletContextListener() {

	}

	public void contextInitialized(ServletContextEvent arg) {
		ServletContext sc = arg.getServletContext();
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(sc.getInitParameter("jndi"));
			sc.setAttribute("DS", ds);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		//System.out.println("initialized");
	}

	public void contextDestroyed(ServletContextEvent arg) {

	}

}
