package com.yangkai.myblog.tools;
public class PagingParamTool {
	
	public static int getCurrentpage(String curr_page,int countpage)
	{
		Integer currpage=null;
		if(curr_page==null || curr_page.equals("null"))
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
		//System.out.println("分页工具类------>PagingParamTool:getCurrentpage()");
		//System.out.println("curr_page:"+curr_page);
		//System.out.println("countpage:"+countpage);
		//System.out.println("--------------------");
		return currpage;
	}
	public static int getCurrentstep(String curr_step,int countstep)
	{
		Integer currstep=null;
		if(curr_step==null || curr_step.equals("null"))
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
		//System.out.println("分页工具类------>PagingParamTool:getCurrentstep()");
		//System.out.println("curr_step:"+curr_step);
		//System.out.println("countstep:"+countstep);
		//System.out.println("--------------------");
		return currstep;
	}
	public static int getCountpage(int size,int pagesize)
	{
		Integer countpage=null;
		if(size%pagesize==0)
		{
			countpage=size/pagesize;
		}
		else{
			countpage=size/pagesize+1;
		}
		//System.out.println("分页工具类------>PagingParamTool:getCountpage()");
		//System.out.println("size:"+size);
		//System.out.println("pagesize:"+pagesize);
		//System.out.println("--------------------");
		return countpage;
	}
	public static int getCountstep(int size,int pagesize,int pagestep)
	{
		Integer countstep=null;
		if(size%(pagesize*pagestep)!=0)
		{
			countstep=size/(pagesize*pagestep)+1;
		}
		else{
			countstep=size/(pagesize*pagestep);
		}
		//System.out.println("分页工具类------>PagingParamTool:getCountstep()");
		//System.out.println("size:"+size);
		//System.out.println("pagesize:"+pagesize);
		//System.out.println("pagestep:"+pagestep);
		//System.out.println("--------------------");
		return countstep;
	}
}
