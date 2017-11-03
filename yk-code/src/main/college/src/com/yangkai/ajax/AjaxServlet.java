package com.yangkai.ajax;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * @author QQ:373672872
 * @category 处理JSP页面提交的的异步查询请求，并以XML文件格式返回结果集
 */
public class AjaxServlet extends HttpServlet {
	private static final long	serialVersionUID	= 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//当前页
		int currpage = Integer.parseInt(request.getParameter("currpage")==null?"1":request.getParameter("currpage"));
		//总的记录数
		int total = this.getResultCount();
		//分页单位
		int pagesize = 5;
		//Page类对象
		Page page = new Page(total,currpage,pagesize);
		//用于返回给前台页面的XML文档
		StringBuffer xmlDOM = new StringBuffer();
		//调用查询方法
		ResultSet rs = this.getResultSet(page.getStart(),page.getPagesize());
		//添加XML根节点
		xmlDOM.append("<root>");
		try {
			//添加数据库查询出来的数据
			xmlDOM.append("<persons>");
			while (rs.next()) {
				xmlDOM.append("<person>");
				xmlDOM.append("<pid>" + rs.getString("pid") + "</pid>");
				xmlDOM.append("<pname>" + rs.getString("pname") + "</pname>");
				xmlDOM.append("<age>" + rs.getString("age") + "</age>");
				xmlDOM.append("</person>");
			}
			rs.close();
			xmlDOM.append("</persons>");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		//添加分页信息
		xmlDOM.append("<page>");
		xmlDOM.append("<currpage>"+page.getCurrpage()+"</currpage>");
		xmlDOM.append("<pagecount>"+page.getPagecount()+"</pagecount>");
		xmlDOM.append("</page>");
		xmlDOM.append("</root>");
		//调用打印方法
		this.print(request, response, xmlDOM.toString());

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doGet(request, response);
		
	}
	
	/**
	 * @category 打印出XMLDOM文档,用于前台页面的接收
	 * @author QQ:373672872
	 * @param request
	 * @param response
	 * @param xmlDOM
	 * @throws IOException
	 */
	private void print(HttpServletRequest request, HttpServletResponse response,String xmlDOM) throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		out.print(xmlDOM);
		out.close();
	}
	
	/**
	 * @author QQ:373672872
	 * @category 返回当前页的查询结果
	 * @param 行号
	 * @param 长度
	 * @return ResultSet
	 */
	private ResultSet getResultSet(int start,int len){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConn();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		/** Mysql:	String sql = "select * from person order by pid limit ?,?"; */
		String sql = "select top "+len+" * from person where pid >= ? order by pid";
		
		try {
			pstmt = conn.prepareStatement(sql);
			/** Mysql:
			pstmt.setInt(1, start);
			pstmt.setInt(2, len);
			*/
			pstmt.setInt(1, start);
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}
	
	/**
	 * @author QQ:373672872
	 * @return 数据库中总的记录数
	 */
	private int getResultCount(){
		int count=0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConn();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		String sql = "select count(*) from person";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		try {
			while(rs.next()){
				count = rs.getInt(1);
			}
			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return count;
	}
	/**
	 * @author QQ:373672872
	 * @return 取得数据库连接驱动
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection getConn() throws ClassNotFoundException, SQLException{
		
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		/** Mysql:	driver = "com.mysql.jdbc.Driver"; */
		String url = "jdbc:sqlserver://127.0.0.1:1433;database=persondb";
		/** Mysql:	url = "jdbc:mysql://localhost:3306/persondb"; */
		String user = "sa";
		/** Mysql:	user = "root"; */ 
		String password = "adley";
		
		Class.forName(driver);
		return DriverManager.getConnection(url,user,password);
	}

}
