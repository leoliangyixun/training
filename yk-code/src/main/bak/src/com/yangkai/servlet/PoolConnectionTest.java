package com.yangkai.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class PoolConnectionTest extends HttpServlet {

	public PoolConnectionTest() {
		super();
	}

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
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/book");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM bookinfo");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		out.println("<div align=\"center\"><table border=\"1\" bordercolor=\"#000000\" cellspacing=\"0\" cellpadding=\"5\" style=\"border-collapse: collapse\">");
		out.print("<tr align=\"center\" bgcolor=\"#FF9999\"><td>ISBN</td><td>ͼ�����</td><td>������</td><td>ͼ�鵥��</td><td>�����</td></tr>");
		try {
			while (rs.next()) {
				out.println("<tr style=\"font-size:12px\">");
				out.println("<td>" + rs.getString("ISBN") + "</td>");
				out.println("<td>" + rs.getString("name") + "</td>");
				out.println("<td>" + rs.getString("publisher") + "</td>");
				out.println("<td>" + rs.getFloat("price") + "</td>");
				out.println("<td>" + rs.getInt("num") + "</td>");
				out.println("</tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("</table></div>");
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
