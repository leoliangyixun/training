package com.yangkai.myblog.tools;

import java.util.List;

import com.yangkai.myblog.domain.Album;
import com.yangkai.myblog.domain.Blog;
import com.yangkai.myblog.domain.Mood;

public class CalculateNumUtil {
	public static int getBlogNum(List<Blog> user_blog){
		if(user_blog!=null){
			return user_blog.size();
		}else{
			return 0;
		}
	}
	public static int getMoodNum(List<Mood> user_mood){
		if(user_mood!=null){
			return user_mood.size();
		}else{
			return 0;
		}
	}
	public static int getAlbumNum(List<Album> user_album){
		if(user_album!=null){
			return user_album.size();
		}else{
			return 0;
		}
	}
}
