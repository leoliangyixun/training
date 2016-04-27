package com.yangkai.myblog.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.yangkai.myblog.domain.Blog;
import com.yangkai.myblog.domain.Contacts;
import com.yangkai.myblog.domain.Mood;

public class PagingUtil {
	
	public static int getCurrentpage(String curr_page,int countpage)
	{
		Integer currpage=null;
		if(curr_page==null || curr_page.equals("null") || curr_page.equals(""))
		{
			currpage=1;
		}
		else{
			currpage=Integer.parseInt(curr_page);
		}
		if(currpage<1)
		{
			currpage=1;
		}
		if(currpage>countpage)
		{
			currpage=countpage;
		}
		//System.out.println("分页工具类------>PagingUtil:getCurrentpage()");
		//System.out.println("curr_page:"+curr_page);
		//System.out.println("countpage:"+countpage);
		//System.out.println("--------------------");
		return currpage;
	}
	public static int getCurrentstep(String curr_step,int countstep)
	{
		Integer currstep=null;
		if(curr_step==null || curr_step.equals("null") || curr_step.equals(""))
		{
			currstep=1;
		}
		else{
			currstep=Integer.parseInt(curr_step);
		}
		if(currstep<1)
		{
			currstep=1;
		}
		if(currstep>countstep)
		{
			currstep=countstep;
		}
		//System.out.println("分页工具类------>PagingUtil:getCurrentstep()");
		//System.out.println("curr_step:"+curr_step);
		//System.out.println("countstep:"+countstep);
		//System.out.println("--------------------");
		return currstep;
	}
	public static int getCountpage(int size,int pagesize)
	{
		Integer countpage=null;
		if(size==0){
			size=1;
		}
		if(size%pagesize==0)
		{
			countpage=size/pagesize;
		}
		else{
			countpage=size/pagesize+1;
		}
		//System.out.println("分页工具类------>PagingUtil:getCountpage()");
		//System.out.println("size:"+size);
		//System.out.println("pagesize:"+pagesize);
		//System.out.println("--------------------");
		return countpage;
	}
	public static int getCountstep(int size,int pagesize,int pagestep)
	{
		Integer countstep=null;
		if(size==0){
			size=1;
		}
		if(size%(pagesize*pagestep)!=0)
		{
			countstep=size/(pagesize*pagestep)+1;
		}
		else{
			countstep=size/(pagesize*pagestep);
		}
		//System.out.println("分页工具类------>PagingUtil:getCountstep()");
		//System.out.println("size:"+size);
		//System.out.println("pagesize:"+pagesize);
		//System.out.println("pagestep:"+pagestep);
		//System.out.println("--------------------");
		return countstep;
	}
	public static int getFriendsMoodNum(Map<String,List<Mood>> map){
		int mood_num=0;
		Set<Map.Entry<String,List<Mood>>> set=map.entrySet();
		for(Entry<String,List<Mood>> entry:set){
			List<Mood>friend_mood=entry.getValue();
			mood_num+=friend_mood.size();
		}
		return mood_num;
	}
	public static int getNum(Map map){
		int num=0;
		Set<Map.Entry> set=map.entrySet();
		for(Entry entry:set){
			List list=(List) entry.getValue();
			num+=list.size();
		}
		return num;
	}
	
	public static  List<Blog> getSpecifiedBlog(List<Blog> user_blog,String link_blog_class){
		List<Blog> specified_user_blog=null;
		for(int i=0;i<user_blog.size();i++){
			if(user_blog.get(i).getBlogclass().equals(link_blog_class)){
				if(specified_user_blog==null){
					specified_user_blog=new ArrayList<Blog>();
				}
				specified_user_blog.add(user_blog.get(i));
			}  
		}
		return specified_user_blog;
	}
	
	public static  List<Contacts> getSpecifiedContacts(List<Contacts> user_contacts,String contacts_class){
		List<Contacts> specified_user_contacts=null;
		for(int i=0;i<user_contacts.size();i++){
			if(user_contacts.get(i).getContactsclass().equals(contacts_class)){
				if(specified_user_contacts==null){
					specified_user_contacts=new ArrayList<Contacts>();
				}
				specified_user_contacts.add(user_contacts.get(i));
			}  
		}
		/*
		if(specified_user_contact!=null){
			for(int i=0;i<specified_user_contact.size();i++){
				System.out.println(specified_user_contact.get(i).getContact());
			}
		}
		*/
//		System.out.println(specified_user_contacts);
		return specified_user_contacts;
	}
	
}
