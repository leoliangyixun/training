package com.yangkai.myblog.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.yangkai.myblog.constants.Constants;
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
import com.yangkai.myblog.dao.MyBlogDAO;
import com.yangkai.myblog.service.MyBlogService;

public class MyBlogServiceImpl implements MyBlogService{

	public HttpServletRequest request;
	public MyBlogDAO dao;
	public MyBlogServiceImpl(MyBlogDAO dao)
	{
		this.dao=dao;
	}
	/*
	public MyBlogServiceImpl(MyBlogDAO dao,HttpServletRequest request)
	{
		this.request=request;
		this.dao=dao;
	}
	public static MyBlogDAO dao=DaoFactory.getMyBlogService(request);
	public MyBlogDAO dao=DaoFactory.getMyBlogDao(request);//只有在实例化的时候才会执行。
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
	public Map<String,List<String>> getFriendsAlbumMapKey(Map<String,Map<String,List<Photo>>> friends_album_map,List<String> friends)
	{

		Map<String,List<String>> friends_album_map_key=null;
		
		if(friends!=null && friends_album_map!=null  )
		{
			
			friends_album_map_key=new HashMap<String,List<String>>();
			
			for(int i=0;i<friends.size();i++)
			{
				
				List<String> photo_names=null;
				Map<String,List<Photo>> album_map=friends_album_map.get(friends.get(i));
				
				if(album_map!=null)
				{
					photo_names=new ArrayList<String>();
					for(String key:album_map.keySet())
					{
						photo_names.add(key);
					}
				}
				friends_album_map_key.put(friends.get(i), photo_names);
				
			}
		}
		return friends_album_map_key;
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
	/*
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
	*/
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
	public int alterUserInfo(User user,String username) 
	{
		return dao.alterUserInfo(user,username);
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
	public List<MessageReply> getAllMessageReply(String username) 
	{
		return dao.getAllMessageReply(username);
	}
	@Override
	public List<Mood> getMood(String username) 
	{
		return dao.getMood(username);
	}
	@Override
	public List<MoodComment> getMoodComment(String username) 
	{
		return dao.getMoodComment(username);
	}
	@Override
	public List<MoodCommentReply> getMoodCommentReply(String username) 
	{
		return dao.getMoodCommentReply(username);
	}
	@Override
	public int addMood(Mood mood) 
	{
		return dao.addMood(mood);
	}
	@Override
	public int addMessageReply(MessageReply messagereply)
	{
		return dao.addMessageReply(messagereply);
	}
	@Override
	public int addMoodComment(MoodComment moodcomment) 
	{
		return dao.addMoodComment(moodcomment);
	}
	@Override
	public int addMoodCommentReply(MoodCommentReply moodcommentreply) 
	{
		return dao.addMoodCommentReply(moodcommentreply);
	}
	@Override
	public int deleteMood(int mood_id) 
	{
		return dao.deleteMood(mood_id);
	}
	@Override
	public int deleteMoodComment(int mood_comment_id) 
	{
		return dao.deleteMoodComment(mood_comment_id);
	}
	@Override
	public int deleteMoodCommentReply(int mood_comment_reply_id) 
	{
		return dao.deleteMoodCommentReply(mood_comment_reply_id);
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
	public List<MessageReply> getMessageReply(String username) 
	{
		return dao.getMessageReply(username);
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
	public int deleteAlbum(String username, String album_name) 
	{
		return dao.deleteAlbum(username, album_name);
	}
	@Override
	public Map<String, List<Mood>> getFriendsMood(List<String> friends) 
	{
		if(friends!=null && friends.size()>0){
			return dao.getFriendsMood(friends);
		}else{
			return null;
		}
	}
	@Override
	public List<Mood> getFirendsMood(Map<String, List<Mood>> friends_mood) {
		List<Mood> all_friends_mood=new ArrayList<Mood>();
		Set<Map.Entry<String, List<Mood>>> set=friends_mood.entrySet();
		for(Entry<String, List<Mood>> entry:set){
			all_friends_mood.addAll(entry.getValue());
		}
		return all_friends_mood;
	}
	@Override
	public List<MoodComment> getFriendsMoodComment(List<String> friends) {
		if(friends!=null && friends.size()>0){
			return dao.getFriendsMoodComment(friends);
		}else{
			return null;
		}
	}
	@Override
	public List<MoodCommentReply> getFriendsMoodCommentReply(List<String> friends) {
		if(friends!=null && friends.size()>0){
			return dao.getFriendsMoodCommentReply(friends);
		}else{
			return null;
		}
	}
	@Override
	public Map<String, List<Message>> getFriendsMessage(List<String> friends,String username) {
		 //从DAO层获取数据。
		if(friends!=null && friends.size()>0){
			return dao.getFriendsMessage(friends,username);
		}else{
			return null;
		}
	}
	@Override
	public List<Message> getFriendsMessage(List<String> friends,List<Message> user_message) {
		//从user_message集合中获取数据。
		List<Message> all_friends_message=null;
		for(int i=0;i<friends.size();i++){
			for(int j=0;j<user_message.size();j++){
				if(user_message.get(j).getGuest().equals(friends.get(i))){
					if(all_friends_message==null){
						all_friends_message=new ArrayList<Message>();
					}
					all_friends_message.add(user_message.get(j));
				}
			}
		}
		return all_friends_message;
	}
	@Override
	public List<Message> getFriendsMessage(Map<String, List<Message>> friends_message) {
		List<Message> all_friends_message=new ArrayList<Message>();
		Set<Map.Entry<String, List<Message>>> set=friends_message.entrySet();
		for(Entry<String, List<Message>> entry:set){
			all_friends_message.addAll(entry.getValue());
		}
		return all_friends_message;
	}
	@Override
	public List<UserRequest> getUserRequest(String username) {
		return dao.getUserRequest(username);
	}
	@Override
	public int addUserRequestResponse(UserResponse userresponse) {
		return dao.addUserRequestResponse(userresponse);
	}
	@Override
	public int alterRequestState(int request_id) {
		return dao.alterRequestState(request_id);
		
	}
	@Override
	public int addUserRequest(UserRequest userrequest) {
		return dao.addUserRequest(userrequest);
		
	}
	@Override
	public List<UserResponse> getUserResponse(String username) {
		return dao.getUserResponse(username);
	}
	@Override
	public List<String> getContactsClass(String username) {
		return dao.getContactsClass(username);
	}
	@Override
	public List<Contacts> getContacts(String username) {
		return dao.getContacts(username);
	}
	@Override
	public List<Contacts> queryContacts(String name, String username) {
		return dao.queryContacts(name,username);
	}
	@Override
	public int addContacts(Contacts contacts) {
		return dao.addContacts(contacts);
	}
	@Override
	public int addContactsClass(String username,String contacts_class) {
		return dao.addContactsClass(username,contacts_class);
	}
	@Override
	public int deleteContacts(int contacts_id) {
		return dao.deleteContacts(contacts_id);
	}
	@Override
	public int alterBlogInfo(User user) {
		return dao.alterBlogInfo(user);
	}
	@Override
	public int deletePhoto(int photo_id) {
		return dao.deletePhoto(photo_id);
	}
	@Override
	public int addFriend(String friend_name,String username) {
		return dao.addFriend(friend_name,username);
	}
}
