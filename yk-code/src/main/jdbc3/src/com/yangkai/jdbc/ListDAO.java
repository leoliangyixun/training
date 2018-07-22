package com.yangkai.jdbc;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.yangkai.jdbc.tools.ConnectionTool;
public class ListDAO {
	private ResultSet fr_rs = null;// 好友记录集
	private ResultSet lb_rs = null;// 最新博客记录集
	private ResultSet ub_rs = null;// 用户博客记录集
	private ResultSet fb_rs = null;// 好友博客记录集
	private List<BlogListBean> blog_list=new ArrayList<BlogListBean>();	
	private PreparedStatement pstmt = null;
	private Connection conn = null;
	Reader rd = null;
	Integer count=null;
	public ListDAO() {

	}

	public void getUserbloglist(String loginuser) 
	{
		conn = ConnectionTool.getConnection();
		
		String sql = "SELECT * FROM user_blog WHERE username=?";
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
				rd = ub_rs.getCharacterStream("blog_content");
				int len = 0;
				char[] buff = new char[20000];
				try {
						len = rd.read(buff);
				} catch (IOException e) {
					e.printStackTrace();
				}
				mub_list.setBlogcontent(new String(buff, 0, len));
				mub_list.setBlogclass(ub_rs.getString("blog_class"));
				mub_list.setBlogdate(ub_rs.getString("blog_date"));
				blog_list.add(mub_list);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionTool.freeConnection(ub_rs, pstmt, conn);
	}

	public void getFriendsbloglist(String loginuser) 
	{
		conn = ConnectionTool.getConnection();
		String sql = "SELECT friends FROM user_friends WHERE username=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, loginuser);
			fr_rs = pstmt.executeQuery();
			while (fr_rs.next()) 
			{
				String str = "SELECT * FROM user_blog WHERE username=?";
				pstmt = conn.prepareStatement(str,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
				pstmt.setString(1, fr_rs.getString("friends"));
				fb_rs = pstmt.executeQuery();
				while (fb_rs.next()) 
				{
					BlogListBean mub_list = new BlogListBean();
					mub_list.setBlogId(fb_rs.getInt("blog_id"));
					mub_list.setUsername(fb_rs.getString("username"));
					mub_list.setBlogsubject(fb_rs.getString("blog_subject"));
					rd = fb_rs.getCharacterStream("blog_content");
					int len = 0;
					char[] buff = new char[20000];
					try {
						len = rd.read(buff);
					} catch (IOException e) {
						e.printStackTrace();
					}
					mub_list.setBlogcontent(new String(buff, 0, len));
					mub_list.setBlogclass(fb_rs.getString("blog_class"));
					mub_list.setBlogdate(fb_rs.getString("blog_date"));
					blog_list.add(mub_list);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionTool.freeConnection(fr_rs, pstmt, conn);
		ConnectionTool.freeConnection(fb_rs, pstmt, conn);
	}

	public void getLatestbloglist() 
	{
		conn = ConnectionTool.getConnection();
		String sql = "SELECT * FROM user_blog ORDER BY blog_id ASC LIMIT 0,5";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			lb_rs = pstmt.executeQuery();
			while (lb_rs.next()) 
			{
				BlogListBean mub_list = new BlogListBean();
				mub_list.setBlogId(lb_rs.getInt("blog_id"));
				mub_list.setUsername(lb_rs.getString("username"));
				mub_list.setBlogsubject(lb_rs.getString("blog_subject"));
				mub_list.setBlogclass(lb_rs.getString("blog_class"));
				mub_list.setBlogdate(lb_rs.getString("blog_date"));
				rd = lb_rs.getCharacterStream("blog_content");
				int len = 0;
				char[] buff = new char[20000];
				try {
					len = rd.read(buff);
				} catch (IOException e) {
					e.printStackTrace();
				}
				mub_list.setBlogcontent(new String(buff, 0, len));
				blog_list.add(mub_list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionTool.freeConnection(lb_rs, pstmt, conn);
	}

	public List<BlogListBean> getBloglist()
	{
		return blog_list;
	}
	public static void  main(String[] args)
	{
		ListDAO dao1=new ListDAO();
		dao1.getLatestbloglist();
		List<BlogListBean> blog_list=dao1.getBloglist();
		
		for(int i=0;i<blog_list.size();i++)
		{
			BlogListBean b1=blog_list.get(i);
			System.out.println(b1.getBlogId());
		}
		//System.out.println(blog_list.size());
		System.out.println("---------------");
		ListDAO dao2=new ListDAO();
		//dao2.getLatestbloglist();
		dao2.getFriendsbloglist("fuck");
		dao2.getUserbloglist("fuck");
		List<BlogListBean> blog_list2=dao2.getBloglist();
		if(blog_list2==null)
		{
			System.out.println("Are you kidding me!!!");
		}
		else{
			for(int i=0;i<blog_list2.size();i++)
			{
				BlogListBean b2=blog_list2.get(i);
				System.out.println(b2.getBlogId());
				
			}
			System.out.println(blog_list2.size());//长度为并不意味着集合为空，切记。
		}
		
		
	}
}