package com.training.fileupload.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadServlet extends HttpServlet {

	public FileUploadServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			//FileItemFactory factory=new DiskFileItemFactory();
			File file=new File(request.getServletContext().getRealPath("/FileUpload"));
			//File file=this.getServletContext().getRealPath("/FileUpload");
			FileItemFactory factory=new DiskFileItemFactory(1024*1024, file);
			ServletFileUpload fileUpload=new ServletFileUpload(factory);
			try {
				List<FileItem> items=fileUpload.parseRequest(request);
				for(int i=0;i<items.size();i++){
					if(items.get(i).isFormField()){
						
					}else{
						
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
	}

	public void init() throws ServletException {
		
	}

}
