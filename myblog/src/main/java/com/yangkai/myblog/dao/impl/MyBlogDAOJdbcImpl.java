package com.yangkai.myblog.dao.impl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yangkai.myblog.constants.Constants;
import com.yangkai.myblog.dao.MyBlogDAO;
import com.yangkai.myblog.domain.Album;
import com.yangkai.myblog.domain.Blog;
import com.yangkai.myblog.domain.BlogComment;
import com.yangkai.myblog.domain.BlogCommentReply;
import com.yangkai.myblog.domain.Contacts;
import com.yangkai.myblog.domain.Message;
import com.yangkai.myblog.domain.MessageReply;
import com.yangkai.myblog.domain.Mood;
import com.yangkai.myblog.domain.MoodComment;
import com.yangkai.myblog.domain.MoodCommentReply;
import com.yangkai.myblog.domain.Photo;
import com.yangkai.myblog.domain.User;
import com.yangkai.myblog.domain.UserRequest;
import com.yangkai.myblog.domain.UserResponse;
import com.yangkai.myblog.factory.ConnectionFactory;
public class MyBlogDAOJdbcImpl implements MyBlogDAO{
	/*
	 * 全局变量要注意线程安全问题。
	 */
	public Connection conn = null;
	public PreparedStatement pstmt = null;
	public ResultSet rs = null;
	public ResultSet rs1 = null;
	public ResultSet rs2 = null;
	public int count=0;
	public Reader  reader = null;
	public MyBlogDAOJdbcImpl(){
		
	}
	public int getAge(Date birthday,Date today){
		int age=0;
		if(birthday!=null && today!=null){
			Calendar cal1=Calendar.getInstance();
			Calendar cal2=Calendar.getInstance();
			cal1.setTime(today);
			cal2.setTime(birthday);
			if(cal1.get(Calendar.MONTH)<cal2.get(Calendar.MONTH)){
				age=cal1.get(Calendar.YEAR)-cal2.get(Calendar.YEAR)-1;
			}else{
				age=cal1.get(Calendar.YEAR)-cal2.get(Calendar.YEAR);
			}
		}
		return age;
	}
	@Override
	public String loginCheck(String username,String password)
	{
		conn=ConnectionFactory.getConnection();
		String sql="SELECT username FROM user_info WHERE username=? and password=?";
		String loginuser=null;
		try {
			pstmt=conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE );
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				loginuser=rs.getString("username");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return loginuser;
	}
	@Override
	public List<Blog> getBlog(String username) 
	{
		conn=ConnectionFactory.getConnection();
		List<Blog> user_blog =null;
		String sql = "SELECT * FROM blog WHERE username=? AND blog_state="
							+Constants.BLOG_STATE_FOR_RELEASE
							+" ORDER BY blog_date DESC";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())) 
			{
				user_blog = new ArrayList<Blog>();
				this.blogMapper(user_blog, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return user_blog;
	}
	@Override
	public User getUser(String username)
	{
		conn=ConnectionFactory.getConnection();
		String sql="SELECT sex,birthday,address FROM user_info WHERE username=?";
		User user=null;
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				user=new User();
				user.setSex(rs.getString("sex"));
				user.setAge(this.getAge(rs.getDate("birthday"), new Date()));
				user.setBirthday(rs.getDate("birthday"));
				user.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int addUser(User user)
	{
		conn=ConnectionFactory.getConnection();
		String sql = "INSERT INTO user_info(username,password,sex,mail,address,regist_time) VALUES(?,?,?,?,?,?)";			
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getSex());
			pstmt.setString(4, user.getMail());
			pstmt.setString(5, user.getAddress());
			pstmt.setObject(6, user.getRegisttime());
			count= pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}
	@Override
	public List<String> getFriends(String username) {
		conn=ConnectionFactory.getConnection();
		List<String> friends=null;
		String sql="SELECT friend_name FROM friends WHERE username=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs=pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())){
				friends=new ArrayList<String>();
				while(rs.next()){
					friends.add(rs.getString("friend_name"));
				}
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friends;
	}
	@Override
	public Map<String,User> getFriendsInfo(String username)
	{
		conn=ConnectionFactory.getConnection();
		List<String> friends=null;
		Map<String,User> friends_info=null;
		//方法一：
		friends=this.getFriends(username);
		if(friends!=null && friends.size()>0)
		{
			friends_info = new HashMap<String,User>();
			try {
				for(int i=0;i<friends.size();i++)
				{
					String sql = "SELECT username,sex,birthday,address,mail,interest,regist_time,blog_name,blog_logo FROM user_info WHERE username=?";
					pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					pstmt.setString(1, friends.get(i));
					rs = pstmt.executeQuery();
					if (rs.next()) 
					{
						User friend_info=new User();
						friend_info.setUsername(rs.getString("username"));
						friend_info.setSex(rs.getString("sex"));
						friend_info.setAge(this.getAge(rs.getDate("birthday"), new Date()));
						friend_info.setBirthday(rs.getDate("birthday"));
						friend_info.setAddress(rs.getString("address"));
						friend_info.setMail(rs.getString("mail"));
						friend_info.setInterest(rs.getString("interest"));
						friend_info.setRegisttime((Date)rs.getObject("regist_time"));
						friend_info.setBlogname(rs.getString("blog_name"));
						friend_info.setBloglogo(rs.getString("blog_logo"));
						friends_info.put(friends.get(i),friend_info);	
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				ConnectionFactory.freeConnection(rs);
				ConnectionFactory.freeConnection(pstmt,conn);
			}
		}
		/*
		//方法二：
		String sql = "SELECT friend_name FROM friends WHERE username=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs1 = pstmt.executeQuery();
			if (!(rs1.isAfterLast() == rs1.isBeforeFirst())) 
			{
				friends_info = new HashMap<String,User>();
				while (rs1.next()) 
				{
					String str = "SELECT username,sex,birthday,address,mail,interest,regist_time,blog_name,blog_logo FROM user_info WHERE username=?";
					pstmt = conn.prepareStatement(str, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					pstmt.setString(1, rs1.getString("friend_name"));
					rs2 = pstmt.executeQuery();
					if (rs2.next()) 
					{
						User friend_info=new User();
						friend_info.setUsername(rs2.getString("username"));
						friend_info.setSex(rs2.getString("sex"));
						friend_info.setBirthday(rs2.getDate("birthday"));
						friend_info.setAddress(rs2.getString("address"));
						friend_info.setMail(rs2.getString("mail"));
						friend_info.setInterest(rs2.getString("interest"));
						friend_info.setRegisttime((Date)rs2.getObject("regist_time"));
						friend_info.setBlogname(rs2.getString("blog_name"));
						friend_info.setBloglogo(rs2.getString("blog_logo"));
						friends_info.put(rs2.getString("username"),friend_info);	
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs2);
			ConnectionFactory.freeConnection(rs1);
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		*/
		return friends_info;
	}
	@Override
	public List<Blog> getFriendsBlog(String username) 
	{
		conn=ConnectionFactory.getConnection();
		List<Blog> friends_blog = null;
		String sql = "SELECT friend_name FROM friends WHERE username=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs1 = pstmt.executeQuery();
			if (!(rs1.isAfterLast() == rs1.isBeforeFirst())) 
			{
				friends_blog = new ArrayList<Blog>();
				while (rs1.next()) 
				{
					String str = "SELECT * FROM blog WHERE username=?  AND blog_state="
								+Constants.BLOG_STATE_FOR_RELEASE+" ORDER BY blog_date DESC";
					pstmt = conn.prepareStatement(str);
					pstmt.setString(1, rs1.getString("friend_name"));
					rs2 = pstmt.executeQuery();
					this.blogMapper(friends_blog, rs2);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs2);
			ConnectionFactory.freeConnection(rs1);
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return friends_blog;
	}
	
	public void blogMapper(List<Blog> list,ResultSet rs) throws SQLException 
	{
		while (rs.next()) 
		{
			Blog blog = new Blog();
			blog.setBlogid(rs.getInt("blog_id"));
			blog.setUsername(rs.getString("username"));
			blog.setBlogsubject(rs.getString("blog_subject"));
			reader = rs.getCharacterStream("blog_content");
			//方法一：
			int len = 0;
			char[] buff = new char[20000];
			try {
				len = reader.read(buff);//一次性保存。
			} catch (IOException e) {
				e.printStackTrace();
			}
			blog.setBlogcontent(new String(buff, 0, len));
			// 方法二：貌似有问题，读取的数据不全。
			/*
			int len = 0;
			StringBuffer sb=new StringBuffer();
			char[] buff = new char[512];
			try {
				while((len=reader.read(buff))!=-1)//分块保存。
				{
					sb.append(buff);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			blog.setBlogcontent(sb.toString());
			*/
			blog.setBlogclass(rs.getString("blog_class"));
			blog.setBlogdate((Date)rs.getObject("blog_date"));
			list.add(blog);
		}
	}
	@Override
	public List<Blog> getLatestBlog() 
	{
		conn=ConnectionFactory.getConnection();
		List<Blog> latest_blog =null ;
		String sql = "SELECT * FROM blog WHERE blog_state="+Constants.BLOG_STATE_FOR_RELEASE+" ORDER BY blog_date DESC LIMIT 0,20";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())) 
			{
				latest_blog = new ArrayList<Blog>();
				this.blogMapper(latest_blog, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return latest_blog;
	}
	@Override
	public List<String> getBlogClass(String username)
	{
		conn=ConnectionFactory.getConnection();
		List<String> blog_class=null;
		String sql="SELECT blog_class FROM blog_class WHERE username=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs=pstmt.executeQuery();
			if(!(rs.isAfterLast()==rs.isBeforeFirst()))
			{
				blog_class=new ArrayList<String>();
				while(rs.next())
				{
					blog_class.add(rs.getString("blog_class"));
				}
			}
			} catch (SQLException e) {
				e.printStackTrace();
		}
		ConnectionFactory.freeConnection(rs, pstmt, conn);
		return blog_class;
	}
	@Override
	public int addBlogClass(String username,String blog_class)
	{
		conn=ConnectionFactory.getConnection();
		String sql = "INSERT INTO blog_class(username,blog_class) VALUES (?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	 
			pstmt.setString(1,username);
			pstmt.setString(2,blog_class);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt, conn);
		}
		return count;
	}
	@Override
	public int addBlog(Blog blog) 
	{
		conn=ConnectionFactory.getConnection();
		String sql = "INSERT INTO blog(username,blog_subject,blog_class,blog_content,blog_date,blog_state) VALUES (?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setString(1,blog.getUsername());
			pstmt.setString(2, blog.getBlogsubject());
			pstmt.setString(3, blog.getBlogclass());
			//大文本字段的处理。
			StringReader sr=new StringReader(blog.getBlogcontent());
			BufferedReader br=new BufferedReader(sr);
			pstmt.setClob(4, br, blog.getBlogcontent().length());
			//pstmt.setString(4,blog.getBlogcontent());
			pstmt.setObject(5,blog.getBlogdate());
			pstmt.setInt(6, blog.getBlogstate());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt, conn);
		}
		return count;
	}
	@Override
	public List<BlogComment> getBlogComment(int blog_id) 
	{
		conn=ConnectionFactory.getConnection();
		List<BlogComment> blog_comment =null;
		String sql = "SELECT * FROM blog_comment WHERE blog_id=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, blog_id);
			rs = pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())) 
			{
				blog_comment = new ArrayList<BlogComment>() ;
				while (rs.next()) 
				{
					BlogComment blogcomment = new BlogComment();
					blogcomment.setBlogcommentid(rs.getInt("blog_comment_id"));
					blogcomment.setBlogid(rs.getInt("blog_id"));
					blogcomment.setGuest(rs.getString("guest"));
					blogcomment.setBlogcommentdate((Date)rs.getObject("blog_comment_date"));
					reader = rs.getCharacterStream("blog_comment_content");
					int len = 0;
					char[] buff = new char[2048];
					try {
						len = reader.read(buff);
					} catch (IOException e) {
						e.printStackTrace();
					}
					blogcomment.setBlogcommentcontent(new String(buff, 0, len));
					blog_comment.add(blogcomment);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return blog_comment;
	}
	@Override
	public int addBlogComment(BlogComment blogcomment)
	{
		
		conn=ConnectionFactory.getConnection();
		String sql = "INSERT INTO blog_comment(blog_id,guest,blog_comment_content,blog_comment_date) VALUES (?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setInt(1,blogcomment.getBlogid());
			pstmt.setString(2, blogcomment.getGuest());
			pstmt.setString(3, blogcomment.getBlogcommentcontent());
			pstmt.setObject(4,blogcomment.getBlogcommentdate());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt, conn);
		}
		//测试代码。 
		/*
		System.out.println(blogcomment.getBlogid());
		System.out.println(blogcomment.getGuest());
		System.out.println(blogcomment.getBlogcommentcontent());
		System.out.println(blogcomment.getBlogcommentdate());
		*/
		return count;
	}
	@Override
	public int updateBlogContent(Blog blog)
	{
		//System.out.println(blog_id+";"+blog_subject+";"+blog_content);
		conn=ConnectionFactory.getConnection();
		String sql = "UPDATE blog SET blog_class=?,blog_subject=?,blog_content=? WHERE blog_id=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	
			pstmt.setString(1, blog.getBlogclass());
			pstmt.setString(2, blog.getBlogsubject());
			/*
			 * pstmt.setString(2, blog_content);
			 * 如果blog_content太长，使用PreparedStatement对象的setString()方法就会出现Data truncation异常，
			 * 因为PreparedStatement对象的setString()方法读取的字符串长度是有限的。
			 */
			StringReader sr=new StringReader(blog.getBlogcontent());
			BufferedReader br=new BufferedReader(sr);
			pstmt.setClob(3, br, blog.getBlogcontent().length());
			
			pstmt.setInt(4,blog.getBlogid());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}
	@Override
	public int deleteBlog(int blog_id) 
	{
		conn=ConnectionFactory.getConnection();
		String sql = "DELETE FROM blog WHERE blog_id=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setInt(1,blog_id);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt, conn);
		}
		return count;
	}
	@Override
	public int deleteBlogComment(int blog_comment_id) 
	{
		conn=ConnectionFactory.getConnection();
		String sql = "DELETE FROM blog_comment WHERE blog_comment_id=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setInt(1,blog_comment_id);
			count=pstmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnectionFactory.freeConnection(pstmt, conn);
		}
		return count;
	}
	@Override
	public int deleteAlbum(String loginuser, String album_name) 
	{
		conn=ConnectionFactory.getConnection();
		String sql = "DELETE FROM album WHERE username=? AND album_name=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, loginuser);
			pstmt.setString(2, album_name);
			count=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return count;
	}
	@Override
	public User getBloger(String username)
	{
		conn=ConnectionFactory.getConnection();
		User bloger=null;
		String sql = "SELECT * FROM user_info where username=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setString(1,username);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				bloger=new User();
				bloger.setId(rs.getInt("id"));
				bloger.setUsername(rs.getString("username"));
				bloger.setName(rs.getString("name"));
				bloger.setSex(rs.getString("sex"));
				bloger.setAge(this.getAge(rs.getDate("birthday"), new Date()));
				bloger.setBirthday(rs.getDate("birthday"));
				//bloger.setTelephone(rs.getString("telephone"));
				//bloger.setQq(rs.getString("qq"));
				bloger.setAddress(rs.getString("address"));
				bloger.setMail(rs.getString("mail"));
				bloger.setInterest(rs.getString("interest"));
				bloger.setBlogname(rs.getString("blog_name"));
				bloger.setBloglogo(rs.getString("blog_logo"));
				bloger.setRegisttime((Date)rs.getObject("regist_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return bloger;
	}
	@Override
	public List<BlogCommentReply> getBlogCommentReply(int blog_comment_id) 
	{	
		conn=ConnectionFactory.getConnection();
		List<BlogCommentReply> blog_comment_reply =null ;
		String sql = "SELECT * FROM blog_comment_reply WHERE blog_comment_id=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setInt(1, blog_comment_id);
			rs = pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())) 
			{
				blog_comment_reply =new ArrayList<BlogCommentReply>() ;
				while (rs.next()) 
				{
					BlogCommentReply blogcommentreply = new BlogCommentReply();
					blogcommentreply.setBlogcommentreplyid(rs.getInt("blog_comment_reply_id"));
					blogcommentreply.setBlogcommentreplycontent(rs.getString("blog_comment_reply_content"));
					blogcommentreply.setBlogcommentreplydate((Date)rs.getObject("blog_comment_reply_date"));
					blog_comment_reply.add(blogcommentreply);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return blog_comment_reply;
	}
	@Override
	public int addBlogCommentReply(BlogCommentReply blogcommentreply)
	{
		conn=ConnectionFactory.getConnection();
		String sql = "INSERT INTO blog_comment_reply( blog_comment_id,blog_comment_reply_content,blog_comment_reply_date) VALUES(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	
			pstmt.setInt(1,blogcommentreply.getBlogcommentid());
			pstmt.setString(2,blogcommentreply.getBlogcommentreplycontent());
			pstmt.setObject(3, blogcommentreply.getBlogcommentreplydate());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}
	@Override
	public int deleteBlogCommentReply(int blog_comment_reply_id) 
	{
		conn=ConnectionFactory.getConnection();
		String sql = "DELETE FROM blog_comment_reply WHERE blog_comment_reply_id=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setInt(1,blog_comment_reply_id);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}
	@Override
	public int getAlbumId(String username, String album_name) 
	{
		conn=ConnectionFactory.getConnection();
		int album_id=0;
		String sql="SELECT album_id FROM album WHERE username=? and album_name=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			pstmt.setString(2, album_name);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				album_id=rs.getInt("album_id");
			}
			} catch (SQLException e) {
				e.printStackTrace();
		}
		ConnectionFactory.freeConnection(rs, pstmt, conn);
		return album_id;
	}
	public Map<String,Map<String,List<Photo>>> getFriendsAlbumMap(List<String> friends)
	{
		Map<String,Map<String,List<Photo>>> friends_album_map=null;
		for(int i=0;i<friends.size();i++)
		{
			String username=friends.get(i);
			Map<String,List<Photo>> album_map=this.getAlbumMap(username); 
			if(album_map!=null && friends_album_map==null)
			{
				friends_album_map=new HashMap<String,Map<String,List<Photo>>>();
			}
			if(album_map!=null)
			{
				friends_album_map.put(username, album_map);
			}	
		}
		return friends_album_map;
	}
	public Map<String,List<Album>> getFriendsAlbum(List<String> friends)
	{
		conn=ConnectionFactory.getConnection();
		Map<String,List<Album>> friends_albums=null;
		for(int i=0;i<friends.size();i++)
		{
			List<Album> albums=null;
			String username=friends.get(i);
			String sql="SELECT album_name,album_desc FROM album WHERE username=?";
			try {
				pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	
				pstmt.setString(1, username);
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					if(friends_albums==null)
					{
						friends_albums=new HashMap<String,List<Album>>();
					}	
					if(albums==null)
					{
						albums=new ArrayList<Album>();
					}
					Album album=new Album();
					album.setAlbumname(rs.getString("album_name"));
					album.setAlbumdesc(rs.getString("album_desc"));
					albums.add(album);
				}
				friends_albums.put(username, albums);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return friends_albums;
	}
	@Override
	public Map<String,List<Photo>> getAlbumMap(String username) 
	{
		conn=ConnectionFactory.getConnection();
		Map<String,List<Photo>> album_map=null;
		String sql="SELECT album_id,album_name FROM album WHERE username=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs1=pstmt.executeQuery();
			if(!(rs1.isAfterLast()==rs1.isBeforeFirst()))//判断用户相册是否为空
			{
				album_map=new HashMap<String,List<Photo>>();
				String album_name=null;
				while(rs1.next())
				{
					album_name=rs1.getString("album_name");
					List<Photo> photos=this.getAlbumPhoto(rs1.getInt("album_id"));
					album_map.put(album_name, photos);
				}
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs2);
			ConnectionFactory.freeConnection(rs1);
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return album_map;
	}
	@Override
	public List<Photo> getAlbumPhoto(int album_id) 
	{	
		 List<Photo> photos=null;
		 String sql="SELECT * FROM photo WHERE album_id=?";
		 try {
			 pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			 pstmt.setInt(1, album_id);
			 rs2=pstmt.executeQuery();
			 if(!(rs2.isAfterLast()==rs2.isBeforeFirst()))//判断用户某一相册是否有照片
			 {
				 photos=new ArrayList<Photo>();
				 while(rs2.next())
				 {
					 Photo photo=new Photo();
					 photo.setPhoId(rs2.getInt("photo_id"));
					 photo.setAlbumid(rs2.getInt("album_id"));
					 photo.setPhotoname(rs2.getString("photo_name"));
					 photo.setPhotodesc(rs2.getString("photo_desc"));
					 photo.setPhotouploaddate((Date)rs2.getObject("photo_upload_date"));
					 photos.add(photo);
				 }
			 }
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		 return photos;
	}
	@Override
	public List<Album> getAlbum(String username) 
	{	
		 conn=ConnectionFactory.getConnection();
		 List<Album> albums=null;
		 String sql="SELECT * FROM album WHERE username=?";
		 try {
			 pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			 pstmt.setString(1, username);
			 rs=pstmt.executeQuery();
			 if(!(rs.isAfterLast()==rs.isBeforeFirst()))
			 {
				 albums=new ArrayList<Album>();
				 while(rs.next())
				 {	
					 Album album=new Album();
					 album.setAlbumid(rs.getInt("album_id"));
					 album.setUsername(rs.getString("username"));
					 album.setAlbumname(rs.getString("album_name"));
					 album.setAlbumdesc(rs.getString("album_desc"));
					 album.setCreatedate((Date)rs.getObject("album_create_date"));
					 albums.add(album);
				 }
			 }
		 } catch (SQLException e) {
			 e.printStackTrace();
		 } finally {
			 ConnectionFactory.freeConnection(rs, pstmt,conn);
		 }
		 return albums;
	}
	@Override
	public boolean checkUsername(String username)
	{
		conn=ConnectionFactory.getConnection();
		boolean use=false;
		String sql = "SELECT username from user_info WHERE username=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setString(1,username);
			rs=pstmt.executeQuery();
			if(rs.next()==true)
			{
				use=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return use;	
	}
	@Override
	public boolean checkAlbumName(String username, String album_name) 
	{
		conn=ConnectionFactory.getConnection();
		boolean exsist=false;
		String sql = "SELECT album_name from album WHERE username=? and album_name=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setString(1,username);
			pstmt.setString(2,album_name);
			rs=pstmt.executeQuery();
			if(rs.next()==true)
			{
				exsist=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return exsist;	
	}
	@Override
	public int uploadPhoto(List<Photo> photos) 
	{
		int[] count = null;
		conn=ConnectionFactory.getConnection();
		String sql="INSERT INTO photo(album_id,photo_name,photo_desc,photo_upload_date) VALUES (?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			for(int i=0;i<photos.size();i++)
			{
				pstmt.setInt(1, photos.get(i).getAlbumid());
				pstmt.setString(2, photos.get(i).getPhotoname());
				pstmt.setString(3, photos.get(i).getPhotodesc());
				pstmt.setObject(4, photos.get(i).getPhotouploaddate());
				pstmt.addBatch();
			}
			 count=pstmt.executeBatch();
			//System.out.println(count.length);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count.length;
	}
	@Override
	public int createAlbum(Album album) 
	{
		conn=ConnectionFactory.getConnection();
		String sql="INSERT INTO album(username,album_name,album_desc,album_create_date) VALUES (?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, album.getUsername());
			pstmt.setString(2, album.getAlbumname());
			pstmt.setString(3, album.getAlbumdesc());
			pstmt.setObject(4, album.getAlbumcreatedate());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int saveBlog(Blog blog) 
	{
		int count=0;
		conn=ConnectionFactory.getConnection();
		String sql = "INSERT INTO blog(username,blog_subject,blog_class,blog_content,blog_date,blog_status) VALUES (?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setString(1,blog.getUsername());
			pstmt.setString(2, blog.getBlogsubject());
			pstmt.setString(3, blog.getBlogclass());
			StringReader sr=new StringReader(blog.getBlogcontent());
			BufferedReader br=new BufferedReader(sr);
			pstmt.setClob(4, br, blog.getBlogcontent().length());
			//pstmt.setString(4,blog.getBlogcontent());
			pstmt.setObject(5,blog.getBlogdate());
			pstmt.setInt(6, blog.getBlogstate());
			count=pstmt.executeUpdate();
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt, conn);
		}
		return count;
	}

	@Override
	public List<Blog> getDraftBlog(String username) 
	{
		conn=ConnectionFactory.getConnection();
		List<Blog> draft_blog =null;
		String sql = "SELECT * FROM blog WHERE username=? AND blog_state="+Constants.BLOG_STATE_FOR_DRAFT+" ORDER BY blog_date DESC";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())) 
			{
				draft_blog = new ArrayList<Blog>();
				this.blogMapper(draft_blog, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return draft_blog;
	}
	@Override
	public List<Blog> getPrivateBlog(String username) 
	{
		conn=ConnectionFactory.getConnection();
		List<Blog> private_blog =null;
		String sql = "SELECT * FROM blog WHERE username=? AND blog_state="+Constants.BLOG_STATE_FOR_PRIVATE+" ORDER BY blog_date DESC";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())) 
			{
				private_blog = new ArrayList<Blog>();
				this.blogMapper(private_blog, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return private_blog;
	}
	@Override
	public int alterBlogState(int blog_state,int blog_id) 
	{
		conn=ConnectionFactory.getConnection();
		String sql = "UPDATE blog SET blog_state=? WHERE blog_id=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	
			pstmt.setInt(1,blog_state);
			pstmt.setInt(2,blog_id);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}
	@Override
	public boolean checkBlogClass(String username, String blog_class)
	{
		conn=ConnectionFactory.getConnection();
		boolean exsist=false;
		String sql = "SELECT blog_class from blog_class WHERE username=? and blog_class=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setString(1,username);
			pstmt.setString(2,blog_class);
			rs=pstmt.executeQuery();
			if(rs.next()==true)
			{
				exsist=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return exsist;	
	}
	@Override
	public int increaseBlogNum(String username) 
	{
		conn=ConnectionFactory.getConnection();
		String sql = "UPDATE user_info SET blog_num=blog_num+1 WHERE username=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	
			pstmt.setString(1, username);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}
	@Override
	public int decreaseBlogNum(String username) 
	{
		conn=ConnectionFactory.getConnection();
		String sql = "UPDATE user_info SET blog_num=blog_num-1 WHERE username=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	
			pstmt.setString(1, username);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}
	@Override
	public int alterUserInfo(User user,String username)
	{
		conn=ConnectionFactory.getConnection();
		String sql = "UPDATE user_info SET name=?,sex=?,birthday=?,mail=?,address=?,interest=? WHERE username=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getSex());
			pstmt.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
			pstmt.setString(4, user.getMail());
			pstmt.setString(5, user.getAddress());
			pstmt.setString(6, user.getInterest());
			pstmt.setString(7, username);
			count= pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs,pstmt,conn);
		}
		return count;
	}
	@Override
	public int addMessage(Message msg) 
	{
		conn=ConnectionFactory.getConnection();
		String sql = "INSERT INTO message(username,guest,message_content,message_date) VALUES (?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setString(1,msg.getUsername());
			pstmt.setString(2,msg.getGuest());
			pstmt.setString(3, msg.getMessagecontent());
			pstmt.setObject(4, msg.getMessagedate());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt, conn);
		}
		return count;
	}
	@Override
	public List<Message> getMessage(String username) 
	{
		conn=ConnectionFactory.getConnection();
		List<Message> user_message =null;
		String sql = "SELECT * FROM message WHERE username=? ORDER BY message_date DESC";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())) 
			{
				user_message = new ArrayList<Message>();
				while (rs.next()) 
				{
					Message msg = new Message();
					msg.setMessageid(rs.getInt("message_id"));
					msg.setUsername(rs.getString("username"));
					msg.setGuest(rs.getString("guest"));
					msg.setMessagedate((Date)rs.getObject("message_date"));
					reader = rs.getCharacterStream("message_content");	
					int len = 0;
					char[] buff = new char[2000];
					try {
						len = reader.read(buff);
					} catch (IOException e) {
						e.printStackTrace();
					}
					msg.setMessagecontent(new String(buff, 0, len));
					user_message.add(msg);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return user_message;
	}
	@Override
	public List<Message> getMyLeaveMessage(String guest) 
	{
		conn=ConnectionFactory.getConnection();
		List<Message> my_leave_message =null;
		String sql = "SELECT * FROM message WHERE guest=? ORDER BY message_date DESC";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, guest);
			rs = pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())) 
			{
				my_leave_message = new ArrayList<Message>();
				while (rs.next()) 
				{
					Message msg = new Message();
					msg.setMessageid(rs.getInt("message_id"));
					msg.setUsername(rs.getString("username"));
					msg.setGuest(rs.getString("guest"));
					msg.setMessagedate((Date)rs.getObject("message_date"));
					reader = rs.getCharacterStream("message_content");	
					int len = 0;
					char[] buff = new char[2000];
					try {
						len = reader.read(buff);
					} catch (IOException e) {
						e.printStackTrace();
					}
					msg.setMessagecontent(new String(buff, 0, len));
					my_leave_message.add(msg);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return my_leave_message;
	}
	@Override
	public List<Mood> getMood(String username) {
		conn=ConnectionFactory.getConnection();
		List<Mood> user_mood =null;
		String sql = "SELECT * FROM mood WHERE username=? ORDER BY mood_date DESC";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())) 
			{
				user_mood = new ArrayList<Mood>();
				while (rs.next()) 
				{
					Mood mood = new Mood();
					mood.setMoodid(rs.getInt("mood_id"));
					mood.setUsername(rs.getString("username"));
					mood.setMooddate((Date)rs.getObject("mood_date"));

					reader = rs.getCharacterStream("mood_content");	
					int len = 0;
					char[] buff = new char[2000];
					try {
						len = reader.read(buff);
					} catch (IOException e) {
						e.printStackTrace();
					}
					mood.setMoodcontent(new String(buff, 0, len));

					user_mood.add(mood);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return user_mood;
	}
	@Override
	public List<MoodComment> getMoodComment(String username) {
		conn=ConnectionFactory.getConnection();
		List<MoodComment> mood_comment =null;
		String sql = "SELECT * FROM mood_comment WHERE username=? ORDER BY mood_comment_date ASC";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())) 
			{
				mood_comment = new ArrayList<MoodComment>();
				while (rs.next()) 
				{
					MoodComment moodcomment = new MoodComment();
					moodcomment.setMoodcommentid(rs.getInt("mood_comment_id"));
					moodcomment.setMoodid(rs.getInt("mood_id"));
					moodcomment.setUsername(rs.getString("username"));
					moodcomment.setGuest(rs.getString("guest"));
					moodcomment.setMoodcommentdate((Date)rs.getObject("mood_comment_date"));
					reader = rs.getCharacterStream("mood_comment_content");	
					int len = 0;
					char[] buff = new char[2000];
					try {
						len = reader.read(buff);
					} catch (IOException e) {
						e.printStackTrace();
					}
					moodcomment.setMoodcommentcontent(new String(buff, 0, len));
					mood_comment.add(moodcomment);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return mood_comment;
	}
	@Override
	public List<MoodCommentReply> getMoodCommentReply(String username) {
		conn=ConnectionFactory.getConnection();
		List<MoodCommentReply> mood_comment_reply = null ;
		String sql = "SELECT * FROM mood_comment_reply WHERE username=? ORDER BY mood_comment_reply_date ASC";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())) 
			{
				mood_comment_reply =new ArrayList<MoodCommentReply>() ;
				while (rs.next()) 
				{
					MoodCommentReply moodcommentreply = new MoodCommentReply();
					moodcommentreply.setMoodcommentreplyid(rs.getInt("mood_comment_reply_id"));
					moodcommentreply.setMoodcommentid(rs.getInt("mood_comment_id"));
					moodcommentreply.setUsername(rs.getString("username"));
					moodcommentreply.setMoodcommentreplycontent(rs.getString("mood_comment_reply_content"));
					moodcommentreply.setMoodcommentreplydate((Date)rs.getObject("mood_comment_reply_date"));
					mood_comment_reply.add(moodcommentreply);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return mood_comment_reply;
	}
	@Override
	public int addMood(Mood mood) {
		conn=ConnectionFactory.getConnection();
		String sql = "INSERT INTO mood(username,mood_content,mood_date) VALUES(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	
			pstmt.setString(1,mood.getUsername());
			pstmt.setString(2,mood.getMoodcontent());
			pstmt.setObject(3, mood.getMooddate());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}

	@Override
	public int addMessageReply(MessageReply messagereply) 
	{
		conn=ConnectionFactory.getConnection();
		String sql = "INSERT INTO message_reply( message_id,username,guest,message_reply_content,message_reply_date) VALUES(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	
			pstmt.setInt(1,messagereply.getMessageid());
			pstmt.setString(2,messagereply.getUsername());
			pstmt.setString(3,messagereply.getGuest());
			pstmt.setString(4,messagereply.getMessagereplycontent());
			pstmt.setObject(5,messagereply.getMessagereplydate());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}

	@Override
	public int addMoodComment(MoodComment moodcomment) {
		conn=ConnectionFactory.getConnection();
		String sql = "INSERT INTO mood_comment(mood_id,username,guest,mood_comment_content,mood_comment_date) VALUES(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	
			pstmt.setInt(1,moodcomment.getMoodid());
			pstmt.setString(2,moodcomment.getUsername());
			pstmt.setString(3,moodcomment.getGuest());
			pstmt.setString(4,moodcomment.getMoodcommentcontent());
			pstmt.setObject(5, moodcomment.getMoodcommentdate());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}

	@Override
	public int addMoodCommentReply(MoodCommentReply moodcommentreply) {
		conn=ConnectionFactory.getConnection();
		String sql = "INSERT INTO mood_comment_reply(mood_comment_id,username,mood_comment_reply_content,mood_comment_reply_date) VALUES(?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	
			pstmt.setInt(1,moodcommentreply.getMoodcommentid());
			pstmt.setString(2,moodcommentreply.getUsername());
			pstmt.setString(3,moodcommentreply.getMoodcommentreplycontent());
			pstmt.setObject(4, moodcommentreply.getMoodcommentreplydate());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}

	@Override
	public int deleteMood(int mood_id) {
		conn=ConnectionFactory.getConnection();
		String sql = "DELETE FROM mood WHERE mood_id=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setInt(1,mood_id);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}

	@Override
	public int deleteMoodComment(int mood_comment_id) {
		conn=ConnectionFactory.getConnection();
		String sql = "DELETE FROM mood_comment WHERE mood_comment_id=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setInt(1,mood_comment_id);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}

	@Override
	public int deleteMoodCommentReply(int mood_comment_reply_id) {
		conn=ConnectionFactory.getConnection();
		String sql = "DELETE FROM mood_comment_reply WHERE mood_comment_reply_id=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setInt(1,mood_comment_reply_id);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}

	@Override
	public int deleteMessage(int message_id) 
	{
		conn=ConnectionFactory.getConnection();
		String sql = "DELETE FROM message WHERE message_id=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setInt(1,message_id);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}

	@Override
	public int deleteMessageReply(int message_reply_id) 
	{
		conn=ConnectionFactory.getConnection();
		String sql = "DELETE FROM message_reply WHERE message_reply_id=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setInt(1,message_reply_id);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}

	@Override
	public List<MessageReply> getAllMessageReply(String username) 
	{
		conn=ConnectionFactory.getConnection();
		List<MessageReply> all_message_reply = null ;
		String sql = "SELECT * FROM message_reply WHERE username=? or guest=? ORDER BY message_reply_date ASC";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			pstmt.setString(2, username);
			rs = pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())) 
			{
				all_message_reply =new ArrayList<MessageReply>() ;
				while (rs.next()) 
				{
					MessageReply messagereply = new MessageReply();
					messagereply.setMessagereplyid(rs.getInt("message_reply_id"));
					messagereply.setMessageid(rs.getInt("message_id"));
					messagereply.setUsername(rs.getString("username"));
					messagereply.setGuest(rs.getString("guest"));
					messagereply.setMessagereplycontent(rs.getString("message_reply_content"));
					messagereply.setMessagereplydate((Date)rs.getObject("message_reply_date"));
					all_message_reply.add(messagereply);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return all_message_reply;
	}
	
	@Override
	public List<MessageReply> getMessageReply(String username) 
	{
		conn=ConnectionFactory.getConnection();
		List<MessageReply> message_reply = null ;
		String sql = "SELECT * FROM message_reply WHERE username=? ORDER BY message_reply_date ASC";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())) 
			{
				message_reply =new ArrayList<MessageReply>() ;
				while (rs.next()) 
				{
					MessageReply messagereply = new MessageReply();
					messagereply.setMessagereplyid(rs.getInt("message_reply_id"));
					messagereply.setMessageid(rs.getInt("message_id"));
					messagereply.setUsername(rs.getString("username"));
					messagereply.setGuest(rs.getString("guest"));
					messagereply.setMessagereplycontent(rs.getString("message_reply_content"));
					messagereply.setMessagereplydate((Date)rs.getObject("message_reply_date"));
					message_reply.add(messagereply);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return message_reply;
	}

	@Override
	public List<Blog> getAllBlog(String username) 
	{
		conn=ConnectionFactory.getConnection();
		List<Blog> all_blog =null;
		String sql = "SELECT * FROM blog WHERE username=? ORDER BY blog_date DESC";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())) 
			{
				all_blog = new ArrayList<Blog>();
				this.blogMapper(all_blog, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return all_blog;
	}

	@Override
	public List<MessageReply> getMyMessageReply(String guest) 
	{
		conn=ConnectionFactory.getConnection();
		List<MessageReply> my_message_reply = null ;
		String sql = "SELECT * FROM message_reply WHERE guest=? ORDER BY message_reply_date ASC";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, guest);
			rs = pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())) 
			{
				my_message_reply =new ArrayList<MessageReply>() ;
				while (rs.next()) 
				{
					MessageReply messagereply = new MessageReply();
					messagereply.setMessagereplyid(rs.getInt("message_reply_id"));
					messagereply.setMessageid(rs.getInt("message_id"));
					messagereply.setUsername(rs.getString("username"));
					messagereply.setGuest(rs.getString("guest"));
					messagereply.setMessagereplycontent(rs.getString("message_reply_content"));
					messagereply.setMessagereplydate((Date)rs.getObject("message_reply_date"));
					my_message_reply.add(messagereply);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return my_message_reply;
	}
	@Override
	public Map<String, List<Mood>> getFriendsMood(List<String> friends) 
	{
		conn=ConnectionFactory.getConnection();
		Map<String,List<Mood>> friends_mood=null;
		for(int i=0;i<friends.size();i++)
		{
			List<Mood> friend_mood=null;
			String username=friends.get(i);
			String sql = "SELECT * FROM mood WHERE username=? ORDER BY mood_date DESC";
			try {
				pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	
				pstmt.setString(1, username);
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					if(friends_mood==null)
					{
						friends_mood=new HashMap<String,List<Mood>>();
					}	
					if(friend_mood==null)
					{
						friend_mood=new ArrayList<Mood>();
					}
					Mood mood = new Mood();
					mood.setMoodid(rs.getInt("mood_id"));
					mood.setUsername(rs.getString("username"));
					mood.setMooddate((Date)rs.getObject("mood_date"));
					reader = rs.getCharacterStream("mood_content");	
					int len = 0;
					char[] buff = new char[2000];
					try {
						len = reader.read(buff);
					} catch (IOException e) {
						e.printStackTrace();
					}
					mood.setMoodcontent(new String(buff, 0, len));
					friend_mood.add(mood);
				}
				if(friend_mood!=null){
					friends_mood.put(username, friend_mood);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return friends_mood;
	}
	@Override
	public List<MoodComment> getFriendsMoodComment(List<String> friends) {
		conn=ConnectionFactory.getConnection();
		List<MoodComment> friends_mood_comment =null;
		for(int i=0;i<friends.size();i++)
		{
			String sql="SELECT * FROM mood_comment WHERE username=? ORDER BY mood_comment_date ASC";
			try {
				pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				pstmt.setString(1, friends.get(i));
				rs = pstmt.executeQuery();
				while (rs.next()) 
				{
					if(friends_mood_comment ==null){
						friends_mood_comment = new ArrayList<MoodComment>();
					}
					MoodComment moodcomment = new MoodComment();
					moodcomment.setMoodcommentid(rs.getInt("mood_comment_id"));
					moodcomment.setMoodid(rs.getInt("mood_id"));
					moodcomment.setUsername(rs.getString("username"));
					moodcomment.setGuest(rs.getString("guest"));
					moodcomment.setMoodcommentdate((Date)rs.getObject("mood_comment_date"));
					reader = rs.getCharacterStream("mood_comment_content");	
					int len = 0;
					char[] buff = new char[2000];
					try {
						len = reader.read(buff);
					} catch (IOException e) {
						e.printStackTrace();
					}
					moodcomment.setMoodcommentcontent(new String(buff, 0, len));
					friends_mood_comment.add(moodcomment);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return friends_mood_comment;
	}
	@Override
	public List<MoodCommentReply> getFriendsMoodCommentReply(List<String> friends) {
		conn=ConnectionFactory.getConnection();
		List<MoodCommentReply> friends_mood_comment_reply =null;
		for(int i=0;i<friends.size();i++)
		{
			String sql="SELECT * FROM mood_comment_reply WHERE username=? ORDER BY mood_comment_reply_date ASC";
			try {
				pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				pstmt.setString(1, friends.get(i));
				rs = pstmt.executeQuery();
				while (rs.next()) 
				{
					if(friends_mood_comment_reply ==null){
						friends_mood_comment_reply = new ArrayList<MoodCommentReply>();
					}
					MoodCommentReply moodcommentreply = new MoodCommentReply();
					moodcommentreply.setMoodcommentreplyid(rs.getInt("mood_comment_reply_id"));
					moodcommentreply.setMoodcommentid(rs.getInt("mood_comment_id"));
					moodcommentreply.setUsername(rs.getString("username"));
					moodcommentreply.setMoodcommentreplydate((Date)rs.getObject("mood_comment_reply_date"));
					reader = rs.getCharacterStream("mood_comment_reply_content");	
					int len = 0;
					char[] buff = new char[2000];
					try {
						len = reader.read(buff);
					} catch (IOException e) {
						e.printStackTrace();
					}
					moodcommentreply.setMoodcommentreplycontent(new String(buff, 0, len));
					friends_mood_comment_reply.add(moodcommentreply);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return friends_mood_comment_reply;
	}
	@Override
	public Map<String, List<Message>> getFriendsMessage(List<String> friends,String username) {
		conn=ConnectionFactory.getConnection();
		Map<String,List<Message>> friends_message=null;
		for(int i=0;i<friends.size();i++)
		{
			List<Message> friend_message=null;
			String guest=friends.get(i);
			String sql = "SELECT * FROM message WHERE username=? AND guest=? ORDER BY message_date DESC";
			try {
				pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	
				pstmt.setString(1, username);
				pstmt.setString(2, guest);
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					if(friends_message==null)
					{
						friends_message=new HashMap<String,List<Message>>();
					}	
					if(friend_message==null)
					{
						friend_message=new ArrayList<Message>();
					}
					Message msg = new Message();
					msg.setMessageid(rs.getInt("message_id"));
					msg.setUsername(rs.getString("username"));
					msg.setGuest(rs.getString("guest"));
					msg.setMessagedate((Date)rs.getObject("message_date"));
					reader = rs.getCharacterStream("message_content");	
					int len = 0;
					char[] buff = new char[2000];
					try {
						len = reader.read(buff);
					} catch (IOException e) {
						e.printStackTrace();
					}
					msg.setMessagecontent(new String(buff, 0, len));
					friend_message.add(msg);
				}
				if(friend_message!=null){
					friends_message.put(username, friend_message);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return friends_message;
	}
	@Override
	public List<UserRequest> getUserRequest(String username) {
		conn=ConnectionFactory.getConnection();
		List<UserRequest> user_request =null;
		String sql = "SELECT * FROM user_request WHERE username=? AND request_state="+Constants.USER_REQUEST_UNCHECKED+" ORDER BY request_date DESC";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())) 
			{
				user_request = new ArrayList<UserRequest>();
				while (rs.next()) 
				{
					UserRequest userrequest = new UserRequest();
					userrequest.setRequestid(rs.getInt("request_id"));
					userrequest.setUsername(rs.getString("username"));
					userrequest.setGuest(rs.getString("guest"));
					userrequest.setRequestdate((Date)rs.getObject("request_date"));
					reader = rs.getCharacterStream("request_content");	
					int len = 0;
					char[] buff = new char[2000];
					try {
						len = reader.read(buff);
					} catch (IOException e) {
						e.printStackTrace();
					}
					userrequest.setRequestcontent(new String(buff, 0, len));
					user_request.add(userrequest);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return user_request;
	}
	@Override
	public int addUserRequestResponse(UserResponse userresponse) {
		conn=ConnectionFactory.getConnection();
		String sql = "INSERT INTO user_response (request_id,username,guest,response_content,response_date) VALUES(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	
			pstmt.setInt(1,userresponse.getRequestid());
			pstmt.setString(2,userresponse.getUsername());
			pstmt.setString(3,userresponse.getGuest());
			pstmt.setString(4,userresponse.getResponsecontent());
			pstmt.setObject(5,userresponse.getResponsedate());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}
	@Override
	public int alterRequestState(int request_id) {
		conn=ConnectionFactory.getConnection();
		String sql = "UPDATE user_request SET request_state=? WHERE request_id=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	
			pstmt.setInt(1,Constants.USER_REQUEST_CHECKED);
			pstmt.setInt(2,request_id);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}
	@Override
	public int addUserRequest(UserRequest userrequest) {
		conn=ConnectionFactory.getConnection();
		String sql = "INSERT INTO user_request (username,guest,request_content,request_date,request_state) VALUES(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	
			pstmt.setString(1,userrequest.getUsername());
			pstmt.setString(2,userrequest.getGuest());
			pstmt.setString(3,userrequest.getRequestcontent());
			pstmt.setObject(4,userrequest.getRequestdate());
			pstmt.setInt(5,userrequest.getRequeststate());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}
	@Override
	public List<UserResponse> getUserResponse(String username) {
		conn=ConnectionFactory.getConnection();
		List<UserResponse> user_response =null;
		String sql = "SELECT * FROM user_response WHERE guest=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (!(rs.isAfterLast() == rs.isBeforeFirst())) 
			{
				user_response = new ArrayList<UserResponse>();
				while (rs.next()) 
				{
					UserResponse userresponse = new UserResponse();
					userresponse.setResponseid(rs.getInt("response_id"));
					userresponse.setRequestid(rs.getInt("request_id"));
					userresponse.setUsername(rs.getString("username"));
					userresponse.setGuest(rs.getString("guest"));
					userresponse.setResponsedate((Date)rs.getObject("response_date"));
					reader = rs.getCharacterStream("response_content");	
					int len = 0;
					char[] buff = new char[2000];
					try {
						len = reader.read(buff);
					} catch (IOException e) {
						e.printStackTrace();
					}
					userresponse.setResponsecontent(new String(buff, 0, len));
					user_response.add(userresponse);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return user_response;
	}
	@Override
	public List<Contacts> getContacts(String username) {
		conn=ConnectionFactory.getConnection();
		List<Contacts> user_contacts=null;
		String sql = "SELECT * FROM contacts_list where username=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setString(1,username);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				if(user_contacts==null){
					user_contacts=new ArrayList<Contacts>();
				}
				Contacts contacts=new Contacts();
				contacts.setContactsid(rs.getInt("contacts_id"));
				contacts.setName(rs.getString("name"));
				contacts.setContactsclass(rs.getString("contacts_class"));
				contacts.setTelephone(rs.getString("telephone"));
				contacts.setQq(rs.getString("qq"));
				contacts.setMail(rs.getString("mail"));
				user_contacts.add(contacts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return user_contacts;
	}
	@Override
	public List<String> getContactsClass(String username) {
		conn=ConnectionFactory.getConnection();
		List<String> user_contacts_class=null;
		String sql="SELECT contacts_class FROM contacts_class WHERE username=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				if(user_contacts_class==null){
					user_contacts_class=new ArrayList<String>();
				}
				user_contacts_class.add(rs.getString("contacts_class"));
			}
			} catch (SQLException e) {
				e.printStackTrace();
		}
		ConnectionFactory.freeConnection(rs, pstmt, conn);
		return user_contacts_class;
	}
	@Override
	public List<Contacts> queryContacts(String name, String username) {
		conn=ConnectionFactory.getConnection();
		List<Contacts> contacts_query_result=null;
		String sql = "SELECT * FROM contacts_list where username=? AND name LIKE ?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setString(1,username);
			pstmt.setString(2,name);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				if(contacts_query_result==null){
					contacts_query_result=new ArrayList<Contacts>();
				}
				Contacts contacts=new Contacts();
				contacts.setContactsid(rs.getInt("contacts_id"));
				contacts.setName(rs.getString("name"));
				contacts.setContactsclass(rs.getString("contacts_class"));
				contacts.setTelephone(rs.getString("telephone"));
				contacts.setQq(rs.getString("qq"));
				contacts.setMail(rs.getString("mail"));
				contacts_query_result.add(contacts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(rs, pstmt,conn);
		}
		return contacts_query_result;
	}
	@Override
	public int addContacts(Contacts contacts) {
		conn=ConnectionFactory.getConnection();
		String sql = "INSERT INTO contacts_list (username,name,contacts_class,telephone,qq,mail) VALUES (?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	
			pstmt.setString(1,contacts.getUsername());
			pstmt.setString(2,contacts.getName());
			pstmt.setString(3,contacts.getContactsclass());
			pstmt.setString(4,contacts.getTelephone());
			pstmt.setString(5,contacts.getQq());
			pstmt.setString(6,contacts.getMail());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}
	@Override
	public int addContactsClass(String username,String contacts_class) {
		conn=ConnectionFactory.getConnection();
		String sql = "INSERT INTO contacts_class (username,contacts_class) VALUES (?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	 
			pstmt.setString(1,username);
			pstmt.setString(2,contacts_class);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt, conn);
		}
		return count;
	}
	@Override
	public int deleteContacts(int contacts_id) {
		conn=ConnectionFactory.getConnection();
		String sql = "DELETE FROM contacts_list WHERE contacts_id=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setInt(1,contacts_id);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}
	@Override
	public int alterBlogInfo(User user) {
		conn=ConnectionFactory.getConnection();
		String sql = "UPDATE user_info SET blog_name=?,blog_logo=? WHERE username=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	
			pstmt.setString(1,user.getBlogname());
			pstmt.setString(2,user.getBloglogo());
			pstmt.setString(3,user.getUsername());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}
	@Override
	public int deletePhoto(int photo_id) {
		conn=ConnectionFactory.getConnection();
		String sql = "DELETE FROM photo WHERE photo_id=?";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);		
			pstmt.setInt(1,photo_id);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt,conn);
		}
		return count;
	}
	@Override
	public int addFriend(String friend_name,String username) {
		conn=ConnectionFactory.getConnection();
		String sql = "INSERT INTO friends (username,friend_name) VALUES (?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);	 
			pstmt.setString(1,username);
			pstmt.setString(2,friend_name);
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.freeConnection(pstmt, conn);
		}
		return count;
	}
}