package com.yangkai.myblog.dao;
import java.util.List;
import java.util.Map;
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

public interface MyBlogDAO {
	public String loginCheck(String username,String password);
	public List<Blog> getBlog(String username);
	public List<Blog> getAllBlog(String username);
	public List<Blog> getDraftBlog(String username);
	public List<Blog> getPrivateBlog(String username);
	public List<Message> getMessage(String username);
	public List<Message> getMyLeaveMessage(String guest);
	public List<MessageReply> getMyMessageReply(String guest);
	public List<MessageReply> getAllMessageReply(String username);
	public List<Mood> getMood(String username);	
	public List<MoodComment> getMoodComment(String username);
	public List<MoodCommentReply> getMoodCommentReply(String username);
	public User getUser(String username);
	public User getBloger(String username);
	public List<String> getFriends(String username);
	public Map<String,User> getFriendsInfo(String username);
	public List<Blog> getFriendsBlog(String username);
	public List<Blog> getLatestBlog();
	public List<BlogCommentReply> getBlogCommentReply(int blog_comment_id);
	public List<MessageReply> getMessageReply(String username);
	public int getAlbumId(String username, String album_name);
	public List<String> getBlogClass(String username);
	public List<BlogComment> getBlogComment(int blog_id);
	public Map<String,List<Photo>> getAlbumMap(String username);
	public List<Photo> getAlbumPhoto(int album_id);//DAO�ڲ�����
	public List<Album> getAlbum(String username);
	public Map<String,Map<String,List<Photo>>> getFriendsAlbumMap(List<String> friends);
	public Map<String,List<Album>> getFriendsAlbum(List<String> friends);
	public int addUser(User user);
	public int alterUserInfo(User user,String username);
	public int increaseBlogNum(String username);
	public int addBlogClass(String username,String blog_class);
	public int addBlog(Blog blog);
	public int addBlogComment(BlogComment blogcomment);
	public int addBlogCommentReply(BlogCommentReply blogcommentreply);
	public int addMessage(Message msg);
	public int addMessageReply(MessageReply messagereply);
	public int addMood(Mood mood);
	public int addMoodComment(MoodComment moodcomment);
	public int addMoodCommentReply(MoodCommentReply moodcommentreply);
	public int deleteBlog(int blog_id);
	public int deleteBlogComment(int blog_comment_id);
	public int deleteBlogCommentReply(int blog_comment_reply_id);
	public int deleteAlbum(String loginuser,String album_name);
	public int deleteMood(int mood_id);
	public int deleteMoodComment(int mood_comment_id);
	public int deleteMoodCommentReply(int mood_comment_reply_id);
	public int deleteMessage(int message_id);
	public int deleteMessageReply(int message_reply_id);
	public int updateBlogContent(Blog blog);
	public int uploadPhoto(List<Photo> photos);
	public boolean checkAlbumName(String username, String album_name);
	public boolean checkUsername(String username);
	public boolean checkBlogClass(String username, String blog_class);
	public int decreaseBlogNum(String username);
	public int createAlbum(Album album);
	public int alterBlogState(int blog_state,int blog_id);
	public int saveBlog(Blog blog);
	public Map<String, List<Mood>> getFriendsMood(List<String> friends);
	public List<MoodComment> getFriendsMoodComment(List<String> friends);
	public List<MoodCommentReply> getFriendsMoodCommentReply(List<String> friends);
	public Map<String, List<Message>> getFriendsMessage(List<String> friends,String username);
	public List<UserRequest> getUserRequest(String username);
	public int addUserRequestResponse(UserResponse userresponse);
	public int alterRequestState(int request_id);
	public int addUserRequest(UserRequest userrequest);
	public List<UserResponse> getUserResponse(String username);
	public List<Contacts> getContacts(String username);
	public List<String> getContactsClass(String username);
	public List<Contacts> queryContacts(String name, String username);
	public int addContacts(Contacts contacts);
	public int addContactsClass(String username,String contacts_class);
	public int deleteContacts(int contacts_id);
	public int alterBlogInfo(User user);
	public int deletePhoto(int photo_id);
	public int addFriend(String friend_name,String username);
}
