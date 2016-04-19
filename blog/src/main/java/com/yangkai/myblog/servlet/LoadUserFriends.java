package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.domain.Album;
import com.yangkai.myblog.domain.Blog;
import com.yangkai.myblog.domain.Message;
import com.yangkai.myblog.domain.Mood;
import com.yangkai.myblog.domain.MoodComment;
import com.yangkai.myblog.domain.MoodCommentReply;
import com.yangkai.myblog.domain.Photo;
import com.yangkai.myblog.domain.User;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;

public class LoadUserFriends extends HttpServlet {

	public void destroy() {
		super.destroy(); 
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			HttpSession session=request.getSession(true);
			String loginuser=(String) session.getAttribute("loginuser");
			List<String> friends=null;
			Map<String,User> friends_info=null;
			List<Blog> friends_blog=null;
			
			Map<String,Map<String,List<Photo>>> friends_album_map=null;
			Map<String,List<Album>> friends_albums=null;
			Map<String,List<String>> friends_album_map_key=null;
			
			Map<String,List<Mood>>friends_mood=null;
			List<Mood> all_friends_mood=null;
			List<MoodComment> friends_mood_comment=null;
			List<MoodCommentReply> friends_mood_comment_reply=null;
			
			List<Message> user_message=null;
			Map<String,List<Message>> friends_message=null;
			List<Message> all_friends_message=null;
			
			MyBlogService service=ServiceFactory.getMyBlogService(request);
			/***************************获取用户好友信息 ****************************/
			friends=service.getFriends(loginuser);
			friends_info=service.getFriendsInfo(loginuser);
			/***************************获取用户好友博客**************************/
			friends_blog=service.getFriendsBlog(loginuser);
			/***************************获取用户好友相册**************************/
			if(friends!=null && friends.size()>0)
			{
				friends_album_map=service.getFriendsAlbumMap(friends);
				if(friends_album_map!=null)
				{
					friends_album_map_key=service.getFriendsAlbumMapKey(friends_album_map, friends);
					friends_albums=service.getFriendsAlbum(friends);
				}
			}
			/***************************获取用户好友说说**************************/
			friends_mood=service.getFriendsMood(friends);
			if(friends_mood!=null){
				all_friends_mood=service.getFirendsMood(friends_mood);
			}
			friends_mood_comment=service.getFriendsMoodComment(friends);
			friends_mood_comment_reply=service.getFriendsMoodCommentReply(friends);
			/***************************获取用户好友留言**************************/
			user_message=service.getMessage(loginuser);
			if(friends!=null && friends.size()>0 && user_message!=null && user_message.size()>0){
				all_friends_message=service.getFriendsMessage(friends,user_message);
			}
			/********************保存参数到session中****************************/ 
			session.setAttribute("friends", friends);
			session.setAttribute("friends_info", friends_info);
			session.setAttribute("friends_blog",friends_blog);
			session.setAttribute("friends_album_map", friends_album_map);
			session.setAttribute("friends_album_map_key", friends_album_map_key);
			session.setAttribute("friends_albums", friends_albums);
			session.setAttribute("friends_message", friends_message);
			session.setAttribute("all_friends_message", all_friends_message);
			session.setAttribute("friends_mood", friends_mood);
			session.setAttribute("all_friends_mood", all_friends_mood);
			session.setAttribute("friends_mood_comment", friends_mood_comment);
			session.setAttribute("friends_mood_comment_reply", friends_mood_comment_reply);
	}

	
	public void init() throws ServletException {
		
	}

}
