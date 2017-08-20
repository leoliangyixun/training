package com.yangkai.myblog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yangkai.myblog.constants.Constants;
import com.yangkai.myblog.domain.Album;
import com.yangkai.myblog.domain.Blog;
import com.yangkai.myblog.domain.Contacts;
import com.yangkai.myblog.domain.UserRequest;
import com.yangkai.myblog.domain.Message;
import com.yangkai.myblog.domain.MessageReply;
import com.yangkai.myblog.domain.Mood;
import com.yangkai.myblog.domain.MoodComment;
import com.yangkai.myblog.domain.MoodCommentReply;
import com.yangkai.myblog.domain.Photo;
import com.yangkai.myblog.domain.User;
import com.yangkai.myblog.domain.UserResponse;
import com.yangkai.myblog.factory.ServiceFactory;
import com.yangkai.myblog.service.MyBlogService;
import com.yangkai.myblog.tools.EncoderUtil;
public class LoginCheck extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
			int login_mark=Integer.parseInt(request.getParameter("login_mark"));//�����ж��û��Ǵ���ҳ��¼���Ǵӵ�½�����¼��
			/*
			System.out.println("Request URL:"+request.getPathInfo());
			System.out.println("getRequestURI():"+request.getRequestURI());
			System.out.println("getPathInfo():"+request.getPathInfo());
			System.out.println("getPathTranslated():"+request.getPathTranslated());
			System.out.println("getQueryString():"+request.getQueryString());
			System.out.println("getRealPath():"+request.getRealPath("/"));
			System.out.println("getRemoteUser():"+request.getRemoteUser());
			System.out.println("getServletPath():"+request.getServletPath());
			System.out.println("getRequestURL():"+request.getRequestURL().toString());
			*/
			HttpSession session=request.getSession();
			String loginuser=null;
			List<String> friends=null;
			Map<String,User> friends_info=null;
			
			List<Blog> friends_blog=null;
			List<Blog> user_blog=null;
			List<Blog> draft_blog=null;
			List<Blog> private_blog=null;
			//List<Blog> all_blog=null;
			List<String> user_blog_class=null;
			
			Map<String,List<Photo>> album_map=null;
			List<String> album_map_key=null;
			List<Album> user_album=null;
			
			Map<String,Map<String,List<Photo>>> friends_album_map=null;
			Map<String,List<Album>> friends_albums=null;
			Map<String,List<String>> friends_album_map_key=null;
			
			List<Mood> user_mood=null;
			List<MoodComment> mood_comment=null;
			List<MoodCommentReply> mood_comment_reply=null;
			Map<String,List<Mood>>friends_mood=null;
			List<Mood> all_friends_mood=null;
			List<MoodComment> friends_mood_comment=null;
			List<MoodCommentReply> friends_mood_comment_reply=null;
			
			List<Message> user_message=null;
			List<Message> my_leave_message=null;
			//List<MessageReply> user_message_reply=null;
			//List<MessageReply> my_leave_message_reply=null;
			List<MessageReply> all_message_reply=null;
			Map<String,List<Message>> friends_message=null;
			List<Message> all_friends_message=null;
			
			List<UserRequest> user_request=null;
			List<UserResponse> user_response=null;
			
			List<Contacts> user_contacts=null;
			List<String> user_contacts_class=null;
			
			String username=EncoderUtil.encode(request.getParameter("username").trim());
			String password=request.getParameter("password").trim();
			MyBlogService service=ServiceFactory.getMyBlogService(request);
			loginuser=service.loginCheck(username, password);
			if(loginuser!=null) 
			{	
				System.out.println("��¼�ɹ�����¼�ˣ�"+loginuser);
				/***************************��ȡ�û�������Ϣ **************************/
				user_blog_class = service.getBlogClass(loginuser);//��ȡ�û��������
				user_blog=service.getBlog(loginuser);//��ȡ�û����͡�
				draft_blog=service.getDraftBlog(loginuser);//��ȡ�û��ݸ��䡣
				private_blog=service.getPrivateBlog(loginuser);//��ȡ�û�˽����־��
				/*
				if((user_blog!=null || draft_blog!=null || private_blog!=null) && (user_blog.size()>0 || draft_blog.size()>0 || private_blog.size()>0))
				{
					all_blog=new ArrayList<Blog>();
					if(user_blog!=null && user_blog.size()>0)
					{
						all_blog.addAll(user_blog);
					}
					if(draft_blog!=null && draft_blog.size()>0)
					{
						all_blog.addAll(draft_blog);
					}
					if(private_blog!=null && private_blog.size()>0)
					{
						all_blog.addAll(private_blog);
					}
	                //���Դ��롣
					//System.out.println("�û��Ĳ��Ĳ�Ϊ�ա�");
					//System.out.println("all_blog:"+all_blog);
					//System.out.println("all_blog.size:"+all_blog.size());
				}
			    */
	
				
				/***************************��ȡ�û����******************************/
				album_map=service.getAlbumMap(loginuser);//��ȡ�û����
				user_album=service.getAlbum(loginuser);//��ȡ�û������Ϣ
				album_map_key=service.getAlbumMapKey(album_map);//��ȡ��Ἧ�ϵ�key��	
				//album_map=dao.getAlbumMapping(loginuser);
				//albums=dao.getAlbum(loginuser);
				/*
				 * ����album_map_key��Ŀ����Ϊ�˷������album_map���Map����
				 * �ܷ�����album_map_key��ֱ������Set���ϱ���album_map???
				 */
				
	//			 System.out.println(album_map);
	//			 System.out.println(albums);
	//			 System.out.println(album_map_key);
				/***************************��ȡ�û�˵˵��Ϣ**************************/
				user_mood=service.getMood(loginuser);
				mood_comment=service.getMoodComment(loginuser);
				mood_comment_reply=service.getMoodCommentReply(loginuser);
				
				/***************************��ȡ�û�������Ϣ**************************/
				user_message=service.getMessage(loginuser);
			    my_leave_message=service.getMyLeaveMessage(loginuser);
			    all_message_reply=service.getAllMessageReply(loginuser);
				//user_message_reply=service.getMessageReply(loginuser);
				//my_leave_message_reply=service.getMyMessageReply(loginuser);
				
				/*
				if((user_message_reply!=null && user_message_reply.size()>0) || (my_leave_message_reply!=null && my_leave_message_reply.size()>0))
				{
					List<MessageReply> all_message_reply=new ArrayList<MessageReply>();
					all_message_reply.addAll(user_message_reply);
					all_message_reply.addAll(my_leave_message_reply);
					session.setAttribute("all_message_reply", all_message_reply);	
					//System.out.println("user_message_reply:"+user_message_reply.size());
					//System.out.println("my_leave_message_reply:"+my_leave_message_reply.size());
					//System.out.println("all_message_reply:"+all_message_reply.size());
				}
				*/
			    /***************************��ȡ�û�����������Ϣ ****************************/
				User bloger=service.getBloger(loginuser);//��ȡ�û���Ϣ
			    user_request=service.getUserRequest(loginuser);
			    user_response=service.getUserResponse(loginuser);
			    user_contacts=service.getContacts(loginuser);
			    user_contacts_class=service.getContactsClass(loginuser);
				/***************************��ȡ�û�������Ϣ ****************************/
				friends=service.getFriends(loginuser);//��ȡ�û����ѡ�
				friends_info=service.getFriendsInfo(loginuser);//��ȡ�û�������Ϣ��
				/***************************��ȡ�û����Ѳ���**************************/
				friends_blog=service.getFriendsBlog(loginuser);//��ȡ���Ѳ��͡�
				/***************************��ȡ�û��������**************************/
				if(friends!=null && friends.size()>0)
				{
					friends_album_map=service.getFriendsAlbumMap(friends);
					if(friends_album_map!=null)
					{
						friends_album_map_key=service.getFriendsAlbumMapKey(friends_album_map, friends);
						friends_albums=service.getFriendsAlbum(friends);
					}
				}
				/***************************��ȡ�û�����˵˵**************************/
				friends_mood=service.getFriendsMood(friends);//��ȡ����˵˵��Map���ϣ�key--->��������value--->List<Mood>��
				if(friends_mood!=null){
					all_friends_mood=service.getFirendsMood(friends_mood);//�����к��ѵĵ�˵˵�ϲ���һ��List<Mood>��
				}
				friends_mood_comment=service.getFriendsMoodComment(friends);
				friends_mood_comment_reply=service.getFriendsMoodCommentReply(friends);
				/***************************��ȡ�û���������**************************/
				 //��DAO���ȡ���ݡ�
				/*
				friends_message=service.getFriendsMessage(friends,loginuser);
				if(friends_message!=null && friends_message.size()>0){
					all_friends_message=service.getFriendsMessage(friends_message);
				}
				*/
				//��user_message�����л�ȡ���ݡ�
				if(friends!=null && friends.size()>0 && user_message!=null && user_message.size()>0){
					all_friends_message=service.getFriendsMessage(friends,user_message);
				}
				/********************���������session��****************************/ 
				session.setAttribute("loginuser",loginuser);
				session.setAttribute("bloger", bloger);
				session.setAttribute("friends", friends);
				session.setAttribute("friends_info", friends_info);
				
				session.setAttribute("user_blog",user_blog);
				session.setAttribute("draft_blog",draft_blog);
				session.setAttribute("private_blog",private_blog);
			    //session.setAttribute("all_blog",all_blog);
				session.setAttribute("friends_blog",friends_blog);
				session.setAttribute("user_blog_class",user_blog_class);
				
				session.setAttribute("album_map",album_map);
				session.setAttribute("album_map_key",album_map_key);
				session.setAttribute("friends_album_map", friends_album_map);
				session.setAttribute("friends_album_map_key", friends_album_map_key);
				session.setAttribute("friends_albums", friends_albums);
				session.setAttribute("user_album",user_album);
				
				session.setAttribute("user_message", user_message);
				session.setAttribute("my_leave_message", my_leave_message);
				session.setAttribute("friends_message", friends_message);
				session.setAttribute("all_friends_message", all_friends_message);
				//session.setAttribute("user_message_reply", user_message_reply);
				//session.setAttribute("my_leave_message_reply", my_leave_message_reply);
				session.setAttribute("all_message_reply", all_message_reply);
				
				session.setAttribute("user_mood",user_mood);
				session.setAttribute("mood_comment",mood_comment);
				session.setAttribute("mood_comment_reply",mood_comment_reply);
				session.setAttribute("friends_mood", friends_mood);
				session.setAttribute("all_friends_mood", all_friends_mood);
				session.setAttribute("friends_mood_comment", friends_mood_comment);
				session.setAttribute("friends_mood_comment_reply", friends_mood_comment_reply);
				
				session.setAttribute("user_request",user_request);
				session.setAttribute("user_response",user_response);
				
				session.setAttribute("user_contacts_class",user_contacts_class);
				session.setAttribute("user_contacts",user_contacts);
			}
			else{
				System.out.println("��¼ʧ�ܣ�ԭ���û������������.");
				session.setAttribute("login","false");	
				if(login_mark==Constants.BLOG_LOGIN_MARK_FROM_LOGIN)
				{
					response.sendRedirect("login/login.jsp");	
					return;
				}
			}
			response.sendRedirect("index.jsp");	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
