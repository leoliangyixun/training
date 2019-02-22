package com.yangkai.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class ListenerCheckServlet extends HttpServlet {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	DataSource ds = null;

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		String sql = "SELECT * FROM bookinfo";
		rs = this.execute(sql);
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
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public ResultSet execute(String sql) {
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);

	}

	public void init() throws ServletException {
		try {
			ServletContext sc = getServletContext();
			ds = (DataSource) sc.getAttribute("DS");
			conn = ds.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
