package org.yangkai.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.yangkai.jdbc.tools.ConnectionTool;

public class BigText {

	private ResultSet ub_rs = null;// 用户博客记录集
	//private List<BlogListBean> b_list=null;
	private PreparedStatement pstmt = null;
	private Connection conn = null;
	
	public List<BlogListBean> getUserbloglist(String loginuser) 
	{
		System.out.println(conn==null);
		conn = ConnectionTool.getConnection();
		System.out.println(conn==null);
		List<BlogListBean> ub_list = new ArrayList<BlogListBean>();
		String sql = "SELECT * FROM blog WHERE username=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, loginuser);
			ub_rs = pstmt.executeQuery();
			while (ub_rs.next()) 
			{
				BlogListBean mub_list = new BlogListBean();
				mub_list.setBlogId(ub_rs.getInt("blog_id"));
				mub_list.setUsername(ub_rs.getString("username"));
				mub_list.setBlogsubject(ub_rs.getString("blog_subject"));
				mub_list.setBlogcontent(ub_rs.getString("blog_content"));//以字符串的形式接受大文本。
				mub_list.setBlogclass(ub_rs.getString("blog_class"));
				mub_list.setBlogdate(ub_rs.getString("blog_date"));
				ub_list.add(mub_list);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//ConnectionTool.freeConnection(ub_rs, pstmt, conn);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(conn==null);
		return ub_list;
	}
	public static void main(String[] args) 
	{
		BigText b=new BigText();
		List<BlogListBean> ub_list=b.getUserbloglist("杨开");
		for(int i=0;i<ub_list.size();i++)
		{
			BlogListBean mub_list=ub_list.get(i);
			System.out.println( mub_list.getBlogcontent());
			System.out.println("----------------------------------------");
		}
	}
}
