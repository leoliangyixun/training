package com.yangkai.servlet.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyListener implements ServletContextListener,
		ServletContextAttributeListener, HttpSessionListener,
		HttpSessionAttributeListener, HttpSessionActivationListener,
		HttpSessionBindingListener, ServletRequestListener,
		ServletRequestAttributeListener {

	public MyListener() {

	}

	public void requestDestroyed(ServletRequestEvent arg0) 
	{

	}

	public void attributeAdded(HttpSessionBindingEvent arg0) 
	{

	}

	public void contextInitialized(ServletContextEvent arg0) 
	{

	}

	public void sessionDidActivate(HttpSessionEvent arg0) 
	{

	}

	public void valueBound(HttpSessionBindingEvent arg0) 
	{

	}

	public void attributeAdded(ServletContextAttributeEvent arg0) 
	{

	}

	public void attributeRemoved(ServletContextAttributeEvent arg0) 
	{

	}

	public void sessionDestroyed(HttpSessionEvent arg0) 
	{

	}

	public void attributeRemoved(HttpSessionBindingEvent arg0) 
	{

	}

	public void attributeAdded(ServletRequestAttributeEvent arg0) 
	{

	}

	public void valueUnbound(HttpSessionBindingEvent arg0) 
	{

	}

	public void sessionWillPassivate(HttpSessionEvent arg0) 
	{

	}

	public void sessionCreated(HttpSessionEvent arg0) 
	{

	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) 
	{

	}

	public void attributeReplaced(ServletContextAttributeEvent arg0) 
	{

	}

	public void attributeRemoved(ServletRequestAttributeEvent arg0) 
	{

	}

	public void contextDestroyed(ServletContextEvent arg0) 
	{

	}

	public void attributeReplaced(ServletRequestAttributeEvent arg0) 
	{

	}

	public void requestInitialized(ServletRequestEvent arg0)
	{

	}

}
