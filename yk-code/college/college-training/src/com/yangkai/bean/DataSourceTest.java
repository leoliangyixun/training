package com.yangkai.bean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

public class DataSourceTest extends HttpServlet {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	DataSource ds = null;
	ServletConfig config = null;

	public DataSourceTest() {

	}

	public ResultSet execute(String sql) {
		try {
			ServletContext sc = config.getServletContext();
			ds = (DataSource) sc.getAttribute("DS");
			conn = ds.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void closeConnection() {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DataSourceTest dst = new DataSourceTest();
		ResultSet rs = null;
		String sql = "SELECT * FROM bookinfo";
		rs = dst.execute(sql);
		if (rs == null) {
			System.out.println("failed");
		}

		/*
		 * DataSourceBean sb=new DataSourceBean(); ResultSet rs=null; String
		 * sql="SELECT * FROM bookinfo"; rs=dsb.execute(sql);
		 * System.out.println("<table border='1'>"); System.out.print(
		 * "<tr><td>ISBN</td><td>ͼ�����</td><td>������</td><td>ͼ�鵥��</td><td>�����</td></tr>"
		 * ); try{ while(rs.next()) { System.out.println("<tr>");
		 * System.out.println("<td>"+rs.getString("ISBN")+"</td>");
		 * System.out.println("<td>"+rs.getString("name")+"</td>");
		 * System.out.println("<td>"+rs.getString("publisher")+"</td>");
		 * System.out.println("<td>"+rs.getFloat("price")+"</td>");
		 * System.out.println("<td>"+rs.getInt("num")+"</td>");
		 * System.out.println("</tr>"); } }catch (SQLException e) {
		 * e.printStackTrace(); } System.out.println("</table>");
		 */
	}

}
