package com.yangkai.servlet.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements ServletContextAttributeListener {

 
    public MyServletContextAttributeListener() {
       
    }

    public void attributeAdded(ServletContextAttributeEvent sce) {
      // System.out.println("���Attrubute");
      // System.out.println(sce.getName()+":"+sce.getValue());
    }

	
    public void attributeReplaced(ServletContextAttributeEvent sce) {
    	 //System.out.println("�滻Attrubute");
    	// System.out.println(sce.getName()+":"+sce.getValue());
    }

	
    public void attributeRemoved(ServletContextAttributeEvent sce) {
    	//System.out.println("ɾ��Attrubute");
    	//System.out.println(sce.getName()+":"+sce.getValue());
    }
	
}
