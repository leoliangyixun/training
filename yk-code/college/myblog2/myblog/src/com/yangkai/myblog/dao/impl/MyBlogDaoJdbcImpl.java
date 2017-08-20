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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.yangkai.myblog.constants.Constants;
import com.yangkai.myblog.dao.MyBlogDao;
import com.yangkai.myblog.domain.Album;
import com.yangkai.myblog.domain.Blog;
import com.yangkai.myblog.domain.BlogComment;
import com.yangkai.myblog.domain.BlogCommentReply;
import com.yangkai.myblog.domain.Message;
import com.yangkai.myblog.domain.MessageReply;
import com.yangkai.myblog.domain.Mood;
import com.yangkai.myblog.domain.MoodComment;
import com.yangkai.myblog.domain.Photo;
import com.yangkai.myblog.domain.User;
import com.yangkai.myblog.factory.ConnectionFactory;
public class MyBlogDaoJdbcImpl implements MyBlogDao{
	/*
	 * 全局变量要注意线程安全问题。
	 */
	public Connection conn = null;
	public PreparedStatement pstmt = null;
	public ResultSet rs = null;
	public ResultSet rs1 = null;
	public ResultSet rs2 = null;
	public Reader  reader = null;
	public int count=0;
	public MyBlogDaoJdbcImpl(){
		
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
			while(rs.next())
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
		String sql = "SELECT * FROM blog WHERE username=? AND blog_state="+Constants.BLOG_STATE_FOR_RELEASE+" ORDER BY blog_date DESC";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
			ConnectionFactory.freeConnection(rs,pstmt,conn);
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
		List<String> blog_class=null;
		conn=ConnectionFactory.getConnection();
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
				bloger.setBirthday(rs.getDate("birthday"));
				//bloger.setTelephone(rs.getString("tel"));
				//bloger.setQq(rs.getString("qq"));
				bloger.setAddress(rs.getString("address"));
				bloger.setMail(rs.getString("mail"));
				bloger.setInterest(rs.getString("interest"));
				bloger.setBlogname(rs.getString("blog_name"));
				bloger.setBloglogo(rs.getString("blog_logo"));
				bloger.setRegisttime((Date)rs.getObject("regist_time"));
				bloger.setBlognum(rs.getInt("blog_num"));
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
	public Map<String,Map<String,List<Photo>>> getFriendsAlbumMap(List<String> friends){
		return null;
		
	}
	public Map<String,List<Album>> getFriendsAlbum(List<String> friends){
		return null;
		
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
					List<Photo> pho=this.getAlbumPhoto(rs1.getInt("album_id"));
					album_map.put(album_name, pho);
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
	public int alterUserInfo(User user) 
	{
		conn=ConnectionFactory.getConnection();
		String sql = "INSERT INTO user_info"
		            +"(useranme,password,sex,address,regist_time)"
					+" VALUES(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getSex());
			pstmt.setDate(5, new java.sql.Date(user.getBirthday().getTime()));
			pstmt.setString(6, user.getTelephone());
			pstmt.setString(7, user.getQq());
			pstmt.setString(8, user.getMail());
			pstmt.setString(9, user.getAddress());
			pstmt.setString(10, user.getInterest());
			pstmt.setObject(11, user.getRegisttime());
			pstmt.setString(12, user.getBlogname());
		    pstmt.setString(13, user.getBloglogo());
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
		List<Message> user_message =null;
		String sql = "SELECT * FROM message WHERE guest=? ORDER BY message_date DESC";
		try {
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, guest);
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
	public List<Mood> getMood(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addMood(Mood mood) {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addMoodCommentReply(BlogCommentReply blogcommentreply) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMood(Mood mood) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMoodComment(int mood_comment_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMoodCommentReply(int mood_comment_reply_id) {
		// TODO Auto-generated method stub
		return 0;
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
}