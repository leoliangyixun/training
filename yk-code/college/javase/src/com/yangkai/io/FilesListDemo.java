package com.yangkai.io;

import java.io.File;
import java.io.IOException;


public class FilesListDemo {

	public static void main(String[] args) throws IOException{
		File parent=new File(File.separator);
		String str="jsp"+File.separator+"myblog"+File.separator+"WebRoot"+File.separator+"photos"+File.separator+"leoliangyixun"+File.separator+"dongman"+File.separator;
		File child=new File(parent,str);
		String[] list=child.list();
		File[] filelist=child.listFiles();
			for(int i=0;i<filelist.length;i++)
			{
				System.out.println(filelist[i]);
			}
			for(int i=0;i<list.length;i++)
			{
				System.out.println(list[i]);
			}
			}

}
