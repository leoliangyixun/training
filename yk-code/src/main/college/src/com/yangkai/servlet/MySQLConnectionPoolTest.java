package com.yangkai.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class MySQLConnectionPoolTest extends HttpServlet {
	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		DataSource ds = null;
		Context ctx = null;
		try {
			// ctx=(Context)new InitialContext().lookup("java:comp/env");
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/book");

		} catch (NamingException e) {
			e.printStackTrace();
		}

		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		out.println(ds);
		/*
		 * if(ds==null) { out.println("�Ҳ���ָ����MySQL���ӳ�!!!"); } else{
		 * out.println("�ҵ�ָ����MySQL���ӳ�"); }
		 */
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void init() throws ServletException {

	}
}
