package com.yangkai.test;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
	public static void main(String[] args) {
		BlogCommentListBean mub_list = new BlogCommentListBean();
		mub_list.setBlogId(1);
		mub_list.setGuestusername("yangkai");
		mub_list.setBlogcommentcontent("miss you");
		mub_list.setBlogcommentdate("2012-10-3 16:27");
		List<BlogCommentListBean> bc_comment=new ArrayList<BlogCommentListBean>();
		bc_comment.add(mub_list);
		for(int i=0;i<bc_comment.size();i++)
		{
			BlogCommentListBean bean=bc_comment.get(i);
			System.out.println(bean.getBlogId());
			System.out.println(bean.getBlogcommentId());
			System.out.println(bean.getGuestusername());
			System.out.println(bean.getBlogcommentcontent());
			System.out.println(bean.getBlogcommentdate());
		}
		System.out.println("-----------------------------------");
		BlogCommentListBean mub_list1 = new BlogCommentListBean();
		mub_list1.setBlogId(2);
		mub_list1.setGuestusername("fucui");
		mub_list1.setBlogcommentcontent("love you");
		mub_list1.setBlogcommentdate("2012-10-3 16:31");
		bc_comment.add(mub_list1);
		for(int i=0;i<bc_comment.size();i++)
		{
			BlogCommentListBean bean=bc_comment.get(i);
			System.out.println(bean.getBlogId());
			System.out.println(bean.getBlogcommentId());
			System.out.println(bean.getGuestusername());
			System.out.println(bean.getBlogcommentcontent());
			System.out.println(bean.getBlogcommentdate());
		}

	}

}
