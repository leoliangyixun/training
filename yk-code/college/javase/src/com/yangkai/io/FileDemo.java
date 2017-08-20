package com.yangkai.io;
import java.io.*;
import java.lang.*;
public class FileDemo{
public static void main(String[] args) throws IOException,InterruptedException
{
        /*
	File ff=new File("ff.txt");
	ff.createNewFile();
	File fd=new File("doc");
	fd.mkdir();

	File fdir=new File(File.separator);//创建父目录对象，也就是当前根目录。
	String str="java"+File.separator+"io"+File.separator+"File"+File.separator+"childDir";
	File cdir=new File(fdir,str);//在父目录下创建子目录。
	cdir.mkdir();
	//cdir.delete();
	cdir.deleteOnExit();
	Thread.sleep(3000);
        */
	File fdir=new File(File.separator);
	String str="java"+File.separator+"io"+File.separator+"File";
	File cdir=new File(fdir,str);
	
	if(cdir.isDirectory())
	{
	/*
		String[] name=cdir.list();
		for(int i=0;i<name.length;i++)
	    {
			System.out.println(name[i]);
	    }
	*/
		String[] name=cdir.list(new FilenameFilter(){
			public boolean accept(File dir,String name)
			{
				if(name.indexOf(".java")!=-1)
				{
					return true;	
				}
				else{
					return false;
				}
			}
		});
		for(int i=0;i<name.length;i++)
	    {
			System.out.println(name[i]);
	    }
	}
	
}
}