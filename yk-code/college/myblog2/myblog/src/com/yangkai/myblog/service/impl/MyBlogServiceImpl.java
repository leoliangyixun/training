package com.yangkai.myblog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yangkai.myblog.constants.Constants;
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
import com.yangkai.myblog.dao.MyBlogDao;
import com.yangkai.myblog.service.MyBlogService;

public class MyBlogServiceImpl implements MyBlogService{

	public HttpServletRequest request;
	public MyBlogDao dao;
	public MyBlogServiceImpl(MyBlogDao dao)
	{
		this.dao=dao;
	}
	/*
	public MyBlogServiceImpl(MyBlogDao dao,HttpServletRequest request)
	{
		this.request=request;
		this.dao=dao;
	}
	public static MyBlogDao dao=DaoFactory.getMyBlogService(request);
	public MyBlogDao dao=DaoFactory.getMyBlogDao(request);//只有在实例化的时候才会执行。
	*/
	@Override
	public List<Blog> getFriendsBlog(String username)
	{
		return dao.getFriendsBlog(username);	
	}
	@Override
	public List<Blog> getBlog(String username)
	{
		return dao.getBlog(username);
	}
	@Override
	public Map<String,List<Photo>> getAlbumMap(String username)
	{
		return dao.getAlbumMap(username);
	}
	@Override
	public List<Album> getAlbum(String username)
	{
		return dao.getAlbum(username);
	}
	@Override
	public List<String> getAlbumMapKey(Map<String,List<Photo>> album_map)
	{
		List<String> album_map_key=null;
		if(album_map!=null)
		{
			album_map_key=new ArrayList<String>();
			for(String key:album_map.keySet())
			{
				album_map_key.add(key);
			}
		}
		return album_map_key;
	}
	@Override
	public List<String> getBlogClass(String username) 
	{
		return dao.getBlogClass(username);
	}
	@Override
	public List<Blog> getLatestBlog() 
	{
		return dao.getLatestBlog();
	}
	@Override
	public String loginCheck(String username, String password) 
	{
		return dao.loginCheck(username, password);
	}
	@Override
	public User getUser(String username) 
	{
		return dao.getUser(username);
	}
	@Override
	public List<String> getFriends(String username) 
	{
		return dao.getFriends(username);
	}
	@Override
	public Map<String,User> getFriendsInfo(String username) 
	{
		return dao.getFriendsInfo(username);
	}
	@Override
	public int addBlogClass(String username,String blog_class)
	{
		return dao.addBlogClass(username,blog_class);
	}
	@Override
	public int addBlog(Blog blog) 
	{
		return dao.addBlog(blog);
	}
	@Override
	public List<BlogComment> getBlogComment(int blog_id) 
	{
		return dao.getBlogComment(blog_id);
	}
	@Override
	public int addBlogComment(BlogComment blogcomment) 
	{
		return dao.addBlogComment(blogcomment);
	}
	@Override
	public int updateBlogContent(Blog blog) 
	{
		return dao.updateBlogContent(blog);
	}
	@Override
	public int deleteBlog(int blog_id) 
	{
		return dao.deleteBlog(blog_id);
	}
	@Override
	public int deleteBlogComment(int blog_comment_id)
	{
		return dao.deleteBlogComment(blog_comment_id);
	}
	@Override
	public User getBloger(String username) 
	{
		return dao.getBloger(username);
	}
	@Override
	public List<BlogCommentReply> getBlogCommentReply(int blog_comment_id) 
	{
		return dao.getBlogCommentReply(blog_comment_id);
	}
	@Override
	public int addBlogCommentReply(BlogCommentReply blogcommentreply) 
	{
		return dao.addBlogCommentReply(blogcommentreply);
	}
	@Override
	public int deleteBlogCommentReply(int blog_comment_reply_id) 
	{
		return dao.deleteBlogCommentReply(blog_comment_reply_id);
	}
	@Override
	public int getAlbumId(String username, String album_name) 
	{
		return dao.getAlbumId(username, album_name);
	}
	@Override
	public List<Photo> getAlbumPhoto(int album_id) 
	{
		return dao.getAlbumPhoto(album_id);
	}
	@Override
	public boolean checkAlbumName(String username, String album_name) 
	{
		return dao.checkAlbumName(username, album_name);
	}
	@Override
	public int uploadPhoto(List<Photo> photos) 
	{
		return dao.uploadPhoto(photos);
	}
	@Override
	public int createAlbum(Album album) 
	{
		return dao.createAlbum(album);
	}
	@Override
	public int saveBlog(Blog blog) 
	{
		return dao.saveBlog(blog);
	}
	@Override
	public List<Blog> getDraftBlog(String username) 
	{
		return dao. getDraftBlog(username);
	}
	@Override
	public int alterBlogState(int blog_state,int blog_id)
	{
		return dao.alterBlogState(blog_state,blog_id);
	}
	@Override
	public boolean checkBlogClass(String username, String blog_class) 
	{
		return dao.checkBlogClass(username, blog_class);
	}
	@Override
	public boolean checkUsername(String username) 
	{
		return dao.checkUsername(username);
	}
	@Override
	public int alterBlogNum(String username,int blog_num_mark) 
	{
		if(blog_num_mark==Constants.BLOG_ADD_NUM)
		{
			return dao.increaseBlogNum(username);
		}
		if(blog_num_mark==Constants.BLOG_CUT_NUM)
		{
			return dao.decreaseBlogNum(username);
		}
		return 0;
	}
	@Override
	public int addUser(User user) 
	{
		return dao.addUser(user);
	}
	@Override
	public int regist(User user) 
	{
		return dao.addUser(user);
	}
	@Override
	public int alterUserInfo(User user) 
	{
		return 0;
	}
	@Override
	public List<Blog> getPrivateBlog(String username) 
	{
		return dao.getPrivateBlog(username);
	}
	@Override
	public int addMessage(Message msg) 
	{
		return dao.addMessage(msg);
	}
	@Override
	public List<Message> getMessage(String username) 
	{
		return dao.getMessage(username);
	}
	public List<Message> getMyLeaveMessage(String username) 
	{
		return dao.getMyLeaveMessage(username);
	}
	@Override
	public List<Mood> getMood(String username) 
	{
		return null;
	}
	@Override
	public int addMood(Mood mood) 
	{
		return 0;
	}
	@Override
	public int addMessageReply(MessageReply messagereply)
	{
		return dao.addMessageReply(messagereply);
	}
	@Override
	public int addMoodComment(MoodComment moodcomment) 
	{
		return 0;
	}
	@Override
	public int addMoodCommentReply(BlogCommentReply blogcommentreply) 
	{
		return 0;
	}
	@Override
	public int deleteMood(Mood mood) 
	{
		return 0;
	}
	@Override
	public int deleteMoodComment(int mood_comment_id) 
	{
		return 0;
	}
	@Override
	public int deleteMoodCommentReply(int mood_comment_reply_id) 
	{
		return 0;
	}
	@Override
	public int deleteMessage(int message_id) 
	{
		return dao.deleteMessage(message_id);
	}
	@Override
	public int deleteMessageReply(int message_reply_id) 
	{
		return dao.deleteMessageReply(message_reply_id);
	}
	@Override
	public int cutBlogNumber(String username)
	{
		return 0;
	}
	@Override
	public List<MessageReply> getMessageReply(String username) 
	{
		return dao.getMessageReply(username);
	}
	@Override
	public List<MessageReply> getAllMessageReply(String username) 
	{
		return dao.getAllMessageReply(username);
	}
	@Override
	public List<Blog> getAllBlog(String username) 
	{
		return dao.getAllBlog(username);
	}
	@Override
	public List<MessageReply> getMyMessageReply(String guest) 
	{
		return dao.getMyMessageReply(guest);
	}
	@Override
	public Map<String, Map<String, List<Photo>>> getFriendsAlbumMap(List<String> friends) 
	{	
		return dao.getFriendsAlbumMap(friends);
	}
	@Override
	public Map<String, List<Album>> getFriendsAlbum(List<String> friends) 
	{	
		return dao.getFriendsAlbum(friends);
	}
	@Override
	public List<String> getFriendsAlbumMapKey() 
	{
		return null;
	}
}
